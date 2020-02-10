package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Task;
import models.TaskIO;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import views.AddTaskView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AddTaskController implements AddTaskTemplate {

    AddTaskView addTaskView = null;

    AbstractTaskList taskList = null;
    final static Logger logger = Logger.getLogger(AddTaskController.class);

    File fileTasks = null;

    public AddTaskController() {
        addTaskView = new AddTaskView();

        fileTasks = new File("tasks.json");


        taskList = getTaskListFromFile();
        addTaskToList();
        saveListToFile();
        MainController.finishAction();
    }

    @Override
    public void addTaskToList() {
        Task addedTask = addTaskView.printTaskTypeView();
        taskList.add(addedTask);
    }

    @Override
    public void saveListToFile() {
        if (!fileTasks.exists()) {
//            System.out.println("File not found");
//            logger.error("File not found in path");
            fileTasks = new File("tasks.json");
        }
        try {
            TaskIO.writeText(taskList, fileTasks);
            System.out.println("YA ZAPISAL");

        } catch (IOException e) {
            logger.error("IOException in read from file", e);
        }
    }

    @Override
    public AbstractTaskList getTaskListFromFile() {
        AbstractTaskList taskList = new ArrayTaskList();
        if (!fileTasks.exists()) {
            System.out.println("File not found");
            logger.error("File not found in path");
            return taskList;
        }
        try {
            TaskIO.readText(taskList, fileTasks);
            System.out.println("YA PROCHETAL");
        } catch (IOException e) {
            logger.error("IOException in read from file", e);
        } catch (ParseException e) {
            logger.error("Parse Exception in read from file", e);
        }
        return taskList;
    }

    public static String readStringFromInput(Scanner scanner) {
        String input = "";
        boolean inputFlag = true;
        while (inputFlag) {
                input = scanner.nextLine();
                if (!input.trim().isEmpty()) {
                    inputFlag = false;
                } else {
                    System.out.printf("Wrong value.. string is empty");
                    logger.error("Wrong input: string is empty :" + input);
                }
        }
        return input;
    }

}