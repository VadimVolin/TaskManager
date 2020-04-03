package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.FileIO;
import models.TaskIO;
import org.apache.log4j.Logger;
import views.MenuView;
import views.MenuViewTemplate;
import views.ReadInputUtil;

import java.io.File;
import java.io.IOException;


public class MainController implements MainTemplate {

    private final static Logger logger = Logger.getLogger(MainController.class);
    private AbstractTaskList abstractTaskList;
    private MenuViewTemplate menuView;
    private AddTaskTemplate addTaskController;
    private UpdateTaskTemplate updateTaskController;
    private DeleteTaskTemplate deleteTaskController;
    private ShowListController showListController;
    private CalendarControllerTemplate calendarController;
    private NotificationController controller;
    private File file;
    private final static int ADD = 1;
    private final static int UPDATE = 2;
    private final static int DELETE = 3;
    private final static int LIST = 4;
    private final static int CALENDAR = 5;
    private final static int CLEAR = 6;
    private final static int EXIT = 7;

    public MainController() {
        menuView = new MenuView();
        abstractTaskList = initListFile();
        addTaskController = new AddTaskController(abstractTaskList, file);
        updateTaskController = new UpdateTaskController(abstractTaskList, file);
        deleteTaskController = new DeleteTaskController(abstractTaskList, file);
        showListController = new ShowListController(abstractTaskList);
        calendarController = new CalendarController(abstractTaskList);
        controller = new NotificationController(abstractTaskList);
        chooseMenuItem();
    }

    public AbstractTaskList initListFile() {
        int chooseItem = menuView.chooseStartAction();
        AbstractTaskList tasks = null;
        try {
            switch (chooseItem) {
                case ADD:
                    file = new File("tasks.json");
                    file.delete();
                    file.createNewFile();
                    tasks = new ArrayTaskList();
                    break;
                case UPDATE:
                    String path = menuView.readFilePath();
                    file = new File(path);
                    tasks = FileIO.getTaskListFromFile(file);
                    tasks = tasks == null ? new ArrayTaskList() : tasks;
                    break;
                case DELETE:
                    String pathFromCache = FileIO.readPathInfoFromCache();
                    if (pathFromCache.equals("")) {
                        file = new File("newTasks.json");
                        logger.info("file not found in path: " + pathFromCache);
                        logger.info("create new file");
                    } else {
                        file = new File(pathFromCache);
                        logger.info("path: " + file.getPath());
                    }
                    tasks = FileIO.getTaskListFromFile(file);
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

            abstractTaskList = FileIO.getTaskListFromFile(file);
            controller.setTaskList(abstractTaskList);

            switch (chosenMenuItem) {
                case ADD:
                    logger.info("User choose add item view");
                    addTaskController.setTaskList(abstractTaskList);
                    addTaskController.addTaskToList();
                    continue startPoint;
                case UPDATE:
                    logger.info("User choose update item");
                    updateTaskController.setTaskList(abstractTaskList);
                    updateTaskController.update();
                    continue startPoint;

                case DELETE:
                    logger.info("User choose delete item");
                    deleteTaskController.setTaskList(abstractTaskList);
                    deleteTaskController.delete();
                    continue startPoint;
                case LIST:
                    logger.info("User choose print task list");
                    showListController.setArrayTaskList(abstractTaskList);
                    showListController.show();
                    continue startPoint;
                case CALENDAR:
                    logger.info("user choose calendar list");
                    calendarController.setTaskList(abstractTaskList);
                    calendarController.calendar();
                    continue startPoint;
                case CLEAR:
                    logger.info("User choose clean list");
                    try {
                        TaskIO.writeText(new ArrayTaskList(), file);
                    } catch (IOException e) {
                        logger.error("IOException ", e);
                    }
                    continue startPoint;
                case EXIT:
                    logger.info("User choose exit");
                    System.out.println("Exit");
                    FileIO.saveListToFile(abstractTaskList, file);
                    FileIO.savePathInfoToCache(file.getPath());
                    ReadInputUtil.closeResource();
                    System.exit(0);
                    break;
                default:
                    continue startPoint;
            }
        }
    }


}
