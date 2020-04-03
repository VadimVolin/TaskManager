package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Tasks;
import org.apache.log4j.Logger;
import views.NotificationView;
import views.PrintListViewTemplate;

import java.time.LocalDateTime;

public class NotificationController implements Runnable {
    private final static Logger logger = Logger.getLogger(CalendarController.class);
    private Thread thread;
    private AbstractTaskList taskList;
    private PrintListViewTemplate notificationView;

    public NotificationController(AbstractTaskList abstractTaskList) {
        taskList = abstractTaskList;
        notificationView = new NotificationView();

        thread = new Thread(this, "notificationController");
        thread.setDaemon(true);
        thread.start();
    }

    public AbstractTaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void run() {
        while (true) {
            ArrayTaskList tasks = getIntervalTasks(LocalDateTime.now());
            if (!(tasks.size() <= 0)) {
                try {
                    Thread.sleep(120_000);
                } catch (InterruptedException e) {
                    logger.error("Controller notification interrupt ", e);
                }
                logger.info("- Notification is found -");

                StringBuffer listString = new StringBuffer();
                for (int i = 0; i < tasks.size(); i++) {
                    listString.append("" + (i + 1) + ". " + tasks.getTask(i) + "\n");
                }
                notificationView.printTaskLsit(listString.toString());
            } else {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    logger.error("Controller notification interrupt ", e);
                }
            }
        }
    }

    private synchronized ArrayTaskList getIntervalTasks(LocalDateTime now) {
        ArrayTaskList arrayTaskList = (ArrayTaskList) Tasks.incoming(taskList, now, LocalDateTime.now().plusDays(1));
        return arrayTaskList;
    }
}
