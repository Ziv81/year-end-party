package com.changing.party.exception;

public class UserIdNotFoundException extends RuntimeException {
    private String message;

    public UserIdNotFoundException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
