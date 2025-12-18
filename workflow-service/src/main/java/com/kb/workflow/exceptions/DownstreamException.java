package com.kb.workflow.exceptions;

import com.kb.common.error.ApiErrorResponse;

public class DownstreamException extends WorkflowException {
    public DownstreamException(String message) {
        super(message);
    }
}
