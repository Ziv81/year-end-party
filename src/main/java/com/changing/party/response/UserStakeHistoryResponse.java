package com.changing.party.response;

import com.changing.party.dto.UserStakeRoundDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStakeHistoryResponse {
    Integer stakeId;
    String title;
    Long createTime;
    Integer winner;
    Integer winPoint;
    List<UserStakePlayerResponse> player;

    public static UserStakeHistoryResponse getUserStakeHistoryResponse(UserStakeRoundDTO userStakeRoundDTO) {
        List<UserStakePlayerResponse> userStakePlayerResponses = new ArrayList<>();
        userStakeRoundDTO.getPlayer().forEach(x -> userStakePlayerResponses.add(UserStakePlayerResponse.getUserStakePlayerResponse(x)));
        return UserStakeHistoryResponse.builder()
                .stakeId(userStakeRoundDTO.getStackId())
                .title(userStakeRoundDTO.getTitle())
                .createTime(userStakeRoundDTO.getCreateTime().getTime())
                .winner(userStakeRoundDTO.getWinner())
                .winPoint(userStakeRoundDTO.getWinPoint())
                .player(userStakePlayerResponses)
                .build();
    }
}
