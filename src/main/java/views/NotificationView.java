package views;

import models.AbstractTaskList;
import models.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class NotificationView implements NotificationViewTemplate {
    @Override
    public void printTaskLsit(AbstractTaskList abstractTaskList) {
        System.out.println("------------------------Notification-----------------------");
        for (Task task : abstractTaskList) {
            System.out.println(task);
        }
        System.out.println("-----------------------------------------------------------");
    }

}
