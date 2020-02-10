package views;

import models.AbstractTaskList;
import models.Task;

public interface UpdateTaskViewTemplate {

    void printStartInfo();
    void printUpdateInfo();
    void printList(AbstractTaskList abstractTaskList);
    int readChoosingTask(int from, int to);
    Task updateTaskData(Task task);
    Task updateNoRepeatTask(Task task);
    Task updateRepeatTask(Task task);


}
