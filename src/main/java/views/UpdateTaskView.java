package views;

import models.AbstractTaskList;
import models.Task;
import util.ReadInputUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateTaskView implements UpdateTaskViewTemplate {

    public UpdateTaskView() {
        printStartInfo();
    }

    public void printStartInfo() {
        System.out.println("\t Update task:");
    }

    public void printList(String taskList) {
        System.out.println(taskList);
    }

    public void printUpdateInfo() {
        System.out.println("Choose task:");
        System.out.println("Input task number:");
        System.out.println("\t Update task:");
    }

    public void printUpdateInfoForRepeatTask() {
        System.out.println("1. Update title");
        System.out.println("2. Update start time");
        System.out.println("3. Update end time");
        System.out.println("4. Update interval");
        System.out.println("5. Change active");
        System.out.println("6. Change to single time task");
    }

    public void printUpdateInfoForNoRepeatTask() {
        System.out.println("1. Update title");
        System.out.println("2. Update time");
        System.out.println("3. Change active");
        System.out.println("4. Change to repeat task");
    }

    public int readChoosingTask(int from, int to) {
        int index = ReadInputUtil.readIntFromInput(from, to);
        return index;
    }

    public void updateTaskData(Task task) {
        if (task.isRepeated()) {
            printUpdateInfoForRepeatTask();
            readNewRepeatTaskData(task);
        } else {
            printUpdateInfoForNoRepeatTask();
            readNewNoRepeatTaskData(task);
        }
    }

    public void readNewRepeatTaskData(Task task) {
        int index = ReadInputUtil.readIntFromInput(1, 6);
        switch (index) {
            case 1:
                String newTitle = readNewTaskTitle();
                task.setTitle(newTitle);
                break;
            case 2:
                LocalDateTime dateTime = readNewDate();
                task.setTime(dateTime, task.getEndTime(), task.getRepeatInterval());
                break;
            case 3:
                LocalDateTime newEndTime = readNewEndTime(task.getStartTime());
                task.setTime(task.getStartTime(), newEndTime, task.getRepeatInterval());
                break;
            case 4:
                int interval = readNewIntervalInMinutes() * 60;
                task.setTime(task.getStartTime(), task.getEndTime(), interval);
                break;
            case 5:
                boolean isActive = changeActivity();
                task.setActive(isActive);
                break;
            case 6:
                changeToNoRepeat(task);
                break;
            default:
                break;
        }
    }

    public void readNewNoRepeatTaskData(Task task) {
        int index = ReadInputUtil.readIntFromInput(1, 4);
        switch (index) {
            case 1:
                String newTitle = readNewTaskTitle();
                task.setTitle(newTitle);
                break;
            case 2:
                LocalDateTime dateTime = readNewDate();
                task.setTime(dateTime);
                break;
            case 3:
                boolean isActive = changeActivity();
                task.setActive(isActive);
                break;
            case 4:
                changeToRepeat(task);
                break;
            default:
                break;
        }
    }

    public void changeToRepeat(Task task) {
        LocalDateTime dateTime = readNewDate();
        LocalDateTime newEndTime = readNewEndTime(dateTime);
        int interval = readNewIntervalInMinutes() * 60;
        task.setTime(dateTime, newEndTime, interval);
    }

    public void changeToNoRepeat(Task task) {
        LocalDateTime time = readNewDate();
        task.setTime(time);
    }

    public boolean changeActivity() {
        System.out.println("Write 1 to set active, 0 to set passive");
        while (true) {
            int item = ReadInputUtil.readIntFromInput(0, 1);
            switch (item) {
                case 0:
                    return false;
                case 1:
                    return true;
            }
        }
    }

    public String readNewTaskTitle() {
        System.out.print("Write task title:\n>:");
        String title = ReadInputUtil.readStringFromInput();
        return title;
    }

    public LocalDateTime readNewDate() {
        System.out.print("\nWrite time in format 'yyyy-MM-dd HH:mm' for example 2016-11-09 11:44\n>:");
        String taskTime = ReadInputUtil.readDateString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(taskTime, formatter);
        return time;
    }

    public LocalDateTime readNewEndTime(LocalDateTime timeStart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeEnd;
        while (true) {
            System.out.print("\nWrite end time in format 'yyyy-MM-dd HH:mm' for example 2016-11-09 11:44\n>:");
            String taskTime = ReadInputUtil.readDateString();
            timeEnd = LocalDateTime.parse(taskTime, formatter);
            if (timeEnd.isAfter(timeStart)) {
                return timeEnd;
            } else {
                System.out.println("Time must be after start of task");
            }
        }
    }

    public int readNewIntervalInMinutes() {
        return ReadInputUtil.readIntFromInput(0, 1000);
    }


}
