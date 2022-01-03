package com.changing.party.common.exception;

public class ImageStatusNotReview extends Exception {
    public ImageStatusNotReview(Integer imageId) {
        super(String.valueOf(imageId));
    }
}
