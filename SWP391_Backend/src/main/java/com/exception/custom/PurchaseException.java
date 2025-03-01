package com.exception.custom;

public class PurchaseException extends RuntimeException {
    public PurchaseException(String message) {
        super(message);
    }
}
