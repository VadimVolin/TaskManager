package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Task;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
import views.AddTaskView;
import views.UpdateTaskView;

import java.io.File;

public class DeleteTaskController implements DeleteTaskTemplate{

    final static Logger logger = Logger.getLogger(AddTaskView.class);

    UpdateTaskView updateTaskView;

    AbstractTaskList taskList = null;

    File fileTasks = null;

    public DeleteTaskController () {

        fileTasks = new File("tasks.json");

        taskList = ReadInputUtil.getTaskListFromFile(fileTasks);

        updateTaskView = new UpdateTaskView();
        updateTaskView.printList(taskList);
        updateTaskView.printUpdateInfo();
        int index = updateTaskView.readChoosingTask(1, taskList.size()) - 1;
        Task task = updateTaskView.updateTaskData(taskList.getTask(index));
        delete(index);
        ReadInputUtil.saveListToFile(taskList, fileTasks);
        MainController.finishAction();
    }

    @Override
    public void delete(int index) {

    }
}
