import contorllers.AddTaskController;
import contorllers.AddTaskTemplate;
import models.ArrayTaskList;
import models.Task;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TestAdded {

    @Test
    public void testAddTask() {

        StringTokenizer scanner = new StringTokenizer("sadsadasd&2020-12-12 20:12&2020-12-12 23:12&152365", "&");
        String[] taskStrings = new String[scanner.countTokens()];
        int i = 0;
        while (scanner.hasMoreTokens()) {
            taskStrings[i] = scanner.nextToken();
            i++;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        switch (taskStrings.length) {
            case 2:
                LocalDateTime time = LocalDateTime.parse(taskStrings[1], formatter);
                System.out.println(new Task(taskStrings[0], time));
                break;
            case 4:
                LocalDateTime start = LocalDateTime.parse(taskStrings[1], formatter);
                LocalDateTime end = LocalDateTime.parse(taskStrings[2], formatter);
               int interval = Integer.parseInt(taskStrings[3]);
                System.out.println(new Task(taskStrings[0], start, end, interval));
                break;
        }
    }

}
