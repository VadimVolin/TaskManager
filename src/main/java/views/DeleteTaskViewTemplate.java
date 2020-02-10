package views;

import models.AbstractTaskList;

public interface DeleteTaskViewTemplate {

    void printDeleteList(AbstractTaskList abstractTaskList);
    int readChoosingTask(int from, int to);

}
