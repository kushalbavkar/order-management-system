package com.kb.users.exceptions;

public class UsersException extends RuntimeException {
    public UsersException(final String message) {
        super(message);
    }

    public UsersException(final String message, Throwable ex) {
        super(message, ex);
    }
}
