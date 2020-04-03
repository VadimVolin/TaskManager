package contorllers;

import models.AbstractTaskList;
import models.FileIO;
import models.Task;
import org.apache.log4j.Logger;
import views.AddTaskView;
import views.UpdateTaskView;
import views.UpdateTaskViewTemplate;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateTaskController implements UpdateTaskTemplate {

    private final static Logger logger = Logger.getLogger(AddTaskView.class);

    private UpdateTaskViewTemplate updateTaskView;
    private AbstractTaskList taskList;
    private File fileTasks;

    public UpdateTaskController(AbstractTaskList abstractTaskList, File file) {
        updateTaskView = new UpdateTaskView();
        fileTasks = file;
        taskList = abstractTaskList;

    }

    @Override
    public void update() {

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

    public AbstractTaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void updateTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int index = updateTaskView.readChoosingTask(1, taskList.size()) - 1;
        Task task = taskList.getTask(index);
        StringBuffer buffer = new StringBuffer();
        if (task.isRepeated()) {
            buffer.append(true).append("&").append(task.getTitle()).append("&").append(task.getStartTime()).append("&").append(task.getEndTime()).append("&").append(task.getRepeatInterval()).append("&").append(task.isActive());
        } else {
            buffer.append(false).append("&").append(task.getTitle()).append("&").append(task.getTime()).append("&").append(task.isActive());
        }
        String[] taskData = updateTaskView.updateTaskData(buffer.toString());
        task.setTitle(taskData[1]);
        if (taskData[0].equals("true")) {
            task.setTime(LocalDateTime.parse(taskData[2]), LocalDateTime.parse(taskData[3]), Integer.valueOf(taskData[4]));
            task.setActive(Boolean.valueOf(taskData[5]));
        } else {
            task.setTime(LocalDateTime.parse(taskData[2]));
            task.setActive(Boolean.valueOf(taskData[3]));
        }
        FileIO.saveListToFile(taskList, fileTasks);
        logger.info("Task update :" + index + " | " + taskList.getTask(index));
    }
}
