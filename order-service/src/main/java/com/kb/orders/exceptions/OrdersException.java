package com.kb.orders.exceptions;

public class OrdersException extends RuntimeException {
    public OrdersException(final String message) {
        super(message);
    }

    public OrdersException(final String message, Throwable ex) {
        super(message, ex);
    }
}
