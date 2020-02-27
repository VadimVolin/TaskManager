package contorllers;

import models.AbstractTaskList;
import org.apache.log4j.Logger;
import models.ReadInputUtil;
import views.AddTaskView;
import views.UpdateTaskView;
import views.UpdateTaskViewTemplate;

import java.io.File;

public class UpdateTaskController implements UpdateTaskTemplate {

    final static Logger logger = Logger.getLogger(AddTaskView.class);

    UpdateTaskViewTemplate updateTaskView;
    AbstractTaskList taskList;
    File fileTasks;

    public UpdateTaskController(AbstractTaskList abstractTaskList, File file) {
        updateTaskView = new UpdateTaskView();
        fileTasks = file;
        taskList = abstractTaskList;

        StringBuffer listString = new StringBuffer();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append("" + (i + 1) + ". " + taskList.getTask(i) + "\n");
        }


        if (taskList.size() > 0) {
            updateTaskView.printList(listString.toString());
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
