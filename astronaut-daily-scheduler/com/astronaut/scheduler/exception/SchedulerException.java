package com.astronaut.scheduler.exception;

/**
 * Base exception for scheduler-related errors.
 */
public class SchedulerException extends Exception {
    public SchedulerException(String message) {
        super(message);
    }
}
