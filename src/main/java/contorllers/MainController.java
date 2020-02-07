package contorllers;

import org.apache.log4j.Logger;
import views.MenuView;

import java.util.Scanner;

public class MainController {

    final static Logger logger = Logger.getLogger(MainController.class);

    MenuView menuView;
    AddTaskController addTaskController;
    UpdateTaskController updateTaskController;
    DeleteTaskController deleteTaskController;
    ShowListController showListController;

    public MainController() {
        menuView = new MenuView();
        int chosenMenuItem = goToCurrentView();
        chooseMenuItem(chosenMenuItem);
    }

    public void chooseMenuItem(int chosenItem) {
        switch (chosenItem) {
            case 1:
                logger.info("User choose add item view");
                System.out.println("Load add item view");
                addTaskController = new AddTaskController();
                finishAction();
            case 2:
                logger.info("User choose update item");
                System.out.println("Load update item view");
                updateTaskController = new UpdateTaskController();
                finishAction();
            case 3:
                logger.info("User choose delete item");
                System.out.println("Load delete item view");
                deleteTaskController = new DeleteTaskController();
                finishAction();
            case 4:
                logger.info("User choose print task list");
                System.out.println("Load task list");
                showListController = new ShowListController();
                finishAction();
            case 5:
                logger.info("User choose exit");
                System.out.println("User choose exit");
                System.exit(0);
        }
    }

    public void finishAction() {
        System.out.println("Return to menu 1 | exit 0");
        int chosenVariant = readIntFromInput(0, 1);
        switch (chosenVariant) {
            case 1:
                logger.info("User choose go to main menu");
                menuView = new MenuView();
            case 2:
                logger.info("User choose exit");
                System.exit(0);
        }
    }

    public int goToCurrentView() {
        int chosenItem = readIntFromInput(1, 5);
        return chosenItem;
    }

    public int readIntFromInput(int from, int to) {
        int chosenItem;
        while (true) {
            System.out.println("Choose item:");
            Scanner userInput = new Scanner(System.in);
            String input = "";
            input = userInput.nextLine();
            if (!input.trim().isEmpty() && input.trim().length() == 1) {
                chosenItem = Character.getNumericValue(input.charAt(0));
                if (chosenItem >= from && chosenItem <= to) {
                    userInput.close();
                    break;
                } else {
                    System.out.println("Please, write " + from + "|" + to + " , only numbers.");
                    logger.error("Wrong input: " + chosenItem);
                }
            } else {
                System.out.println("Please, write from " + from + "|" + to + " , only numbers.");
                logger.error("Wrong input: " + input);
            }
        }
        return chosenItem;
    }

}
