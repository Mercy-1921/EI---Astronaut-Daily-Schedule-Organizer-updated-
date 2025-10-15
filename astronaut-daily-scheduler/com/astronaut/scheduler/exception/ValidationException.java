package com.astronaut.scheduler.exception;

/**
 * Custom exception for validation-related errors.
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
