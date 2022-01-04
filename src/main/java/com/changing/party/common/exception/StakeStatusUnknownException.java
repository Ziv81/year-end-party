package com.changing.party.common.exception;

public class StakeStatusUnknownException extends Exception {
    public StakeStatusUnknownException(Integer message) {
        super(String.valueOf(message));
    }
}
