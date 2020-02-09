package views;

import contorllers.MainController;
import models.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddTaskView {

    private Scanner userInputScanner;
    private String title;
    private LocalDateTime time;

    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public AddTaskView() {
        userInputScanner = new Scanner(System.in);
        System.out.println("\t\tAdd task");
    }

    public void readUserInput() {

    }

    public Task printTaskTypeView() {
        System.out.println("Choose type of task:");
        System.out.println("1. No repeat task");
        System.out.println("2. Repeat task");
        int item = MainController.readIntFromInput(1, 2);
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
        System.out.print("Write task title:\n>:");
        String title = userInputScanner.nextLine();

        System.out.print("\nWrite start time in format yyyy-MM-dd HH:mm:ss\n>:");
        String timeStart = userInputScanner.nextLine();

        System.out.print("\nWrite end time in format yyyy-MM-dd HH:mm:ss\n>:");
        String timeEnd = userInputScanner.nextLine();

        System.out.print("\nWrite interval time in format yyyy-MM-dd HH:mm:ss\n>:");
        int interval = userInputScanner.nextInt();

        return ;
    }

    private Task readNoRepeatTaskData() {

        System.out.print("Write task title:\n>:");
        String title = userInputScanner.nextLine();

        System.out.print("\nWrite time:\n>:");
        String time = userInputScanner.nextLine();

        return ;
    }

}
