package com.changing.party.common.exception;

public class MissionIDNotFoundException extends Throwable {
    public MissionIDNotFoundException(Integer missionId) {
        super(String.valueOf(missionId));
    }
}
