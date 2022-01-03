package com.changing.party.common.exception;

public class MissionAnswerStatusUnknownException extends Exception {

    public MissionAnswerStatusUnknownException(Integer message) {
        super(String.valueOf(message));
    }
}
