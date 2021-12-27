package com.changing.party.exception;

public class AnswerBinaryNotOpenException extends RuntimeException {
    public AnswerBinaryNotOpenException() {
        super("Answer status is not open.");
    }
}
