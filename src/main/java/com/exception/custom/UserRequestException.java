package com.exception.custom;

public class UserRequestException extends Exception {
    public UserRequestException(String message) {
        super(message);
    }
}
