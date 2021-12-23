package com.changing.party.exception;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(Long userId) {
        super(String.format("User id %s not found", userId));
    }
}
