package com.changing.party.common.exception;

public class StakeIdNotFoundException extends Exception {
    public StakeIdNotFoundException(Integer data) {
        super(String.valueOf(data));
    }
}
