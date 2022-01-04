package com.changing.party.common.exception;

public class StakeIsNotCloseException extends Exception {
    public StakeIsNotCloseException(Integer id, String title) {
        super(String.format("%s-%s", id, title));
    }
}
