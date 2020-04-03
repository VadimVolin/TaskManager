package views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class UpdateTaskView implements UpdateTaskViewTemplate {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public UpdateTaskView() {

    }

    public void printList(String taskList) {
        System.out.println(taskList);
    }

    public void printUpdateInfo() {
        System.out.println("\t Update task:");
        System.out.println("Choose task:");
        System.out.println("Input task number:");
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

    public String[] updateTaskData(String task) {
        StringTokenizer tokenizer = new StringTokenizer(task, "&");
        String[] taskStrings = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            taskStrings[i] = tokenizer.nextToken();
            i++;
        }
        if (taskStrings[0].equals("true")) {
            printUpdateInfoForRepeatTask();
            System.out.println("Choose item for update:");
            return readNewRepeatTaskData(taskStrings);
        } else {
            printUpdateInfoForNoRepeatTask();
            System.out.println("Choose item for update:");
            return readNewNoRepeatTaskData(taskStrings);
        }
    }

    public String[] readNewRepeatTaskData(String[] task) {
        int index = ReadInputUtil.readIntFromInput(1, 6);
        switch (index) {
            case 1:
                String newTitle = readNewTaskTitle();
                task[1] = newTitle;
                break;
            case 2:
                LocalDateTime dateTime = readNewDate();
                task[2] = dateTime.toString();
                break;
            case 3:
                LocalDateTime newEndTime = readNewEndTime(LocalDateTime.parse(task[3]));
                task[3] = newEndTime.toString();
                break;
            case 4:
                int interval = readNewIntervalInMinutes();
                task[4] = String.valueOf(interval);
                break;
            case 5:
                boolean isActive = changeActivity();
                task[5] = String.valueOf(isActive);
                break;
            case 6:
                String str = changeToNoRepeat(task);
                StringTokenizer tokenizer = new StringTokenizer(str, "&");
                String[] taskStrings = new String[tokenizer.countTokens()];
                int i = 0;
                task = new String[4];
                while (tokenizer.hasMoreTokens()) {
                    task[i] = tokenizer.nextToken();
                    i++;
                }
                break;
            default:
                break;
        }
        return task;
    }

    public String[] readNewNoRepeatTaskData(String[] task) {
        int index = ReadInputUtil.readIntFromInput(1, 4);
        switch (index) {
            case 1:
                String newTitle = readNewTaskTitle();
                task[1] = newTitle;
                break;
            case 2:
                LocalDateTime dateTime = readNewDate();
                task[2] = dateTime.toString();
                break;
            case 3:
                boolean isActive = changeActivity();
                task[3] = String.valueOf(isActive);
                break;
            case 4:
                String str = changeToRepeat(task);
                StringTokenizer tokenizer = new StringTokenizer(str, "&");
                String[] taskStrings = new String[tokenizer.countTokens()];
                int i = 0;
                task = new String[6];
                while (tokenizer.hasMoreTokens()) {
                    task[i] = tokenizer.nextToken();
                    i++;
                }
                break;
            default:
                break;
        }
        return task;
    }

    public String changeToRepeat(String[] taskForRepeat) {
        LocalDateTime dateTime = readNewDate();
        LocalDateTime newEndTime = readNewEndTime(dateTime);
        int interval = readNewIntervalInMinutes();
        StringBuffer buffer = new StringBuffer();
        buffer.append(true)
                .append("&")
                .append(taskForRepeat[1])
                .append("&")
                .append(dateTime.toString())
                .append("&")
                .append(newEndTime.toString())
                .append("&")
                .append(interval)
                .append("&")
                .append(taskForRepeat[3])
        ;
        return buffer.toString();
    }

    public String changeToNoRepeat(String[] taskForNoRepeat) {
        LocalDateTime time = readNewDate();
        StringBuffer buffer = new StringBuffer();
        buffer.append(false)
                .append("&")
                .append(taskForNoRepeat[1])
                .append("&")
                .append(time.toString())
                .append("&")
                .append(taskForNoRepeat[5])
        ;
        return buffer.toString();
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
        LocalDateTime time = LocalDateTime.parse(taskTime, formatter);
        return time;
    }

    public LocalDateTime readNewEndTime(LocalDateTime timeStart) {
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
        System.out.println("Write new interval in minutes:");
        return ReadInputUtil.readIntFromInput(0, 1000);
    }


}
