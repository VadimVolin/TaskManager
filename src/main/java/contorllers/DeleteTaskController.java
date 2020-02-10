package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Task;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
import views.AddTaskView;
import views.DeleteTaskView;
import views.DeleteTaskViewTemplate;
import views.UpdateTaskView;

import java.io.File;

public class DeleteTaskController implements DeleteTaskTemplate{

    private final static Logger logger = Logger.getLogger(DeleteTaskController.class);

    private DeleteTaskViewTemplate deleteTaskView;

    private AbstractTaskList taskList = null;

    private File fileTasks = null;

    public DeleteTaskController () {

        fileTasks = new File("tasks.json");

        taskList = ReadInputUtil.getTaskListFromFile(fileTasks);

        deleteTaskView = new DeleteTaskView();
        deleteTaskView.printDeleteList(taskList);
        if (taskList.size() > 0) {
            int index = deleteTaskView.readChoosingTask(1, taskList.size()) - 1;
            delete(index);
        } else {
            System.out.println("List is empty");
            logger.info("List is empty");
        }
        ReadInputUtil.saveListToFile(taskList, fileTasks);
    }

    @Override
    public void delete(int index) {
        Task task = taskList.getTask(index);
        if (task == null) {
            logger.info("User try delete task, but task is null");
            return;
        }
        logger.info("Remove task - " + task);
        taskList.remove(task);
    }
}
