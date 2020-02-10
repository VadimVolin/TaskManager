package contorllers;

import models.AbstractTaskList;
import models.Task;

public interface AddTaskTemplate {

    void addTaskToList();
    void saveListToFile();
    AbstractTaskList getTaskListFromFile();

}
