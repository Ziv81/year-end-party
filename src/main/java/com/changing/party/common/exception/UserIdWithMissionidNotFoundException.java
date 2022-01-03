package com.changing.party.common.exception;

public class UserIdWithMissionidNotFoundException extends Exception {
    public UserIdWithMissionidNotFoundException(Integer userId, Integer missionId) {
        super(String.format("%s-%s", String.valueOf(userId), String.valueOf(missionId)));
    }
}
