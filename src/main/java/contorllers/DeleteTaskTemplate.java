package contorllers;

import models.AbstractTaskList;

public interface DeleteTaskTemplate {

    void delete(int index);

    void delete();

    AbstractTaskList getTaskList();

    void setTaskList(AbstractTaskList taskList);
}
