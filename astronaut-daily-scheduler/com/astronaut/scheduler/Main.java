package com.astronaut.scheduler;

import com.astronaut.scheduler.service.*;
import com.astronaut.scheduler.model.*;
import com.astronaut.scheduler.factory.TaskFactory;
import com.astronaut.scheduler.util.Logger;

import java.util.Scanner;

/**
 * Console-based entry point for Astronaut Daily Schedule Organizer.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ScheduleManager manager = ScheduleManager.getInstance();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Logger.info("Application started");
        manager.addObserver(new UserNotifier());

        while (isRunning) {
            displayMenu();
            handleMenuChoice();
        }

        scanner.close();
        Logger.info("Application terminated");
    }

    private static void displayMenu() {
        System.out.println("\n=== Astronaut Daily Schedule Organizer ===");
        System.out.println("1) Add Task");
        System.out.println("2) Remove Task");
        System.out.println("3) View All Tasks");
        System.out.println("4) Edit Task");
        System.out.println("5) Mark Task Completed");
        System.out.println("6) View Tasks by Priority");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
    }

    private static void handleMenuChoice() {
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1" -> addTask();
            case "2" -> removeTask();
            case "3" -> manager.viewTasks();
            case "4" -> editTask();
            case "5" -> markTaskCompleted();
            case "6" -> viewTasksByPriority();
            case "0" -> {
                isRunning = false;
                System.out.println("\n✅ Exiting. Goodbye!");
            }
            default -> System.out.println("❌ Invalid choice. Please try again.");
        }
    }

    private static void addTask() {
        try {
            System.out.print("Description: ");
            String desc = scanner.nextLine().trim();
            System.out.print("Start time (HH:mm): ");
            String start = scanner.nextLine().trim();
            System.out.print("End time (HH:mm): ");
            String end = scanner.nextLine().trim();
            System.out.print("Priority (High/Medium/Low): ");
            String priority = scanner.nextLine().trim();

            Task newTask = TaskFactory.createTask(desc, start, end, priority);
            manager.addTask(newTask);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void removeTask() {
        System.out.print("Description of task to remove: ");
        String desc = scanner.nextLine().trim();
        manager.removeTask(desc);
    }

    private static void editTask() {
        try {
            System.out.print("Description of task to edit: ");
            String oldDesc = scanner.nextLine().trim();
            System.out.print("New description: ");
            String newDesc = scanner.nextLine().trim();
            System.out.print("New start time (HH:mm): ");
            String newStart = scanner.nextLine().trim();
            System.out.print("New end time (HH:mm): ");
            String newEnd = scanner.nextLine().trim();
            System.out.print("New priority (High/Medium/Low): ");
            String newPriority = scanner.nextLine().trim();

            Task updatedTask = TaskFactory.createTask(newDesc, newStart, newEnd, newPriority);
            manager.editTask(oldDesc, updatedTask);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void markTaskCompleted() {
        System.out.print("Description of task to mark completed: ");
        String desc = scanner.nextLine().trim();
        manager.markTaskCompleted(desc);
    }

    private static void viewTasksByPriority() {
        System.out.print("Enter priority (High/Medium/Low): ");
        String priStr = scanner.nextLine().trim();
        try {
            Priority pri = Priority.valueOf(priStr.toUpperCase());
            manager.viewTasksByPriority(pri);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid priority. Use High, Medium, or Low.");
        }
    }
}
