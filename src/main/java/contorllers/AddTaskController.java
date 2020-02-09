package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Task;
import models.TaskIO;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class AddTaskController implements AddTaskTemplate {

    AbstractTaskList taskList;
    final static Logger logger = Logger.getLogger(AddTaskController.class);

    public AddTaskController() {

    }

    @Override
    public void addTaskToList(Task task) {

    }

    @Override
    public void saveListToFile() {

    }

    @Override
    public AbstractTaskList getTaskListFromFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("tasks.txt").getFile());

        AbstractTaskList taskList = new ArrayTaskList();
        if (!file.exists()) {
            System.out.println("File not found");
            logger.error("File not found in path");
            return taskList;
        }
        try {
            TaskIO.readText(taskList, file);
        } catch (IOException e) {
            logger.error("IOException in read from file", e);
        } catch (ParseException e) {
            logger.error("Parse Exception in read from file", e);
        }
        return taskList
    }

}