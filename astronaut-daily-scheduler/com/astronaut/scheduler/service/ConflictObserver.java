package com.astronaut.scheduler.service;

/**
 * Observer interface for task conflict notifications.
 */
public interface ConflictObserver {
    void notifyConflict(String conflictMessage);
}
