package com.changing.party.response;

import com.changing.party.dto.UserStakePlayerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStakePlayerResponse {
    Integer playerId;
    String playerName;
    Integer point;

    public static UserStakePlayerResponse getUserStakePlayerResponse(UserStakePlayerDTO userStakePlayerDTO){
        return UserStakePlayerResponse.builder()
                .playerId(userStakePlayerDTO.getPlayerId())
                .playerName(userStakePlayerDTO.getPlayerName())
                .point(userStakePlayerDTO.getPoint())
                .build();
    }
}
