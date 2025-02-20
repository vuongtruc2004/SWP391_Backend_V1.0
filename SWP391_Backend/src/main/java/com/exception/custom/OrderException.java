package com.exception.custom;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
