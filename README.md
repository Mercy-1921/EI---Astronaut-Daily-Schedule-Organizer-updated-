# Astronaut Daily Scheduler

A simple yet professional Java application for managing astronaut daily tasks with automatic conflict detection.

## Features

- ✅ Add new tasks with description, start time, end time, and priority  
- ✅ Remove existing tasks  
- ✅ View all tasks sorted by start time  
- ✅ Edit existing tasks  
- ✅ Mark tasks as completed  
- ✅ Filter tasks by priority level  
- ✅ Automatic conflict detection for overlapping tasks  
- ✅ Input validation and detailed error messages  
- ✅ Logging of all operations to file and console  


## Requirements

- Java 11 or higher
- Works directly in **VS Code**, **CMD**, or **PowerShell**

## Project Structure

```
astronaut-daily-scheduler/
└── com/
    └── astronaut/
        └── scheduler/
            ├── model/
            │   ├── Task.java
            │   └── Priority.java
            ├── factory/
            │   └── TaskFactory.java
            ├── service/
            │   ├── ScheduleManager.java
            │   ├── ConflictObserver.java
            │   └── UserNotifier.java
            ├── util/
            │   ├── Logger.java
            │   └── ValidationUtils.java
            ├── exception/
            │   ├── SchedulerException.java
            │   └── ValidationException.java
            └── Main.java


```

## 🚀 How to Compile and Run

### 🧩 Step 1 — Open Terminal in Project Folder
In VS Code or Windows PowerShell, navigate to your project root.

### 🧩 Step 2 — Compile the Source Files

javac com\astronaut\scheduler\model\*.java com\astronaut\scheduler\factory\*.java com\astronaut\scheduler\service\*.java com\astronaut\scheduler\util\*.java com\astronaut\scheduler\exception\*.java com\astronaut\scheduler\Main.java

### 🧩 Step 2 — Run the Application

java com.astronaut.scheduler.Main

## Usage

Once the application starts, you'll see a menu with the following options:

```
=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option:
```
## Test Cases

### Adding a Task

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 1
Description: Morning Exercise
Start time (HH:mm): 07:00
End time (HH:mm): 08:00
Priority (High/Medium/Low): High
✅ Task added successfully. No conflicts.


=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 1
Description: Team Meeting
Start time (HH:mm): 09:00
End time (HH:mm): 10:00
Priority (High/Medium/Low): Medium
✅ Task added successfully. No conflicts.

**Note:** The application will automatically detect if the new task conflicts with existing tasks.

### View All Tasks

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 3

=== All Tasks ===
07:00 - 08:00: Morning Exercise [High]
09:00 - 10:00: Team Meeting [Medium]

### Remove an Existing Task

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 2
Description of task to remove: Morning Exercise
✅ Task removed successfully.

### Add a Valid Task After Removal 

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 1
Description: Lunch Break
Start time (HH:mm): 12:00
End time (HH:mm): 13:00
Priority (High/Medium/Low): Low
✅ Task added successfully. No conflicts.

### Edit an Existing Task

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 4
Description of task to edit: Team Meeting
New description: Updated Team Meeting
New start time (HH:mm): 09:15
New end time (HH:mm): 10:15
New priority (High/Medium/Low): High
✅ Task updated successfully.

### Mark a Task as Completed

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 5
Description of task to mark completed: Lunch Break
✅ Task marked as completed.

### View Tasks by Priority
=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 6
Enter priority (High/Medium/Low): High

=== Tasks with Priority: High ===
09:15 - 10:15: Updated Team Meeting [High] (Completed)

### Add a Task That Conflicts with Existing Task

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 1
Description: Training Session
Start time (HH:mm): 09:30
End time (HH:mm): 10:30
Priority (High/Medium/Low): High
⚠️  Error: Task conflicts with existing task "Updated Team Meeting".
❌ Error: Task conflicts with existing task "Updated Team Meeting".

### Remove a Non-Existent Task

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 2
Description of task to remove: Non-existent Task
❌ Error: Task not found.

### Add a Task with Invalid Time Format

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 1
Description: Invalid Time Task
Start time (HH:mm): 25:00
End time (HH:mm): 26:00
Priority (High/Medium/Low): Low
❌ Error: Invalid time format. Use HH:mm (00:00 - 23:59)

### Edit a Non-Existent Task

=== Astronaut Daily Schedule Organizer ===
1) Add Task
2) Remove Task
3) View All Tasks
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
Choose an option: 4
Description of task to edit: Non-existent Task
New description: New Task
New start time (HH:mm): 11:00
New end time (HH:mm): 12:00
New priority (High/Medium/Low): Medium
❌ Error: Task not found.

## Design Patterns Used

1. **Singleton Pattern**: `ScheduleManager` ensures only one instance manages all tasks
2. **Observer Pattern**: `ConflictObserver` interface and `UserNotifier` implementation for notifications
3. **Factory Pattern**: `TaskFactory` creates `Task` objects with validation

## Logging

The application logs all operations to `astronaut_schedule.log` file including:
- Task additions
- Task removals
- Task modifications
- Conflict detections
- Errors and warnings

## Error Handling

- Input validation for time format and priority
- Conflict detection prevents overlapping tasks
- Clear error messages for user guidance
- Comprehensive logging for debugging

## Code Quality

- Follows SOLID principles and best practices of object-oriented programming.
- Clean and modular code, with each class placed in its own file.
- Comprehensive logging and robust error handling for reliable performance.
