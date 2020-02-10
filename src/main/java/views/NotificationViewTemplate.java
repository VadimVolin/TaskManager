package views;

import models.AbstractTaskList;
import models.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public interface NotificationViewTemplate {

    void printTaskLsit(AbstractTaskList abstractTaskList);

}
