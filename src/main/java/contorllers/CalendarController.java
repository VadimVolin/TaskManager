package contorllers;

import models.*;
import org.apache.log4j.Logger;
import views.CalendarView;
import views.CalendarViewTemplate;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class CalendarController implements CalendarControllerTemplate {
    public static final int EXIT = 0;
    public static final int RETURN = 1;
    public static final int SAVE_TO_FILE = 2;
    private final static Logger logger = Logger.getLogger(CalendarController.class);

    private AbstractTaskList taskList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SortedMap<LocalDateTime, Set<Task>> calendarTasks;

    private CalendarViewTemplate calendarView;

    public CalendarController(AbstractTaskList abstractTaskList) {
        calendarView = new CalendarView();
        taskList = abstractTaskList;

    }

    public void calendar() {
        if (taskList.size() > 0) {
            calendarView.printStartInfo();
            initDateStart();
            initDateEnd();
            logger.info("init taskList, size = " + taskList.size());
            calendarTasks = getCalendar();
            logger.info("init calendar, size = " + calendarTasks.size());

            StringBuffer calendarString = new StringBuffer();
            for (Set<Task> value : calendarTasks.values()) {
                for (Task task : value) {
                    calendarString.append(task + "\n");
                }
                calendarString.append("-------------------------------------------------------------------------\n");
            }

            calendarView.showCalendar(calendarString.toString());
            calendarView.printVariantInfo();
            int choosingVariant = calendarView.getChooseVariant();
            logger.info("user choose:" + choosingVariant);
            actionFinish(choosingVariant);
        } else {
            System.out.println("List is empty");
            logger.info("List is empty");
        }
    }

    public AbstractTaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    private void actionFinish(int item) {
        switch (item) {
            case EXIT:
                System.out.println("Exit!");
                logger.info("Exit");
                System.exit(0);
                break;
            case RETURN:
                System.out.println("Return to menu");
                logger.info("Return to menu");
                break;
            case SAVE_TO_FILE:
                System.out.println("Saving..");
                String fileName = calendarView.readFileNameForSave();
                File file = new File(fileName + ".txt");
                logger.info("create file - " + fileName + ".txt");
                boolean flag = saveCalendarToFile(file);
                if (flag) {
                    System.out.println("File " + fileName + " saved!");
                    logger.info("File " + fileName + " saved.");
                    break;
                } else {
                    System.out.println("File " + fileName + " not saved!");
                    logger.info("File " + fileName + " not saved.");
                    System.out.println("return to menu..");
                    break;
                }
        }
    }

    @Override
    public void initDateStart() {
        startTime = calendarView.readDateStart();
    }

    @Override
    public void initDateEnd() {
        endTime = calendarView.readDateEnd(startTime);
    }

    @Override
    public SortedMap<LocalDateTime, Set<Task>> getCalendar() {
        return Tasks.calendar(taskList, startTime, endTime);
    }

    @Override
    public boolean saveCalendarToFile(File file) {
        if (calendarTasks.size() <= 0) {
            return false;
        }
        AbstractTaskList myList = new ArrayTaskList(calendarTasks.size() * 5);
        for (Set<Task> value : calendarTasks.values()) {
            Iterator<Task> iterator = value.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task != null) {
                    myList.add(task);
                }
            }
        }
        if (myList == null || myList.size() == 0) {
            logger.info("List size 0");
            System.out.println("List empty.. return");
            return false;
        }
        try {
            TaskIO.writeText(myList, file);
            return true;
        } catch (IOException e) {
            logger.error("error write", e);
        }
        return false;
    }


}
