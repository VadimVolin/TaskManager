package contorllers;

import models.AbstractTaskList;
import models.Task;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public interface CalendarControllerTemplate {

    void initDateStart();

    void initDateEnd();

    SortedMap<LocalDateTime, Set<Task>> getCalendar();

    boolean saveCalendarToFile(File file);

    AbstractTaskList getTaskList();

    void setTaskList(AbstractTaskList taskList);

    void calendar();

}
