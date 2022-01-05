package com.changing.party.response;

import com.changing.party.dto.UserStakeDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStakeResponse {
    Integer status;
    Integer stakeId;
    String title;
    List<UserStakePlayerResponse> player;

    public static UserStakeResponse getUserStakeResponse(UserStakeDTO userStakeDTO) {
        if (userStakeDTO == null)
            return null;
        List<UserStakePlayerResponse> player = new ArrayList<>();
        userStakeDTO.getPlayer().forEach(x -> player.add(UserStakePlayerResponse.getUserStakePlayerResponse(x)));
        return UserStakeResponse.builder()
                .status(userStakeDTO.getStatus().getStatus())
                .stakeId(userStakeDTO.getStakeId())
                .title(userStakeDTO.getTitle())
                .player(player)
                .build();
    }
}
