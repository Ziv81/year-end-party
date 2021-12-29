package com.changing.party.user.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private final String title;
    private final String userName;
    private final Integer userId;
    private final int userPoint;
    private int userRank;
    private int isCheckIn;
    private int isAgree;

    public static User getUserModel(UserModel user, int userRank) {
        return User.builder()
                .title(user.getJobTitle())
                .userName(user.getEnglishName())
                .userId(user.getUserId().intValue())
                .userPoint(user.getUserPoint())
                .userRank(userRank)
                .isCheckIn(user.getIsCheckIn())
                .isAgree(user.getIsAgree())
                .build();
    }

}
