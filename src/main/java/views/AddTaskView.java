package views;

import contorllers.AddTaskController;
import contorllers.MainController;
import models.Task;
import org.apache.log4j.Logger;
import util.ReadInputUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddTaskView {

    final static Logger logger = Logger.getLogger(AddTaskView.class);

    private Scanner userInputScanner;

    private String title;

    private LocalDateTime time;

    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;

    public AddTaskView() {
        userInputScanner = new Scanner(System.in);
        System.out.println("\t\tAdd task");
    }

    public Task printTaskTypeView() {
        System.out.println("Choose type of task:");
        System.out.println("1. No repeat task");
        System.out.println("2. Repeat task");
        int item = ReadInputUtil.readIntFromInput(userInputScanner, 1, 2);
        Task task = null;
        switch (item) {
            case 1:
                task = readNoRepeatTaskData();
                break;
            case 2:
                task = readRepeatTaskData();
                break;
            default:

        }
        return task;

    }

    private Task readRepeatTaskData() {
        Task task = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.print("Write task title:\n>:");
        String title = ReadInputUtil.readStringFromInput(userInputScanner);

        System.out.print("\nWrite start time in format yyyy-MM-dd HH:mm:ss for example 2016-11-09 11:44:44\n>:");
        String timeStart = ReadInputUtil.readDateString(userInputScanner);

        start = LocalDateTime.parse(timeStart, formatter);

        while(true) {
            System.out.print("\nWrite end time in format yyyy-MM-dd HH:mm:ss for example 2016-11-09 11:44:44\n>:");
            String timeEnd = ReadInputUtil.readDateString(userInputScanner);
            end = LocalDateTime.parse(timeEnd, formatter);
            if (end.isAfter(start)) {
                break;
            }
        }

        System.out.print("\nWrite interval time in minutes\n>:");
        int intervalInMinutes = ReadInputUtil.readIntFromInput(userInputScanner, 0, 1000);
        interval = intervalInMinutes * 60;

        if (!title.isEmpty() && interval >= 0) {
            task = new Task(title, start, end, interval);
        }

        return task;
    }

    private Task readNoRepeatTaskData() {
        Task task = null;
        System.out.print("Write task title:\n>:");
        String title = ReadInputUtil.readStringFromInput(userInputScanner);

        System.out.print("\nWrite time in format 'yyyy-MM-dd HH:mm:ss' for example 2016-11-09 11:44:44\n>:");
        String taskTime = ReadInputUtil.readDateString(userInputScanner);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = LocalDateTime.parse(taskTime, formatter);

        if (!title.isEmpty()) {
            task = new Task(title, time);
        }

        return task;
    }
}
