package com.kb.products.exceptions;

import com.kb.common.error.ApiErrorResponse;
import com.kb.common.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class ProductsControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(ValidationException ex) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error("Request Validation Exception - Status code: {}, details: {}", status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(buildErrorResponse(status, ex.getMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        final HttpStatusCode status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        log.error("Unsupported MediaType Exception - Status code: {}, details: {}", status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(buildErrorResponse(HttpStatus.valueOf(status.value()), "Content-Type must be application/json"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleUnreadableMessage(HttpMessageNotReadableException ex) {
        final HttpStatusCode status = HttpStatus.BAD_REQUEST;
        log.error("HTTP Message Unreadable Exception - Status code: {}, details: {}", status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(buildErrorResponse(HttpStatus.valueOf(status.value()), "Request body is missing or malformed"));
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingPathVariable(MissingPathVariableException ex) {
        final HttpStatusCode status = HttpStatus.BAD_REQUEST;
        log.error("Missing PathVariable Exception - Status code: {}, details: {}", status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(buildErrorResponse(HttpStatus.valueOf(status.value()), ex.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnsupportedMethod(HttpRequestMethodNotSupportedException ex) {
        final HttpStatusCode status = HttpStatus.METHOD_NOT_ALLOWED;
        log.error("HTTP Method Unsupported Exception - Status code: {}, details: {}", status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(buildErrorResponse(HttpStatus.valueOf(status.value()), ex.getMessage()));
    }

    @ExceptionHandler(ProductsException.class)
    public ResponseEntity<ApiErrorResponse> handleServiceFailures(ProductsException ex) {
        final HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Service Exception - Status code: {}, details: {}", status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(buildErrorResponse(HttpStatus.valueOf(status.value()), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleServiceFailures(Exception ex) {
        final HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Unexpected Service Exception - Status code: {}, details: {}", status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(buildErrorResponse(HttpStatus.valueOf(status.value()), "Internal Server Error"));
    }

    private static ApiErrorResponse buildErrorResponse(final HttpStatus status, final String message) {
        return new ApiErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                Instant.now()
        );
    }
}
