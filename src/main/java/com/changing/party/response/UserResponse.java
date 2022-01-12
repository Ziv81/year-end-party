package com.changing.party.response;


import com.changing.party.dto.UserModelDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private final String title;
    private final String userName;
    private final Integer userId;
    private final int userPoint;
    private int userRank;
    private Integer isCheckIn;
    private Integer isAgree;

    public static UserResponse getUserModel(UserModelDTO user) {
        return UserResponse.builder()
                .title(String.format("%s %s", user.getDepartment().trim(), user.getJobTitle().trim()))
                .userName(user.getEnglishName())
                .userId(user.getUserId().intValue())
                .userPoint(user.getUserPoint())
                .userRank(user.getRank())
                .isCheckIn(user.getIsCheckIn())
                .isAgree(user.getIsAgree())
                .build();
    }

}
