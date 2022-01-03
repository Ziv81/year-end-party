package com.changing.party.common.exception;

public class MissionIDNotFoundException extends Exception {
    public MissionIDNotFoundException(Integer missionId) {
        super(String.valueOf(missionId));
    }
}
