package com.changing.party.service;

import com.changing.party.common.enums.StakeStatus;
import com.changing.party.common.exception.*;
import com.changing.party.dto.StakeDTO;
import com.changing.party.dto.UserStakeDTO;
import com.changing.party.dto.UserStakePlayerDTO;
import com.changing.party.dto.UserStakeRoundDTO;
import com.changing.party.model.*;
import com.changing.party.repository.StakeDetailRepository;
import com.changing.party.repository.StakePlayerRepository;
import com.changing.party.repository.StakeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class StakeService {

    StakeRepository stakeRepository;
    StakeDetailRepository stakeDetailRepository;
    StakePlayerRepository stakePlayerRepository;
    UserService userService;

    @Getter
    @AllArgsConstructor
    public enum MemoryStakeStatus {
        /**
         * 沒有任何賭局進行中
         */
        NONE(1),
        /**
         * 賭局開放下注
         */
        OPEN(2),
        /**
         * 賭局停止下注
         */
        CLOSE(3);

        int status;
    }

    private static MemoryStakeStatus memoryStakeStatus = MemoryStakeStatus.NONE;
    private static int memoryLastStakeId;

    public StakeService(StakeRepository stakeRepository, StakeDetailRepository stakeDetailRepository, StakePlayerRepository stakePlayerRepository, UserService userService) {
        this.stakeRepository = stakeRepository;
        this.stakeDetailRepository = stakeDetailRepository;
        this.stakePlayerRepository = stakePlayerRepository;
        this.userService = userService;
    }

    /**
     * 後臺管理網站查詢開盤歷史資訊
     *
     * @return
     */
    public List<StakeDTO> getStakeHistoryList() {
        List<Stake> stakes = stakeRepository.findAll();
        List<StakeDTO> stakeDTOS = new ArrayList<>();
        stakes.forEach(x -> stakeDTOS.add(StakeDTO.getStakeDTO(x)));
        return stakeDTOS;
    }

    /**
     * 建立新的賭盤
     *
     * @param stakeDTO
     */
    public void createStake(StakeDTO stakeDTO) throws AlreadyOneStakeISOpenException {
        if (!StakeService.getMemoryStakeStatus().equals(MemoryStakeStatus.NONE)) {
            throw new AlreadyOneStakeISOpenException();
        }

        if (stakeRepository.existsByStatus(StakeStatus.OPEN)) {
            throw new AlreadyOneStakeISOpenException();
        }
        Stake stake = stakeRepository.save(Stake.builder()
                .status(StakeStatus.OPEN)
                .title(stakeDTO.getTitle())
                .build());
        stakeDTO.getStakePlayer().forEach(
                x -> stakePlayerRepository.save(
                        StakePlayer.builder()
                                .stake(stake)
                                .playerId(x.getPlayerId())
                                .playerName(x.getPlayerName())
                                .build()
                )
        );
        StakeService.setMemoryStakeStatus(MemoryStakeStatus.OPEN);
        StakeService.setMemoryLastStakeId(stake.getId());
    }

    /**
     * 停止下注
     *
     * @param data
     * @throws StakeIdNotFoundException
     * @throws StakeIsNotOpenException
     */
    public void stopStake(Integer data) throws StakeIdNotFoundException, StakeIsNotOpenException {
        if (!StakeService.getMemoryStakeStatus().equals(MemoryStakeStatus.OPEN)) {
            throw new StakeIdNotFoundException(data);
        }
        Stake stake = stakeRepository.findById(data)
                .orElseThrow(() -> new StakeIdNotFoundException(data));

        if (stake.getStatus() != StakeStatus.OPEN)
            throw new StakeIsNotOpenException(stake.getId(), stake.getTitle());

        stake.setStatus(StakeStatus.CLOSE);
        stakeRepository.save(stake);
        StakeService.setMemoryStakeStatus(MemoryStakeStatus.CLOSE);
    }

    /**
     * 結算賭局
     *
     * @param stakeId
     * @param winnerId
     * @throws StakeIsNotCloseException
     * @throws StakeIdNotFoundException
     * @throws StakeWinnerPlayIdNotFoundException
     */
    public void finishStake(int stakeId, int winnerId)
            throws StakeIsNotCloseException,
            StakeIdNotFoundException,
            StakeWinnerPlayIdNotFoundException {

        if (!StakeService.getMemoryStakeStatus().equals(MemoryStakeStatus.CLOSE)) {
            throw new StakeIdNotFoundException(stakeId);
        }

        Stake stake = stakeRepository.findById(stakeId)
                .orElseThrow(() -> new StakeIdNotFoundException(stakeId));

        if (stake.getStakePlayers().stream().noneMatch(x -> x.getPlayerId().equals(winnerId))) {
            throw new StakeWinnerPlayIdNotFoundException();
        }

        if (stake.getStatus() != StakeStatus.CLOSE)
            throw new StakeIsNotCloseException(stake.getId(), stake.getTitle());

        stake.setWinnerId(winnerId);
        stake.setStatus(StakeStatus.FINISH);
        stakeRepository.save(stake);
        calcBetsResult(stake, winnerId);
        StakeService.setMemoryStakeStatus(MemoryStakeStatus.NONE);
    }

    /**
     * 計算最後一輪下注的結果
     */
    private void calcBetsResult(Stake stake, int winnerPlayer) throws StakeWinnerPlayIdNotFoundException {
        int winnerId = stake
                .getStakePlayers()
                .stream()
                .filter(x -> x.getPlayerId() == winnerPlayer)
                .findFirst()
                .orElseThrow(StakeWinnerPlayIdNotFoundException::new)
                .getPlayerId();
        int totalLosePoint = stakeDetailRepository
                .findByStake_IdAndStakePlayer_PlayerIdIsNot(stake.getId(), winnerId)
                .stream()
                .mapToInt(StakeOnlyBetPoint::getStakePoint)
                .sum();

        Set<StakeDetail> winnerList = stakeDetailRepository.findByStake_IdAndStakePlayer_PlayerId(stake.getId(), winnerId);
        int totalWinPoint = winnerList.stream().mapToInt(StakeDetail::getStakePoint).sum();

        for (StakeDetail stakeDetail : winnerList) {
            double winPointPercentage = 1.0 * stakeDetail.getStakePoint() / totalWinPoint;
            int winPoint = (int) Math.ceil(totalLosePoint * winPointPercentage) + stakeDetail.getStakePoint();
            stakeDetail.setWinPoint(winPoint);
            stakeDetailRepository.save(stakeDetail);
            userService.updateUserPoint(stakeDetail.getUserModel(), winPoint);
        }
    }

    /**
     * 查詢使用者最後一筆賭局資訊
     *
     * @return
     */
    public UserStakeDTO getStake() throws StakeIdNotFoundException {
        UserModel user = userService.getUserModelFromSecurityContext();
        Set<StakeDetail> stakeDetails = stakeDetailRepository.findByUserModel_UserIdAndStake_Id(user.getUserId(), memoryLastStakeId);
        // 不開放下注且資料庫沒有使用者上一輪資訊
        if (memoryStakeStatus != MemoryStakeStatus.OPEN && stakeDetails.isEmpty()) {
            return null;
        }
        return getUserStakeByStakeId(memoryLastStakeId, stakeDetails);
    }

    /**
     * 查詢使用者下注歷程資訊
     */
    public List<UserStakeRoundDTO> getUserStakeHistory() throws StakeIdNotFoundException {
        UserModel user = userService.getUserModelFromSecurityContext();
        Set<StakeDetail> stakeDetailList = stakeDetailRepository.findByUserModel_UserIdOrderByIdAsc(user.getUserId());
        Map<Object, List<StakeDetail>> stakeDetailHashMap =
                stakeDetailList.stream().collect(Collectors.groupingBy(x -> x.getStake().getId()));
        List<UserStakeRoundDTO> userStakeRoundDTOs = new ArrayList<>();
        Iterator<Object> keySetIterator = stakeDetailHashMap.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer stakeIdKey = (Integer) keySetIterator.next();
            userStakeRoundDTOs.add(getUserStakeRoundByStakeId(stakeIdKey, stakeDetailHashMap.get(stakeIdKey).stream().collect(Collectors.toSet())));
        }
        return userStakeRoundDTOs;
    }

    /**
     * 查詢使用者特定賭局下注資訊
     *
     * @param stakeId
     * @return
     * @throws StakeIdNotFoundException
     */
    private UserStakeRoundDTO getUserStakeRoundByStakeId(int stakeId, Set<StakeDetail> stakeDetails) throws StakeIdNotFoundException {
        Stake lastStake = stakeRepository.findById(stakeId).orElseThrow(() -> new StakeIdNotFoundException(stakeId));
        List<UserStakePlayerDTO> userStakePlayerDTOs = getUserStakePlayerDTOList(lastStake.getStakePlayers());
        addUserStakePoint(userStakePlayerDTOs, stakeDetails);
        Date createTime = null;
        Integer beforePoint = null;
        Optional<StakeDetail> stakeDetail = stakeDetails.stream().findFirst();
        if (stakeDetail.isPresent()) {
            createTime = stakeDetail.get().getStakeTime();
            beforePoint = stakeDetail.get().getBeforePoint();
        }
        return UserStakeRoundDTO.builder()
                .stakeId(lastStake.getId())
                .status(lastStake.getStatus())
                .title(lastStake.getTitle())
                .player(userStakePlayerDTOs)
                .winner(lastStake.getWinnerId())
                .winPoint(stakeDetails.stream().mapToInt(StakeDetail::getWinPoint).sum())
                .createTime(createTime)
                .beforePoint(beforePoint)
                .build();
    }

    /**
     * 查詢使用者特定賭局下注資訊
     *
     * @param stakeId
     * @return
     * @throws StakeIdNotFoundException
     */
    private UserStakeDTO getUserStakeByStakeId(int stakeId, Set<StakeDetail> stakeDetails) throws StakeIdNotFoundException {
        Stake lastStake = stakeRepository.findById(stakeId).orElseThrow(() -> new StakeIdNotFoundException(stakeId));
        List<UserStakePlayerDTO> userStakePlayerDTOs = getUserStakePlayerDTOList(lastStake.getStakePlayers());
        addUserStakePoint(userStakePlayerDTOs, stakeDetails);
        return UserStakeDTO.builder()
                .stakeId(lastStake.getId())
                .status(lastStake.getStatus())
                .title(lastStake.getTitle())
                .player(userStakePlayerDTOs)
                .winner(lastStake.getWinnerId())
                .winPoint(stakeDetails.stream().mapToInt(x -> x.getWinPoint()).sum())
                .build();
    }

    /**
     * 加入使用者下注資訊
     *
     * @param userStakePlayerDTOs
     * @param stakeDetails
     */
    private void addUserStakePoint(List<UserStakePlayerDTO> userStakePlayerDTOs, Set<StakeDetail> stakeDetails) {
        // 加入下注資訊
        stakeDetails.forEach(x ->
                userStakePlayerDTOs.stream().filter(userStakePlayerDTO ->
                                userStakePlayerDTO.getPlayerId().equals(x.getStakePlayer().getPlayerId()))
                        .findFirst().ifPresent(existUserStakePlayerDTO -> existUserStakePlayerDTO.setPoint(x.getStakePoint()))
        );
    }

    /**
     * 使用者下注
     *
     * @param stakeId
     * @param userStakeDTOList
     */
    public void userPlaceBets(int stakeId, List<UserStakePlayerDTO> userStakeDTOList)
            throws StakeIsNotOpenException, StakeIdNotFoundException, StakePlayerIdNotFoundException, UserPointNotEnoughException, UserAlreadyPlaceBetsException {
        if (memoryStakeStatus != MemoryStakeStatus.OPEN) {
            throw new StakeIsNotOpenException(stakeId, "");
        }
        UserModel user = userService.getUserModelFromSecurityContext();

        if (!stakeDetailRepository.findByUserModel_UserIdAndStake_Id(user.getUserId(), memoryLastStakeId).isEmpty()) {
            throw new UserAlreadyPlaceBetsException();
        }

        Stake stake = stakeRepository.findById(stakeId).orElseThrow(() -> new StakeIdNotFoundException(stakeId));
        if (stake.getStatus() != StakeStatus.OPEN) {
            throw new StakeIsNotOpenException(stakeId, "");
        }

        List<StakePlayer> stakePlayers = stakePlayerRepository.findByStake_Id(stakeId);
        //確認總下注籌碼少於或等於使用者擁有的點數
        int totalBetsPoint = userStakeDTOList.stream().mapToInt(x -> x.getPoint()).sum();
        if (totalBetsPoint > user.getUserPoint()) {
            throw new UserPointNotEnoughException();
        }

        List<StakeDetail> saveStakeDetails = new ArrayList<>();
        int userCurrentPoint = user.getUserPoint();
        for (UserStakePlayerDTO userStakePlayerDTO : userStakeDTOList) {
            // 確認 player id 存在
            StakePlayer stakePlayer =
                    stakePlayers.stream()
                            .filter(x -> x.getPlayerId().equals(userStakePlayerDTO.getPlayerId())).findFirst()
                            .orElseThrow(() -> new StakePlayerIdNotFoundException(userStakePlayerDTO.getPlayerId()));
            saveStakeDetails.add(StakeDetail.builder()
                    .stake(stake)
                    .userModel(user)
                    .stakePlayer(stakePlayer)
                    .stakePoint(userStakePlayerDTO.getPoint())
                    .winPoint(0)
                    .stakeTime(new Date())
                    .beforePoint(userCurrentPoint)
                    .build());
            userCurrentPoint -= userStakePlayerDTO.getPoint();
        }

        saveStakeDetails.forEach(x -> stakeDetailRepository.save(x));
        userService.updateUserPoint(user, -totalBetsPoint);
    }

    /**
     * 清空所有資料
     */
    public void clearAll() {
        stakeDetailRepository.deleteAll();
        stakePlayerRepository.deleteAll();
        stakeRepository.deleteAll();
    }

    /**
     * 將 StakePlayer 轉換為 UserStakePlayerDTO
     *
     * @param stakePlayers
     * @return
     */
    private List<UserStakePlayerDTO> getUserStakePlayerDTOList(List<StakePlayer> stakePlayers) {
        List<UserStakePlayerDTO> userStakePlayerDTOs = new ArrayList<>();
        stakePlayers.forEach(x -> userStakePlayerDTOs.add(UserStakePlayerDTO.getUserStakePlayerDTO(x)));
        return userStakePlayerDTOs;
    }

    public static MemoryStakeStatus getMemoryStakeStatus() {
        return memoryStakeStatus;
    }

    public static void setMemoryStakeStatus(MemoryStakeStatus memoryStakeStatus) {
        StakeService.memoryStakeStatus = memoryStakeStatus;
    }

    public static int getMemoryLastStakeId() {
        return memoryLastStakeId;
    }

    public static void setMemoryLastStakeId(int memoryLastStakeId) {
        StakeService.memoryLastStakeId = memoryLastStakeId;
    }
}
