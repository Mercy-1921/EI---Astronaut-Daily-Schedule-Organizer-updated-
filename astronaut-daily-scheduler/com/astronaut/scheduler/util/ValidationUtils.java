package com.astronaut.scheduler.util;

import com.astronaut.scheduler.model.Priority;

/**
 * Utility class for common input validation methods.
 */
public final class ValidationUtils {

    private ValidationUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static boolean isPriorityValid(String priority) {
        try {
            Priority.valueOf(priority.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isTimeFormatValid(String time) {
        return time != null && time.matches("^([01]?\\d|2[0-3]):[0-5]\\d$");
    }
}
