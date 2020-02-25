package views;

import util.ReadInputUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarView implements CalendarViewTemplate {

    public CalendarView() {
        System.out.println("\tCalendar");
        printStartInfo();
    }


    @Override
    public void printStartInfo() {
        System.out.println("Write date interval for searching:");
    }

    @Override
    public void printVariantInfo() {
        System.out.println("For return to menu write 1, exit from app write 0, save calendar to file 2");
        System.out.println("0. Exit");
        System.out.println("1. Menu");
        System.out.println("2. Save to file");
    }

    @Override
    public String readFileNameForSave() {
        System.out.println("Write file name for save");
        String fileName = ReadInputUtil.readStringFromInput();
        return fileName;
    }

    @Override
    public void showCalendar(String calendarMap) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(calendarMap);
    }

    @Override
    public int getChooseVariant() {
        int chosingItem = ReadInputUtil.readIntFromInput(0, 2);
        return chosingItem;
    }


    @Override
    public LocalDateTime readDateStart() {
        System.out.print("\nWrite start time in format 'yyyy-MM-dd HH:mm' for example 2016-11-09 11:44\n>:");
        String taskTime = ReadInputUtil.readDateString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(taskTime, formatter);
        return time;
    }

    @Override
    public LocalDateTime readDateEnd(LocalDateTime timeStart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeEnd;
        while (true) {
            System.out.print("\nWrite end time in format 'yyyy-MM-dd HH:mm:ss' for example 2016-11-09 11:44\n>:");
            String taskTime = ReadInputUtil.readDateString();
            timeEnd = LocalDateTime.parse(taskTime, formatter);
            if (timeEnd.isAfter(timeStart)) {
                break;
            } else {
                System.out.println("Time must be after start of task");
            }
        }
        return timeEnd;
    }
}
