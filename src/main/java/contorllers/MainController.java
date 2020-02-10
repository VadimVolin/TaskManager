package contorllers;

import models.ArrayTaskList;
import models.TaskIO;
import org.apache.log4j.Logger;
import util.ReadInputUtil;
import views.MenuView;
import views.MenuViewTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MainController {

    final static Logger logger = Logger.getLogger(MainController.class);

    private MenuViewTemplate menuView;
    private AddTaskTemplate addTaskController;
    private UpdateTaskTemplate updateTaskController;
    private DeleteTaskTemplate deleteTaskController;
    private ShowListTemplate showListController;

    private static Scanner scanner;


    public MainController() {
        menuView = new MenuView();
        scanner = new Scanner(System.in);
        int chosenMenuItem = goToCurrentView();
        chooseMenuItem(chosenMenuItem);
    }

    public void chooseMenuItem(int chosenItem) {
        switch (chosenItem) {
            case 1:
                logger.info("User choose add item view");
                System.out.println("Load add item view");
                addTaskController = new AddTaskController();
                break;
            case 2:
                logger.info("User choose update item");
                System.out.println("Load update item view");
                updateTaskController = new UpdateTaskController();
                break;
            case 3:
                logger.info("User choose delete item");
                System.out.println("Load delete item view");
                deleteTaskController = new DeleteTaskController();
                break;
            case 4:
                logger.info("User choose print task list");
                System.out.println("Load task list");
                showListController = new ShowListController();
                break;
            case 5:
                logger.info("user choose calendar list");

            case 6:
                logger.info("User choose clean list");
                System.out.println("Cleaning list..");
                try {
                    TaskIO.writeText(new ArrayTaskList(), new File("tasks.json"));
                } catch (IOException e) {
                    logger.error("IOException ",e);
                }
                new MainController();
            case 7:
                logger.info("User choose exit");
                System.out.println("User choose exit");
                System.exit(0);
                break;
            default:
                new MainController();
        }
    }

    public static void finishAction() {
        System.out.println("Return to menu 1 | exit 0");
        int chosenVariant = 1;
        chosenVariant = ReadInputUtil.readIntFromInput(0, 1);
        switch (chosenVariant) {
            case 1:
                logger.info("User choose go to main menu");
                MainController mainController = new MainController();
            case 2:
                logger.info("User choose exit");
                System.exit(0);
        }
    }

    public int goToCurrentView() {
        int chosenItem = 0;
        chosenItem = ReadInputUtil.readIntFromInput(1, 6);
        return chosenItem;
    }


}
