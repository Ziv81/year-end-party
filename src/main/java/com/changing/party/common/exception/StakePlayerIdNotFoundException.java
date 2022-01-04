package com.changing.party.common.exception;

public class StakePlayerIdNotFoundException extends Exception {
    public StakePlayerIdNotFoundException(Integer playerId) {
        super(String.valueOf(playerId));
    }
}
