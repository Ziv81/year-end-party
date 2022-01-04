package com.changing.party.service;

import com.changing.party.common.enums.StakeStatus;
import com.changing.party.common.exception.*;
import com.changing.party.dto.StakeDTO;
import com.changing.party.dto.UserStakeDTO;
import com.changing.party.dto.UserStakePlayerDTO;
import com.changing.party.model.Stake;
import com.changing.party.model.StakeDetail;
import com.changing.party.model.StakePlayer;
import com.changing.party.model.UserModel;
import com.changing.party.repository.StakeDetailRepository;
import com.changing.party.repository.StakePlayerRepository;
import com.changing.party.repository.StakeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    public void createStake(StakeDTO stakeDTO) throws AlreadyOneStackISOpenException {
        if (!StakeService.getMemoryStakeStatus().equals(MemoryStakeStatus.NONE)) {
            throw new AlreadyOneStackISOpenException();
        }

        if (stakeRepository.existsByStatus(StakeStatus.OPEN)) {
            throw new AlreadyOneStackISOpenException();
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
        //TODO 結算邏輯
        stakeRepository.save(stake);
        StakeService.setMemoryStakeStatus(MemoryStakeStatus.NONE);
    }

    /**
     * 查詢使用者最後一筆賭局資訊
     *
     * @return
     */
    public UserStakeDTO getStake() throws StakeIdNotFoundException {
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
    public List<UserStakeDTO> getUserStakeHistory() {
        Set<StakeDetail> stakeDetailList = stakeDetailRepository.findByUserModel_UserId(user.getUserId());
        return null;
    }


    /**
     * 查詢使用者特定賭局下注資訊，若該局沒有下注資訊回傳 Optional.empty()
     *
     * @param stakeId
     * @return
     * @throws StakeIdNotFoundException
     */
    private UserStakeDTO getUserStakeByStakeId(int stakeId) throws StakeIdNotFoundException {
        Set<StakeDetail> stakeDetails =
                stakeDetailRepository.findByUserModel_UserIdAndStake_Id(user.getUserId(), memoryLastStakeId);
        return getUserStakeByStakeId(stakeId, stakeDetails);
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
                .stackId(lastStake.getId())
                .status(lastStake.getStatus())
                .title(lastStake.getTitle())
                .player(userStakePlayerDTOs)
                .build();
    }

    private void addUserStakePoint(List<UserStakePlayerDTO> userStakePlayerDTOs, Set<StakeDetail> stakeDetails) {
        // 加入下注資訊
        if (!stakeDetails.isEmpty()) {
            stakeDetails.forEach(x ->
                    userStakePlayerDTOs.stream().filter(userStakePlayerDTO ->
                                    userStakePlayerDTO.getPlayerId().equals(x.getStakePlayer().getPlayerId()))
                            .findFirst().ifPresent(existUserStakePlayerDTO -> existUserStakePlayerDTO.setPoint(x.getStakePoint()))
            );
        }
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
        if (userStakeDTOList.stream().mapToInt(x -> x.getPoint()).sum() > user.getUserPoint()) {
            throw new UserPointNotEnoughException();
        }

        List<StakeDetail> saveStakeDetails = new ArrayList<>();
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
                    .stakeTime(new Date())
                    .build());
        }

        saveStakeDetails.forEach(x -> stakeDetailRepository.save(x));
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
