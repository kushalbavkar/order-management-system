package com.kb.workflow.exceptions;

import com.kb.common.error.ApiErrorResponse;

public class WorkflowException extends RuntimeException {
    public WorkflowException() {
        super();
    }

    public WorkflowException(final String message) {
        super(message);
    }

    public WorkflowException(final String message, Throwable ex) {
        super(message, ex);
    }
}
