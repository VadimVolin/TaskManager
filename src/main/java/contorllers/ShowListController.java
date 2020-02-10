package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.TaskIO;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import views.ShowListView;
import views.ShowListViewTemplate;

import java.io.File;
import java.io.IOException;

public class ShowListController implements ShowListTemplate{

    ShowListViewTemplate showListView;
    AbstractTaskList arrayTaskList;

    final static Logger logger = Logger.getLogger(ShowListController.class);

    public ShowListController() {
        showListView = new ShowListView();
        arrayTaskList = initListFromFile();
        showListView.printTaskList(arrayTaskList);
    }

    private AbstractTaskList initListFromFile() {
        File file = new File("tasks.json");

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
        return taskList;
    }

}
