package com.exception.custom;

public class InvalidRequestInput extends RuntimeException {
    public InvalidRequestInput(String message) {
        super(message);
    }
}
