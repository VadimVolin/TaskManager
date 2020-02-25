package views;

import java.time.LocalDateTime;

public interface CalendarViewTemplate {

    void printStartInfo();

    void printVariantInfo();

    String readFileNameForSave();

    void showCalendar(String calendarMap);

    int getChooseVariant();

    LocalDateTime readDateStart();

    LocalDateTime readDateEnd(LocalDateTime timeStart);
}
