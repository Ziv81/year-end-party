package com.changing.party.dto;

import com.changing.party.model.StakePlayer;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStakePlayerDTO {
    private String playerName;
    private Integer playerId;
    private Integer point;

    public static UserStakePlayerDTO getUserStakePlayerDTO(StakePlayer stakePlayer) {
        return UserStakePlayerDTO.builder()
                .playerId(stakePlayer.getPlayerId())
                .playerName(stakePlayer.getPlayerName())
                .build();
    }
}
