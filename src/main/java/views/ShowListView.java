package views;

import models.AbstractTaskList;
import models.Task;

public class ShowListView implements ShowListViewTemplate{

    public ShowListView() {

    }

    public void printTaskList(String taskList) {
        System.out.println(taskList);
    }


}
