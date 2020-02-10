package contorllers;

import models.Task;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public interface CalendarControllerTemplate {

    void initDateStart();
    void initDateEnd();
    void initTaskList();
    SortedMap<LocalDateTime, Set<Task>> getCalendar();
    boolean saveCalendarToFile(File file);

}
