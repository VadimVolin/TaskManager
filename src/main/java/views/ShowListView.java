package views;

import models.AbstractTaskList;
import models.Task;

public class ShowListView implements ShowListViewTemplate{

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
