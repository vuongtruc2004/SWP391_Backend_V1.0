package com.exception.custom;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
