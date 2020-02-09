package views;

import models.AbstractTaskList;
import models.Task;

import java.util.Scanner;

public class ShowListView {

    public ShowListView() {

    }

    public void printTaskList(AbstractTaskList abstractTaskList) {
        if (abstractTaskList.size() == 0) {
            System.out.println("List is empty..");
        } else {
            for (Task task : abstractTaskList) {
                System.out.println(task);
            }
        }
    }


}
