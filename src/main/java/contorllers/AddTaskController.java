package contorllers;

import models.AbstractTaskList;
import models.Task;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
import views.AddTaskView;
import views.AddTaskViewTemplate;

import java.io.File;

public class AddTaskController implements AddTaskTemplate {

    AddTaskViewTemplate addTaskView;
    AbstractTaskList taskList;
    File fileTasks;

    public AddTaskController() {
        addTaskView = new AddTaskView();

        fileTasks = new File("tasks.json");

        taskList = ReadInputUtil.getTaskListFromFile(fileTasks);
        addTaskToList();
        ReadInputUtil.saveListToFile(taskList, fileTasks);
    }

    @Override
    public void addTaskToList() {
        Task addedTask = addTaskView.printTaskTypeView();
        taskList.add(addedTask);
    }

    // RegExp for time ^(\d{4})-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01]) (0[0-9]|1[012]):([0-5][0-9]):([0-5][0-9])$  -> yyyy-mm-dd hh:mm:ss

}