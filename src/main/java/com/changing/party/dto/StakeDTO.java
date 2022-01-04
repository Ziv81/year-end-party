package com.changing.party.dto;

import com.changing.party.common.enums.StakeStatus;
import com.changing.party.model.Stake;
import com.changing.party.request.CreateStakeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StakeDTO {
    private Integer id;
    private StakeStatus status;
    private String title;
    private List<StakePlayerDTO> stakePlayer;
    private Integer winnerId;

    public static StakeDTO getStakeDTO(Stake stake) {
        List<StakePlayerDTO> stakePlayerDTOS = new ArrayList<>();
        stake.getStakePlayers().forEach(x -> stakePlayerDTOS.add(StakePlayerDTO.getStakePlayerDTO(x)));
        return new StakeDTO(stake.getId(), stake.getStatus(), stake.getTitle(), stakePlayerDTOS, stake.getWinnerId());
    }

    public static StakeDTO getStakeDTO(CreateStakeRequest stakeRequest) {
        List<StakePlayerDTO> stakePlayerDTOS = new ArrayList<>();
        stakeRequest.getPlayer().forEach(x -> stakePlayerDTOS.add(StakePlayerDTO.getStakePlayerDTO(x)));
        return StakeDTO.builder()
                .title(stakeRequest.getTitle())
                .stakePlayer(stakePlayerDTOS)
                .build();
    }
}
