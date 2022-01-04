package com.changing.party.response;

import com.changing.party.dto.UserStakeDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Builder
@Log4j2
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStakeResponse {
    Integer status;
    Integer stackId;
    String title;
    List<UserStakePlayerResponse> player;

    public static UserStakeResponse getUserStakeResponse(UserStakeDTO userStakeDTO) {
        if (userStakeDTO == null)
            return null;
        List<UserStakePlayerResponse> player = new ArrayList<>();
        userStakeDTO.getPlayer().forEach(x -> player.add(UserStakePlayerResponse.getUserStakePlayerResponse(x)));
        return UserStakeResponse.builder()
                .status(userStakeDTO.getStatus().getStatus())
                .stackId(userStakeDTO.getStackId())
                .title(userStakeDTO.getTitle())
                .player(player)
                .build();
    }
}
