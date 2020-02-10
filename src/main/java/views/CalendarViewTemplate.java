package views;

import models.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public interface CalendarViewTemplate {

    void printStartInfo();
    void printVariantInfo();
    String readFileNameForSave();
    void showCalendar(SortedMap<LocalDateTime, Set<Task>> calendarMap);
    int getChooseVariant();
    LocalDateTime readDateStart();
    LocalDateTime readDateEnd(LocalDateTime timeStart);
}
