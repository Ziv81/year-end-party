package com.changing.party.common.exception;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(int userId) {
        super(String.format("User id %s not found", userId));
    }
}
