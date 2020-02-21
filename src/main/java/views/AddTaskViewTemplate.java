package views;

import models.Task;

public interface AddTaskViewTemplate {

    Task readNoRepeatTaskData();
    Task readRepeatTaskData();
    Task printTaskTypeView();

}
