package com.changing.party.exception;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(int userId) {
        super(String.format("User id %s not found", userId));
    }
}
