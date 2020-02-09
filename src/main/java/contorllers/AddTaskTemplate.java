package contorllers;

import models.AbstractTaskList;
import models.Task;

public interface AddTaskTemplate {

    void addTaskToList(Task task);
    void saveListToFile();
    AbstractTaskList getTaskListFromFile();

}
