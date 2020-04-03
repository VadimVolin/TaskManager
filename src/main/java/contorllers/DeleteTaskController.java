package contorllers;

import models.AbstractTaskList;
import models.FileIO;
import models.Task;
import org.apache.log4j.Logger;
import views.DeleteTaskView;
import views.DeleteTaskViewTemplate;

import java.io.File;

public class DeleteTaskController implements DeleteTaskTemplate {

    private final static Logger logger = Logger.getLogger(DeleteTaskController.class);

    private DeleteTaskViewTemplate deleteTaskView;

    private AbstractTaskList taskList;

    private File fileTasks;

    public DeleteTaskController(AbstractTaskList abstractTaskList, File file) {

        fileTasks = file;
        taskList = abstractTaskList;

    }

    @Override
    public void delete() {
        StringBuffer listString = new StringBuffer();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append("" + (i + 1) + ". " + taskList.getTask(i) + "\n");
        }

        deleteTaskView = new DeleteTaskView();
        deleteTaskView.printDeleteList(listString.toString());
        if (taskList.size() > 0) {
            int index = deleteTaskView.readChoosingTask(1, taskList.size()) - 1;
            delete(index);
        } else {
            System.out.println("List is empty");
            logger.info("List is empty");
        }
        FileIO.saveListToFile(taskList, fileTasks);
    }

    public AbstractTaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(AbstractTaskList taskList) {
        this.taskList = taskList;
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
