package com.changing.party.common.exception;

public class ImageIdNotFoundException extends Exception {
    public ImageIdNotFoundException(Integer imageId) {
        super(String.valueOf(imageId));
    }
}
