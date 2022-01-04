package com.changing.party.service;

import com.changing.party.common.enums.StakeStatus;
import com.changing.party.common.exception.AlreadyOneStackISOpenException;
import com.changing.party.common.exception.StakeIdNotFoundException;
import com.changing.party.common.exception.StakeIsNotOpenException;
import com.changing.party.dto.StakeDTO;
import com.changing.party.model.Stake;
import com.changing.party.model.StakePlayer;
import com.changing.party.repository.StakeDetailRepository;
import com.changing.party.repository.StakePlayerRepository;
import com.changing.party.repository.StakeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
public class StakeService {

    StakeRepository stakeRepository;
    StakeDetailRepository stakeDetailRepository;
    StakePlayerRepository stakePlayerRepository;
    UserService userService;

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
    }

    public void stopStake(Integer data) throws StakeIdNotFoundException, StakeIsNotOpenException {
        Stake stake = stakeRepository.findById(data)
                .orElseThrow(() -> new StakeIdNotFoundException(data));

        if (stake.getStatus() != StakeStatus.OPEN)
            throw new StakeIsNotOpenException(stake.getId(), stake.getTitle());

        stake.setStatus(StakeStatus.CLOSE);
        stakeRepository.save(stake);
    }
}
