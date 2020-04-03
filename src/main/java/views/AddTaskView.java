package views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTaskView implements AddTaskViewTemplate {

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AddTaskView() {

    }

    public String printTaskTypeView() {
        System.out.println("\t\tAdd task");
        System.out.println("Choose type of task:");
        System.out.println("1. No repeat task");
        System.out.println("2. Repeat task");
        int item = ReadInputUtil.readIntFromInput(1, 2);
        String task = null;
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

    public String readRepeatTaskData() {
        StringBuffer taskString = new StringBuffer();

        System.out.print("Write task title:\n>:");
        title = ReadInputUtil.readStringFromInput();

        System.out.print("\nW?rite start time in format yyyy-MM-dd HH:mm for example 2016-11-09 11:44\n>:");
        String timeStart = ReadInputUtil.readDateString();

        start = LocalDateTime.parse(timeStart, formatter);
        String timeEnd = "";
        while (true) {
            System.out.print("\nWrite end time in format yyyy-MM-dd HH:mm for example 2016-11-09 11:44\n>:");
            timeEnd = ReadInputUtil.readDateString();
            end = LocalDateTime.parse(timeEnd, formatter);
            if (end.isAfter(start)) {
                timeEnd.replace('T', ' ');
                break;
            }
        }

        System.out.print("\nWrite interval time in minutes\n>:");
        int intervalInMinutes = ReadInputUtil.readIntFromInput(0, 1000);
        interval = intervalInMinutes;

        if (!title.isEmpty() && interval >= 0) {
            taskString.append(title).append("&").append(timeStart).append("&").append(timeEnd).append("&").append(interval);
        }

        return taskString.toString();
    }

    public String readNoRepeatTaskData() {
        StringBuffer taskString = new StringBuffer();
        System.out.print("Write task title:\n>:");
        title = ReadInputUtil.readStringFromInput();

        System.out.print("\nWrite time in format 'yyyy-MM-dd HH:mm' for example 2016-11-09 11:44\n>:");
        String taskTime = ReadInputUtil.readDateString();
        if (!title.isEmpty()) {
            taskString.append(title).append("&").append(taskTime);
        }

        return taskString.toString();
    }
}
