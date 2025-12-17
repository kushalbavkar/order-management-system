package com.kb.workflow.exceptions;

public class WorkflowException extends RuntimeException {
    public WorkflowException(final String message) {
        super(message);
    }

    public WorkflowException(final String message, Throwable ex) {
        super(message, ex);
    }
}
