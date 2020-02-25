package views;

import models.AbstractTaskList;
import models.Task;

import java.time.LocalDateTime;

public interface UpdateTaskViewTemplate {

    void printStartInfo();

    void printUpdateInfo();

    void printList(String list);

    int readChoosingTask(int from, int to);

    void updateTaskData(Task task);

    void printUpdateInfoForRepeatTask();

    void printUpdateInfoForNoRepeatTask();

    void readNewRepeatTaskData(Task task);

    void readNewNoRepeatTaskData(Task task);

    void changeToRepeat(Task task);

    void changeToNoRepeat(Task task);

    boolean changeActivity();

    String readNewTaskTitle();

    LocalDateTime readNewDate();

    LocalDateTime readNewEndTime(LocalDateTime timeStart);

    int readNewIntervalInMinutes();
}
