package views;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class ReadInputUtil {
    private final static Logger logger = Logger.getLogger(ReadInputUtil.class);
    private static Scanner scanner = new Scanner(System.in);

    public static void closeResource() {
        try {
            scanner.close();
        } catch (Exception e) {
            logger.error("Error on closing Scanner: ", e);
        }
    }

    public static String readStringFromInput() {
        String input = "";
        while (true) {
            input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                break;
            } else {
                System.out.println("String is empty");
                logger.info("Wrong input: string is empty :" + input);
            }
        }
        return input;
    }

    // попробовать проверить перед скипом
    public static String readDateString() {
        String input = "";
        while (true) {
            input = readStringFromInput();
            if (input.matches("(\\d{4})-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$")) {
                break;
            } else {
                System.out.println("Wrong format of date");
                logger.info("Wrong input: wrong format:" + input);
            }
        }
        return input;
    }

    public static int readIntFromInput(int from, int to) {

        int chosenItem = 0;
        boolean inputFlag = true;
        while (inputFlag) {
            String input = readStringFromInput();
            try {
                chosenItem = Integer.parseInt(input);
                if (chosenItem >= from && chosenItem <= to) {
                    break;
                } else {
                    System.out.printf("Input integer numbers from interval [%d ; %d]\n", from, to);
                    logger.info("Wrong input: " + chosenItem);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Input integer numbers from interval [%d ; %d]\n", from, to);
                logger.error("Not number in input:", e);
            }
        }
        return chosenItem;

    }
}
