package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.TaskIO;
import org.apache.log4j.Logger;
import models.ReadInputUtil;
import views.MenuView;
import views.MenuViewTemplate;

import java.io.File;
import java.io.IOException;


public class MainController implements MainTemplate {

    final static Logger logger = Logger.getLogger(MainController.class);

    private MenuViewTemplate menuView;
    private AddTaskTemplate addTaskController;
    private UpdateTaskTemplate updateTaskController;
    private DeleteTaskTemplate deleteTaskController;
    private ShowListController showListController;
    private CalendarControllerTemplate calendarController;
    private NotificationController controller;

    AbstractTaskList abstractTaskList;
    private File file;

    public MainController() {
        menuView = new MenuView();
        abstractTaskList = initListFile();
        controller = new NotificationController(abstractTaskList);
        chooseMenuItem();
    }

    public AbstractTaskList initListFile() {
        int chooseItem = ReadInputUtil.readIntFromInput(1, 3);
        AbstractTaskList tasks = null;
        try {
            switch (chooseItem) {
                case 1:
                    file = new File("tasks.json");
                    file.delete();
                    file.createNewFile();
                    tasks = new ArrayTaskList();
                    break;
                case 2:
                    String path = menuView.readFilePath();
                    file = new File(path);
                    tasks = ReadInputUtil.getTaskListFromFile(file);
                    tasks = tasks == null ? new ArrayTaskList() : tasks;
                    break;
                case 3:
                    String pathFromCache = ReadInputUtil.readPathInfoFromCache();
                    if (pathFromCache.equals("")) {
                        file = new File("newTasks.json");
                        logger.error("file not found in path: " + pathFromCache);
                        logger.error("create new file");
                    } else {
                        file = new File(pathFromCache);
                        logger.info("path: " + file.getPath());
                    }
                    tasks = ReadInputUtil.getTaskListFromFile(file);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void chooseMenuItem() {

        startPoint:
        while (true) {
            menuView.printInfo();
            int chosenMenuItem = menuView.goToCurrentView();

            abstractTaskList = ReadInputUtil.getTaskListFromFile(file);
            controller.setTaskList(abstractTaskList);

            switch (chosenMenuItem) {
                case 1:
                    logger.info("User choose add item view");
                    addTaskController = new AddTaskController(abstractTaskList, file);

                    continue startPoint;
                case 2:
                    logger.info("User choose update item");
                    updateTaskController = new UpdateTaskController(abstractTaskList, file);
                    continue startPoint;

                case 3:
                    logger.info("User choose delete item");
                    deleteTaskController = new DeleteTaskController(abstractTaskList, file);
                    continue startPoint;
                case 4:
                    logger.info("User choose print task list");
                    showListController = new ShowListController(abstractTaskList);
                    continue startPoint;
                case 5:
                    logger.info("user choose calendar list");
                    calendarController = new CalendarController(abstractTaskList);
                    continue startPoint;
                case 6:
                    logger.info("User choose clean list");
                    try {
                        TaskIO.writeText(new ArrayTaskList(), file);
                    } catch (IOException e) {
                        logger.error("IOException ", e);
                    }
                    continue startPoint;
                case 7:
                    logger.info("User choose exit");
                    System.out.println("User choose exit");
                    ReadInputUtil.saveListToFile(abstractTaskList, file);
                    ReadInputUtil.savePathInfoToCache(file.getPath());
                    ReadInputUtil.getScanner().close();
                    System.exit(0);
                    break;
                default:
                    continue startPoint;
            }
        }
    }


}
