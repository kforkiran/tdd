package com.kiran.tdd.exceptions;

public class OrderDetailsNotFoundException extends RuntimeException {

    public OrderDetailsNotFoundException() {
    }

    public OrderDetailsNotFoundException(String message) {
        super(message);
    }
}
