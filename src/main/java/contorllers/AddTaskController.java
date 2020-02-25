package contorllers;

import models.AbstractTaskList;
import models.Task;
import util.ReadInputUtil;
import views.AddTaskView;
import views.AddTaskViewTemplate;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class AddTaskController implements AddTaskTemplate {

    AddTaskViewTemplate addTaskView;
    AbstractTaskList taskList;
    File fileTasks;

    public AddTaskController(AbstractTaskList abstractTaskList) {
        addTaskView = new AddTaskView();

        fileTasks = new File("tasks.json");

        taskList = abstractTaskList;
        addTaskToList();
        ReadInputUtil.saveListToFile(taskList, fileTasks);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        switch (taskStrings.length) {
            case 2:
                LocalDateTime time = LocalDateTime.parse(taskStrings[1], formatter);
                task = new Task(taskStrings[0], time);
                break;
            case 4:
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
    }

    // RegExp for time ^(\d{4})-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01]) (0[0-9]|1[012]):([0-5][0-9]):([0-5][0-9])$  -> yyyy-mm-dd hh:mm:ss

}