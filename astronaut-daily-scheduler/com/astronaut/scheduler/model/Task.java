package com.astronaut.scheduler.model;

import java.time.LocalTime;

/**
 * Represents a single task with description, time range, and priority.
 * Follows encapsulation and immutability principles.
 */
public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;
    private boolean completed;

    public Task(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String base = String.format("%s - %s: %s [%s]",
                startTime, endTime, description, priority.getDisplayName());
        return completed ? base + " (Completed)" : base;
    }
}
