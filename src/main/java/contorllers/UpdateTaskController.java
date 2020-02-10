package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Task;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
import views.AddTaskView;
import views.UpdateTaskView;
import views.UpdateTaskViewTemplate;

import java.io.File;

public class UpdateTaskController implements UpdateTaskTemplate{

    final static Logger logger = Logger.getLogger(AddTaskView.class);

    UpdateTaskViewTemplate updateTaskView;
    AbstractTaskList taskList = null;
    File fileTasks = null;

    public UpdateTaskController () {
        updateTaskView = new UpdateTaskView();
        fileTasks = new File("tasks.json");
        taskList = ReadInputUtil.getTaskListFromFile(fileTasks);
        if (taskList.size() > 0) {
            updateTaskView.printList(taskList);
            updateTaskView.printUpdateInfo();
            updateTask();
        } else {
            System.out.println("List is empty!");
            logger.info("list is empty - " + taskList.size());
        }
        MainController.finishAction();
    }

    @Override
    public void updateTask() {
        int index = updateTaskView.readChoosingTask(1, taskList.size()) - 1;
        Task task = updateTaskView.updateTaskData(taskList.getTask(index));
        ReadInputUtil.saveListToFile(taskList, fileTasks);
        boolean puttingFlag = ((ArrayTaskList) taskList).put(index, task);
        if (puttingFlag) {
            logger.info("Task update :" + index + " | " + task);
        } else {
            logger.error("Error task update :" + index + " | " + task);
        }
    }
}
