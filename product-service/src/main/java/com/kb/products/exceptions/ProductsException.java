package com.kb.products.exceptions;

public class ProductsException extends RuntimeException {
    public ProductsException(final String message) {
        super(message);
    }

    public ProductsException(final String message, Throwable ex) {
        super(message, ex);
    }
}
