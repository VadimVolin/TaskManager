package views;

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
        System.out.println("Write task title:\n>:");


    }

    public void readUserInput() {

    }

    public int chooseTaskType() {
        System.out.println("Choose type of task:");
        System.out.println("1. Repeat task");
        System.out.println("2. Repeat task");
//        int chooseItem =
//        switch ()
        return 0;
    }

    public boolean addTask(Task task) {
return false;
    }

}
