package contorllers;

import models.AbstractTaskList;

public interface AddTaskTemplate {

    void addTaskToList();
    AbstractTaskList getTaskList();
    void setTaskList(AbstractTaskList taskList);

}
