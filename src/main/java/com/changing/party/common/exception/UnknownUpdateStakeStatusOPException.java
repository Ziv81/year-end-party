package com.changing.party.common.exception;

public class UnknownUpdateStakeStatusOPException extends Exception {
    public UnknownUpdateStakeStatusOPException(String op) {
        super(op);
    }
}
