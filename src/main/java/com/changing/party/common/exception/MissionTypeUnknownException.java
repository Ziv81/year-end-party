package com.changing.party.common.exception;

public class MissionTypeUnknownException extends Throwable {
    public MissionTypeUnknownException(int unknownMissionType) {
        super(String.valueOf(unknownMissionType));
    }
}
