package contorllers;

import models.AbstractTaskList;
import models.ArrayTaskList;
import models.Tasks;
import org.apache.log4j.Logger;
import views.NotificationView;
import views.NotificationViewTemplate;

import java.time.LocalDateTime;

public class NotificationController implements Runnable {
    private final static Logger logger = Logger.getLogger(CalendarController.class);
    private Thread thread;
    private AbstractTaskList taskList;
    private NotificationViewTemplate notificationView;

    public NotificationController(AbstractTaskList abstractTaskList) {
        taskList = abstractTaskList;
        notificationView = new NotificationView();

        thread = new Thread(this, "notificationController");
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            ArrayTaskList tasks = getIntervalTasks(LocalDateTime.now());
            if (!(tasks.size() <= 0)) {
                try {
                    thread.sleep(120_000);
                } catch (InterruptedException e) {
                    logger.error("Controller notification interrupt ", e);
                }
                logger.info("- Notification is found -");
                notificationView.printTaskLsit(tasks);
                tasks = null;
            } else {
                try {
                    thread.sleep(60000);
                } catch (InterruptedException e) {
                    logger.error("Controller notification interrupt ", e);
                }
            }
        }
    }

    public void setTaskList(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    private synchronized ArrayTaskList getIntervalTasks(LocalDateTime now) {
        ArrayTaskList arrayTaskList = (ArrayTaskList) Tasks.incoming(taskList, now, LocalDateTime.now().plusDays(1));
        return arrayTaskList;
    }
}
