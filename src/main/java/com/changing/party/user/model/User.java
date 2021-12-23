package com.changing.party.user.model;


import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
@Getter
public class User {
    private final String title;
    private final String userName;
    private final Long userId;
    private final int userPoint;
    private int userRank;

    public static User getUserModel(UserModel user) {
        return User.builder()
                .title(user.getJobTitle())
                .userName(user.getEnglishName())
                .userId(user.getUserId())
                .userPoint(user.getUserPoint())
                .build();
    }

}
