package views;

import models.AbstractTaskList;

public interface DeleteTaskViewTemplate {

    void printDeleteList(String taskLst);

    int readChoosingTask(int from, int to);

}
