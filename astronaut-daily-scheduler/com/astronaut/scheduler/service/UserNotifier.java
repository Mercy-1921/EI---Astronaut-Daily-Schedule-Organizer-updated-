package com.astronaut.scheduler.service;

/**
 * Console-based observer implementation.
 * Used to notify the user of conflicts.
 */
public class UserNotifier implements ConflictObserver {
    @Override
    public void notifyConflict(String conflictMessage) {
        System.out.println("⚠️  " + conflictMessage);
    }
}
