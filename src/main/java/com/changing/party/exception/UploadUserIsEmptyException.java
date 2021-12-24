package com.changing.party.exception;

public class UploadUserIsEmptyException extends RuntimeException {

    public UploadUserIsEmptyException() {
        super("Upload user list is empty. Check request");
    }
}
