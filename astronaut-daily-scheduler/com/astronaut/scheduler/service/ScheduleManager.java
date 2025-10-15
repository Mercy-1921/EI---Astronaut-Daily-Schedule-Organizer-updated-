package com.astronaut.scheduler.service;

import com.astronaut.scheduler.model.Task;
import com.astronaut.scheduler.model.Priority;
import com.astronaut.scheduler.util.Logger;

import java.util.*;

/**
 * Singleton class that manages all astronaut tasks.
 * Implements Observer pattern for conflict notifications.
 */
public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<ConflictObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
            Logger.info("ScheduleManager instance created");
        }
        return instance;
    }

    public void addObserver(ConflictObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (ConflictObserver obs : observers) {
            obs.notifyConflict(message);
        }
    }

    // ================== CRUD Operations ==================

    public boolean addTask(Task task) {
        for (Task t : tasks) {
            if (isConflict(task, t)) {
                String msg = "Error: Task conflicts with existing task \"" + t.getDescription() + "\".";
                notifyObservers(msg);
                Logger.warning(msg);
                return false;
            }
        }
        tasks.add(task);
        System.out.println("✅ Task added successfully. No conflicts.");
        Logger.info("Task added: " + task.getDescription());
        return true;
    }

    public boolean removeTask(String description) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (t.getDescription().equalsIgnoreCase(description)) {
                it.remove();
                System.out.println("✅ Task removed successfully.");
                Logger.info("Task removed: " + description);
                return true;
            }
        }
        System.out.println("❌ Error: Task not found.");
        Logger.warning("Task not found for removal: " + description);
        return false;
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        tasks.sort(Comparator.comparing(Task::getStartTime));
        System.out.println("\n=== All Tasks ===");
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    public void editTask(String oldDesc, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().equalsIgnoreCase(oldDesc)) {
                tasks.set(i, updatedTask);
                System.out.println("✅ Task updated successfully.");
                Logger.info("Task updated: " + oldDesc);
                return;
            }
        }
        System.out.println("❌ Error: Task not found.");
    }

    public void markTaskCompleted(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                t.setCompleted(true);
                System.out.println("✅ Task marked as completed.");
                Logger.info("Task completed: " + description);
                return;
            }
        }
        System.out.println("❌ Error: Task not found.");
    }

    public void viewTasksByPriority(Priority priority) {
        List<Task> filtered = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority() == priority) {
                filtered.add(t);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No tasks with priority " + priority.getDisplayName() + ".");
            return;
        }

        filtered.sort(Comparator.comparing(Task::getStartTime));
        System.out.println("\n=== Tasks with Priority: " + priority.getDisplayName() + " ===");
        for (Task t : filtered) {
            System.out.println(t);
        }
    }

    // ================== Conflict Logic ==================
    private boolean isConflict(Task a, Task b) {
        return a.getStartTime().isBefore(b.getEndTime()) &&
               a.getEndTime().isAfter(b.getStartTime());
    }
}
