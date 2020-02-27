package models;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class ReadInputUtil {
    private final static Logger logger = Logger.getLogger(ReadInputUtil.class);

    public static Scanner getScanner() {
        return scanner;
    }

    private static Scanner scanner = new Scanner(System.in);

    public static String readPathInfoFromCache() {
        File file = new File("cache.dat");
        String path = "";
        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                dataInputStream = new DataInputStream(fileInputStream);
                path = dataInputStream.readUTF();
            } catch (FileNotFoundException e) {
                logger.error(e);
            } catch (IOException e) {
                logger.error(e);
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return path;
    }

    public static void savePathInfoToCache(String path) {
        File file = new File("cache.dat");
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(path);
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
            return new ArrayTaskList();
        }
        try {
            TaskIO.readText(taskList, fileTasks);
        } catch (IOException e) {
            logger.error("IOException in read from file", e);
        } catch (ParseException e) {
            logger.error("Parse Exception in read from file", e);
        }
        if (taskList == null) {
            taskList = new ArrayTaskList();
        }
        return taskList;
    }

    public static String readStringFromInput() {
        String input = "";
        boolean inputFlag = true;
        while (inputFlag) {
            if (!scanner.hasNext()) {
                input = scanner.nextLine();
                logger.error("User input: " + input);
            } else {
                input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println("Wrong value.. string is empty");
                    logger.error("Wrong input: string is empty :" + input);
                } else {
                    inputFlag = false;
                }
            }
        }
        return input;
    }

    public static String readDateString() {

        String input = "";
        boolean inputFlag = true;
        while (inputFlag) {
            if (!scanner.hasNextLine()) {
                String wrongInput = scanner.next();
                System.out.println("Wrong value.. ");
                logger.error("Wrong input:" + wrongInput);
            } else {
                input = scanner.nextLine();
                if (input.matches("(\\d{4})-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$")) {
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
        }
        return input;
    }

    public static int readIntFromInput(int from, int to) {

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
