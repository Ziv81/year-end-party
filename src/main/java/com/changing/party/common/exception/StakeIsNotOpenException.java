package com.changing.party.common.exception;

public class StakeIsNotOpenException extends Exception {
    public StakeIsNotOpenException(Integer stakeId, String stakeTitle) {
        super(String.format("%s-%s", stakeId, stakeTitle));
    }
}
