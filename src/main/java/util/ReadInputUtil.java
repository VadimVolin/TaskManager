package util;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.TaskIO;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadInputUtil{
    final static Logger logger = Logger.getLogger(ReadInputUtil.class);

    public static void saveListToFile(AbstractTaskList taskList, File fileTasks) {
        if (!fileTasks.exists()) {
            logger.error("File not found in path, create new file");
            fileTasks = new File("tasks.json");
        }
        try {
            TaskIO.writeText(taskList, fileTasks);

        } catch (IOException e) {
            logger.error("IOException in read from file", e);
        }
    }

    public static AbstractTaskList getTaskListFromFile(File fileTasks) {
        AbstractTaskList taskList = new ArrayTaskList();
        if (!fileTasks.exists()) {
            System.out.println("File not found");
            logger.error("File not found in path");
            return taskList;
        }
        try {
            TaskIO.readText(taskList, fileTasks);
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
                System.out.println("Input string:");
                input = scanner.nextLine();
                if (!input.trim().isEmpty()) {
                    inputFlag = false;
                } else {
                    System.out.println("Wrong value.. string is empty");
                    logger.error("Wrong input: string is empty :" + input);
                }
        }
        return input;
    }

    public static String readDateString(Scanner scanner) {
        String input = "";
        boolean inputFlag = true;
        while (inputFlag) {
                System.out.println("Input date: ");
                input = scanner.nextLine();
                if (input.matches("(\\d{4})-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$")) {
                    if (!input.trim().isEmpty()) {
                        inputFlag = false;
                    } else {
                        System.out.println("Wrong value.. string is empty");
                        logger.error("Wrong input: string is empty :" + input);
                    }
                } else {
                    System.out.println("Wrong value.. wrong format");
                    logger.error("Wrong input: wrong format:" + input);
                }
            }
        return input;
    }

    public static int readIntFromInput(Scanner scanner, int from, int to) {
        int chosenItem = 0;
        boolean inputFlag = true;
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
        return chosenItem;

    }





}