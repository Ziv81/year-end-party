package com.changing.party.common.exception;

public class UnknownImageFormatException extends Throwable {
    public UnknownImageFormatException(String base64File) {
        super(base64File);
    }
}
