package com.exception.custom;

public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }
}
