package contorllers;

import org.apache.log4j.Logger;
import views.MenuView;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainController {

    final static Logger logger = Logger.getLogger(MainController.class);

    private MenuView menuView;
    private AddTaskController addTaskController;
    private UpdateTaskController updateTaskController;
    private DeleteTaskController deleteTaskController;
    private ShowListController showListController;
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
            case 2:
                logger.info("User choose update item");
                System.out.println("Load update item view");
                updateTaskController = new UpdateTaskController();
            case 3:
                logger.info("User choose delete item");
                System.out.println("Load delete item view");
                deleteTaskController = new DeleteTaskController();
            case 4:
                logger.info("User choose print task list");
                System.out.println("Load task list");
                showListController = new ShowListController();
            case 5:
                logger.info("User choose exit");
                System.out.println("User choose exit");
                System.exit(0);
            default:
                new MainController();
        }
    }

    public static void finishAction() {
        System.out.println("Return to menu 1 | exit 0");
        int chosenVariant = 1;
        chosenVariant = readIntFromInput(0, 1);
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
        chosenItem = readIntFromInput(1, 5);
        return chosenItem;
    }

    public static int readIntFromInput(int from, int to) {
        int chosenItem = 0;
        boolean inputFlag = true;
        try {
            while (inputFlag) {
                System.out.println("Choose item:");
                if (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("Wrong value.. input integer numbers from interval [%d ; %d]\n", from, to);
                    logger.error("Wrong input: " + input);
                } else {
                    chosenItem = scanner.nextInt();
                    if (chosenItem >= from && chosenItem <= to) {
                        inputFlag = false;
                    } else {
                        System.out.printf("Wrong value.. input integer numbers from interval [%d ; %d]\n", from, to);
                        logger.error("Wrong input: " + chosenItem);
                    }
                }
            }
        } catch (NoSuchElementException e) {
            scanner = new Scanner(System.in);
        }
            finally
         {
            if (scanner != null) {
//                scanner.close();
            }
        }
        return chosenItem;

    }

    /*
    *
    *  while (true) {
            System.out.println("Choose item:");
            String input = "";
            while (!scanner.hasNext()) {
                scanner.next();
            }
            input = scanner.nextLine();
            System.out.println("Read for chosing item");
            if (!input.trim().isEmpty() && input.trim().length() == 1 && !input.equals("")) {
                chosenItem = Character.getNumericValue(input.charAt(0));
                if (chosenItem >= from && chosenItem <= to) {
                    return chosenItem;
                } else {
                    System.out.println("Please, write " + from + "|" + to + " , only numbers.");
                    logger.error("Wrong input: " + chosenItem);
                }
            } else {
                System.out.println("Please, write from " + from + "|" + to + " , only numbers.");
                logger.error("Wrong input: " + input);
            }
        }
    *
    * */

}
