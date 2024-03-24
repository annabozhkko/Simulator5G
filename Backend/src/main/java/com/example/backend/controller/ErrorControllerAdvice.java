package com.example.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class ErrorControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        LOGGER.error("Error: " + exception + "\n" + Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    public record ErrorResponse(LocalDateTime timestamp, String message, int code) {}

    protected ErrorResponse body(String message, Integer code) {
        return new ErrorResponse(LocalDateTime.now(), message, code);
    }
}
