package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.TaskIO;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
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
    private ShowListTemplate showListController;
    private CalendarControllerTemplate calendarController;
    private NotificationController controller;

    AbstractTaskList abstractTaskList;

    public MainController() {
        abstractTaskList = ReadInputUtil.getTaskListFromFile(new File("tasks.json"));
        controller = new NotificationController(abstractTaskList);
        menuView = new MenuView();
        chooseMenuItem();
    }

    public void chooseMenuItem() {

        startPoint:
        while (true) {
            menuView.printInfo();
            int chosenMenuItem = goToCurrentView();

            abstractTaskList = ReadInputUtil.getTaskListFromFile(new File("tasks.json"));
            controller.setTaskList(abstractTaskList);

            switch (chosenMenuItem) {
                case 1:
                    logger.info("User choose add item view");
                    System.out.println("Load add item view");
                    addTaskController = new AddTaskController(abstractTaskList);

                    continue startPoint;
                case 2:
                    logger.info("User choose update item");
                    System.out.println("Load update item view");
                    updateTaskController = new UpdateTaskController(abstractTaskList);
                    continue startPoint;

                case 3:
                    logger.info("User choose delete item");
                    System.out.println("Load delete item view");
                    deleteTaskController = new DeleteTaskController(abstractTaskList);
                    continue startPoint;
                case 4:
                    logger.info("User choose print task list");
                    System.out.println("Load task list");
                    showListController = new ShowListController(abstractTaskList);
                    continue startPoint;
                case 5:
                    logger.info("user choose calendar list");
                    calendarController = new CalendarController(abstractTaskList);
                    continue startPoint;
                case 6:
                    logger.info("User choose clean list");
                    System.out.println("Cleaning list..");
                    try {
                        TaskIO.writeText(new ArrayTaskList(), new File("tasks.json"));
                    } catch (IOException e) {
                        logger.error("IOException ", e);
                    }
                    continue startPoint;
                case 7:
                    logger.info("User choose exit");
                    System.out.println("User choose exit");
                    ReadInputUtil.getScanner().close();
                    System.exit(0);
                    break;
                default:
                    continue startPoint;
            }
        }
    }

    public int goToCurrentView() {
        int chosenItem;
        chosenItem = ReadInputUtil.readIntFromInput(1, 7);
        return chosenItem;
    }


}
