package com.astronaut.scheduler.factory;

import com.astronaut.scheduler.model.Task;
import com.astronaut.scheduler.model.Priority;
import com.astronaut.scheduler.util.ValidationUtils;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Factory Pattern implementation for creating validated Task objects.
 */
public class TaskFactory {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static Task createTask(String description, String start, String end, String priorityStr) {
        try {
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Error: Description cannot be empty.");
            }

            // Validate time format before parsing
            if (!ValidationUtils.isTimeFormatValid(start) || !ValidationUtils.isTimeFormatValid(end)) {
                throw new IllegalArgumentException("Error: Invalid time format. Use HH:mm (00:00 - 23:59)");
            }

            LocalTime startTime = LocalTime.parse(start, TIME_FORMAT);
            LocalTime endTime = LocalTime.parse(end, TIME_FORMAT);

            if (!endTime.isAfter(startTime)) {
                throw new IllegalArgumentException("Error: End time must be after start time.");
            }

            Priority priority = Priority.valueOf(priorityStr.toUpperCase());
            return new Task(description, startTime, endTime, priority);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error: Invalid time format. Use HH:mm (00:00 - 23:59)");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("No enum constant")) {
                throw new IllegalArgumentException("Error: Invalid priority. Use High, Medium, or Low.");
            }
            throw e;
        }
    }
}
