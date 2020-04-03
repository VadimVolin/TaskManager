package views;

import java.time.LocalDateTime;

public interface UpdateTaskViewTemplate {

    void printUpdateInfo();

    void printList(String list);

    int readChoosingTask(int from, int to);

    String[] updateTaskData(String task);

    void printUpdateInfoForRepeatTask();

    void printUpdateInfoForNoRepeatTask();

    String[] readNewRepeatTaskData(String[] task);

    String[] readNewNoRepeatTaskData(String[] task);

    String changeToRepeat(String[] task);

    String changeToNoRepeat(String[] taskForNoRepeat);

    boolean changeActivity();

    String readNewTaskTitle();

    LocalDateTime readNewDate();

    LocalDateTime readNewEndTime(LocalDateTime timeStart);

    int readNewIntervalInMinutes();
}
