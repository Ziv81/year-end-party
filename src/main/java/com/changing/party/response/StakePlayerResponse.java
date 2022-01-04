package com.changing.party.response;

import com.changing.party.dto.StakePlayerDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StakePlayerResponse {
    Integer playerId;
    String playerName;

    public static StakePlayerResponse getStakePlayerResponse(StakePlayerDTO stakePlayerDTO) {
        return StakePlayerResponse.builder()
                .playerId(stakePlayerDTO.getPlayerId())
                .playerName(stakePlayerDTO.getPlayerName())
                .build();
    }
}
