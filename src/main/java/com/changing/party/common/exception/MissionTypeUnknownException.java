package com.changing.party.common.exception;

public class MissionTypeUnknownException extends Exception {
    public MissionTypeUnknownException(int unknownMissionType) {
        super(String.valueOf(unknownMissionType));
    }
}
