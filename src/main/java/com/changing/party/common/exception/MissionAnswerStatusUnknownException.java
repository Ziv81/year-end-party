package com.changing.party.common.exception;

public class MissionAnswerStatusUnknownException extends Throwable {

    public MissionAnswerStatusUnknownException(Integer message) {
        super(String.valueOf(message));
    }
}
