package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Task;
import models.TaskIO;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import util.ReadInputUtil;
import views.AddTaskView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AddTaskController implements AddTaskTemplate {

    AddTaskView addTaskView = null;

    AbstractTaskList taskList = null;
    final static Logger logger = Logger.getLogger(AddTaskController.class);

    File fileTasks = null;

    public AddTaskController() {
        addTaskView = new AddTaskView();

        fileTasks = new File("tasks.json");

        taskList = ReadInputUtil.getTaskListFromFile(fileTasks);
        addTaskToList();
        ReadInputUtil.saveListToFile(taskList, fileTasks);
        MainController.finishAction();
    }

    @Override
    public void addTaskToList() {
        Task addedTask = addTaskView.printTaskTypeView();
        taskList.add(addedTask);
    }

    // RegExp for time ^(\d{4})-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01]) (0[0-9]|1[012]):([0-5][0-9]):([0-5][0-9])$  -> yyyy-mm-dd hh:mm:ss

}