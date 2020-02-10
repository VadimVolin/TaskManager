package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Task;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
import views.AddTaskView;
import views.UpdateTaskView;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UpdateTaskController implements UpdateTaskTemplate{

    final static Logger logger = Logger.getLogger(AddTaskView.class);

    UpdateTaskView updateTaskView;

    AbstractTaskList taskList = null;

    File fileTasks = null;

    public UpdateTaskController () {

        fileTasks = new File("tasks.json");

        taskList = ReadInputUtil.getTaskListFromFile(fileTasks);

        updateTaskView = new UpdateTaskView();
        updateTaskView.printList(taskList);
        updateTaskView.printUpdateInfo();
        int index = updateTaskView.readChoosingTask(1, taskList.size()) - 1;
        Task task = updateTaskView.updateTaskData(taskList.getTask(index));
        updateTask(index, task);
        ReadInputUtil.saveListToFile(taskList, fileTasks);
        MainController.finishAction();
    }

    @Override
    public void updateTask(int index, Task task) {
        boolean puttingFlag = ((ArrayTaskList) taskList).put(index, task);
        if (puttingFlag) {
            logger.info("Task update :" + index + " | " + task);
        }
    }
}
