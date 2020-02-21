package views;

import models.AbstractTaskList;
import models.Task;
import util.ReadInputUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateTaskView implements UpdateTaskViewTemplate{

    public UpdateTaskView() {
        printStartInfo();
    }

    public void printStartInfo() {
        System.out.println("\t Update task:");
    }

    public void printList(AbstractTaskList abstractTaskList) {
        int i = 0;
        for (Task task: abstractTaskList) {
            i++;
            System.out.println(i + " " + task);
        }
    }

    public void printUpdateInfo() {
        System.out.println("Choose task:");
        System.out.println("Input task number:");
        System.out.println("\t Update task:");
    }

    public int readChoosingTask(int from, int to) {
        int index = ReadInputUtil.readIntFromInput(from, to);
        return index;
    }

    public Task updateTaskData(Task task) {

        Task newTask = null;
        if (task.isRepeated()) {
            newTask = updateRepeatTask(task);
        } else {
            newTask = updateNoRepeatTask(task);
        }
        return newTask;
    }

    public Task updateNoRepeatTask(Task task) {
        System.out.println("Update title?");
        System.out.println("Write 1 to update, 0 to skip:");
        int chosenItem = ReadInputUtil.readIntFromInput(0, 1);
        switch (chosenItem) {
            case 0:
                break;
            case 1:
                System.out.print("Write task title:\n>:");
                String title = ReadInputUtil.readStringFromInput();
                task.setTitle(title);
                break;
            default:
                break;
        }
        chosenItem = -1;
        System.out.println("Update date?");
        System.out.println("Write 1 to update, 0 to skip:");
        chosenItem = ReadInputUtil.readIntFromInput(0, 1);
        switch (chosenItem) {
            case 0:
                break;
            case 1:
                System.out.print("\nWrite start time in format 'yyyy-MM-dd HH:mm' for example 2016-11-09 11:44\n>:");
                String taskTime = ReadInputUtil.readDateString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime time = LocalDateTime.parse(taskTime, formatter);
                task.setTime(time);
                break;
            default:
                break;
        }
        return task;
    }

    public Task updateRepeatTask(Task task) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Update title?");
        System.out.println("Write 1 to update, 0 to skip:");
        int chosenItem = ReadInputUtil.readIntFromInput(0, 1);
        switch (chosenItem) {
            case 0:
                break;
            case 1:
                System.out.print("Write task title:\n>:");
                String title = ReadInputUtil.readStringFromInput();
                task.setTitle(title);
                break;
            default:
                break;
        }
        System.out.println("Update date start?");
        System.out.println("Write 1 to update, 0 to skip:");
        LocalDateTime timeStart = null;
        LocalDateTime timeEnd = null;
        chosenItem = ReadInputUtil.readIntFromInput(0, 1);
        switch (chosenItem) {
            case 0:
                break;
            case 1:
                System.out.print("\nWrite start time in format 'yyyy-MM-dd HH:mm' for example 2016-11-09 11:44\n>:");
                String taskTime = ReadInputUtil.readDateString();
                timeStart = LocalDateTime.parse(taskTime, formatter);
                break;
            default:
                break;
        }
        System.out.println("Update date end?");
        System.out.println("Write 1 to update, 0 to skip:");
        chosenItem = ReadInputUtil.readIntFromInput(0, 1);
        switch (chosenItem) {
            case 0:
                break;
            case 1:
                while(true) {
                    System.out.print("\nWrite end time in format 'yyyy-MM-dd HH:mm' for example 2016-11-09 11:44\n>:");
                    String taskTime = ReadInputUtil.readDateString();
                    timeEnd = LocalDateTime.parse(taskTime, formatter);
                    if (timeEnd.isAfter(timeStart)) {
                        break;
                    } else {
                        System.out.println("Time must be after start of task");
                    }
                }
                break;
            default:
                break;
        }
        int interval = 0;
        System.out.println("Update interval?");
        System.out.println("Write 1 to update, 0 to skip:");
        chosenItem = ReadInputUtil.readIntFromInput(0, 1);
        switch (chosenItem) {
            case 0:
                break;
            case 1:
                System.out.println("Write new interval:");
                interval = ReadInputUtil.readIntFromInput(0, 1000);
                break;
            default:
                break;
        }
        task.setTime(timeStart, timeEnd, interval);
        return task;
    }


}
