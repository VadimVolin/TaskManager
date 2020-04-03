package models;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.*;

public class FileIO {
    private final static Logger logger = Logger.getLogger(FileIO.class);

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
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (IOException e) {
                    logger.error("Error with i/o: ", e);
                }
                try {
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                } catch (IOException e) {
                    logger.error("Error with i/o: ", e);
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
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                logger.error("Error with i/o: ", e);
            }
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                logger.error("Error with i/o: ", e);
            }
        }
    }

    public static void saveListToFile(AbstractTaskList taskList, File fileTasks) {
        if (!fileTasks.exists()) {
            logger.info("File not found in path, create new file");
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
            logger.info("File not found in path");
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


}
