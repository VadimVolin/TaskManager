package contorllers;

import models.AbstractTaskList;
import views.PrintListViewTemplate;
import views.ShowListView;

public class ShowListController {

    private PrintListViewTemplate showListView;
    private AbstractTaskList arrayTaskList;

    public ShowListController(AbstractTaskList abstractTaskList) {
        showListView = new ShowListView();
        arrayTaskList = abstractTaskList;


    }

    public void show() {
        StringBuffer listString = new StringBuffer();
        for (int i = 0; i < arrayTaskList.size(); i++) {
            listString.append("" + (i + 1) + ". " + arrayTaskList.getTask(i) + "\n");
        }

        showListView.printTaskLsit(listString.toString());
    }

    public AbstractTaskList getArrayTaskList() {
        return arrayTaskList;
    }

    public void setArrayTaskList(AbstractTaskList arrayTaskList) {
        this.arrayTaskList = arrayTaskList;
    }
}
