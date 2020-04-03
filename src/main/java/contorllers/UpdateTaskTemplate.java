package contorllers;

import models.AbstractTaskList;

public interface UpdateTaskTemplate {

    void updateTask();

    AbstractTaskList getTaskList();

    void setTaskList(AbstractTaskList taskList);

    void update();

}
