package com.changing.party.dto;

import com.changing.party.model.StakePlayer;
import com.changing.party.request.StakePlayerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StakePlayerDTO {
    private Integer id;
    private String playerName;
    private Integer playerId;

    public static StakePlayerDTO getStakePlayerDTO(StakePlayer stakePlayer) {
        return new StakePlayerDTO(stakePlayer.getId(), stakePlayer.getPlayerName(), stakePlayer.getPlayerId());
    }

    public static StakePlayerDTO getStakePlayerDTO(StakePlayerRequest stakePlayer) {
        return StakePlayerDTO.builder()
                .playerId(stakePlayer.getPlayerId())
                .playerName(stakePlayer.getPlayerName())
                .build();
    }
}
