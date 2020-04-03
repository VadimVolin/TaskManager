package contorllers;

import models.AbstractTaskList;
import models.FileIO;
import models.Task;
import views.AddTaskView;
import views.AddTaskViewTemplate;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class AddTaskController implements AddTaskTemplate {

    public static final int SIMPLE_TASK_SIZE = 2;
    public static final int SPEC_TASK_SIZE = 4;
    private AddTaskViewTemplate addTaskView;
    private AbstractTaskList taskList;
    private File fileTasks;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AddTaskController(AbstractTaskList abstractTaskList, File file) {
        addTaskView = new AddTaskView();

        fileTasks = file;

        taskList = abstractTaskList;
    }

    public AbstractTaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void addTaskToList() {
        String addedTask = addTaskView.printTaskTypeView();
        StringTokenizer tokenizer = new StringTokenizer(addedTask, "&");
        String[] taskStrings = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            taskStrings[i] = tokenizer.nextToken();
            i++;
        }
        Task task = null;
        switch (taskStrings.length) {
            case SIMPLE_TASK_SIZE:
                LocalDateTime time = LocalDateTime.parse(taskStrings[1], formatter);
                task = new Task(taskStrings[0], time);
                break;
            case SPEC_TASK_SIZE:
                LocalDateTime start = LocalDateTime.parse(taskStrings[1], formatter);
                LocalDateTime end = LocalDateTime.parse(taskStrings[2], formatter);
                int interval = Integer.parseInt(taskStrings[3]);
                task = new Task(taskStrings[0], start, end, interval);
                break;
            default:
                break;
        }
        task.setActive(true);
        taskList.add(task);
        FileIO.saveListToFile(taskList, fileTasks);
    }

    // RegExp for time ^(\d{4})-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01]) (0[0-9]|1[012]):([0-5][0-9]):([0-5][0-9])$  -> yyyy-mm-dd hh:mm:ss

}