package contorllers;

import models.AbstractTaskList;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
import views.AddTaskView;
import views.UpdateTaskView;
import views.UpdateTaskViewTemplate;

import java.io.File;

public class UpdateTaskController implements UpdateTaskTemplate {

    final static Logger logger = Logger.getLogger(AddTaskView.class);

    UpdateTaskViewTemplate updateTaskView;
    AbstractTaskList taskList;
    File fileTasks;

    public UpdateTaskController(AbstractTaskList abstractTaskList) {
        updateTaskView = new UpdateTaskView();
        fileTasks = new File("tasks.json");
        taskList = abstractTaskList;
        if (taskList.size() > 0) {
            updateTaskView.printList(taskList);
            updateTaskView.printUpdateInfo();
            updateTask();
        } else {
            System.out.println("List is empty!");
            logger.info("list is empty - " + taskList.size());
        }
    }

    @Override
    public void updateTask() {
        int index = updateTaskView.readChoosingTask(1, taskList.size()) - 1;
        updateTaskView.updateTaskData(taskList.getTask(index));
        ReadInputUtil.saveListToFile(taskList, fileTasks);
        logger.info("Task update :" + index + " | " + taskList.getTask(index));
    }
}
