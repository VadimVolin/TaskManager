package models;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            try {
                Task task = iterator.next();
                if (task.nextTimeAfter(start).isAfter(end)) {
                    iterator.remove();
                }
            } catch (NullPointerException e) {
                iterator.remove();
            }
        }
        return tasks;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendarMap = new TreeMap<>();
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            arrayTaskList.add(iterator.next());
        }
        for (LocalDateTime i = start; i.isBefore(end); i = i.plusDays(1)) {
            Set<Task> taskSet = new HashSet<>();
            ArrayTaskList incomingList =
                    (ArrayTaskList) arrayTaskList.incoming(i, i.plusDays(1));
            for (int j = 0; j < incomingList.size(); j++) {
                taskSet.add(incomingList.getTask(j));
            }
            if (!taskSet.isEmpty()) {
                calendarMap.put(
                        incomingList.getTask(0).nextTimeAfter(i),
                        taskSet
                );
            }
        }
        return calendarMap;
    }

}
