package views;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class MenuView {

    Scanner userInput;
    final static Logger logger = Logger.getLogger(MenuView.class);

    public MenuView() {
        userInput = new Scanner(System.in);
        System.out.println("\tMenu:");
        System.out.println("1. Add task");
        System.out.println("2. Update task");
        System.out.println("3. Delete task");
        System.out.println("4. Print task list");
        System.out.println("5. Exit");

        int chosenItem = readIntFromInput(userInput, 1, 5);

        goToCurrentView(chosenItem);

        userInput.close();
    }

    public void goToCurrentView(int chosenItem) {
        Scanner actionScanner = new Scanner(System.in);
        switch (chosenItem) {
            case 1:
                logger.info("User choose Add task");
                AddTaskView addTaskView = new AddTaskView();
                finishAction(actionScanner);
            case 2:
                logger.info("User choose Update task");
                System.out.println("Update List");
                finishAction(actionScanner);
            case 3:
                logger.info("User choose Delete task");
                System.out.println("Delete task");
                finishAction(actionScanner);
            case 4:
                logger.info("User choose Print task list");
                System.out.println("Print task list");
                finishAction(actionScanner);
            case 5:
                logger.info("User choose exit");
                System.out.println("Exit");
                System.exit(0);
        }
    }

    public void finishAction(Scanner scanner) {
        System.out.println("Return to menu 1 | exit 0");
        int chosenVariant = readIntFromInput(scanner, 0, 1);
        switch (chosenVariant) {
            case 1:
                logger.info("User choose go to main menu");
                MenuView menuView = new MenuView();
            case 2:
                logger.info("User choose exit");
                System.exit(0);
        }
    }

    public int readIntFromInput(Scanner scanner, int from, int to) {
        int chosenItem;
        while(true) {
            System.out.println("Choose item:");
            String input = userInput.nextLine().trim();
            if (!input.isEmpty() && input.length() == 1) {
                chosenItem = Character.getNumericValue(input.charAt(0));
                if (chosenItem >= from && chosenItem <= to) {
                    break;
                } else {
                    System.out.println("Please, write " + from + "|" + to+ " , only numbers.");
                    logger.error("Wrong input: " + chosenItem);
                }
            } else {
                System.out.println("Please, write from " + from + "|" + to+ " , only numbers.");
                logger.error("Wrong input: " + input);
            }
        }
        return chosenItem;
    }

}
