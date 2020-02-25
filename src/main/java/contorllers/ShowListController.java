package contorllers;

import models.AbstractTaskList;
import views.ShowListView;
import views.ShowListViewTemplate;

public class ShowListController {

    ShowListViewTemplate showListView;
    AbstractTaskList arrayTaskList;

    public ShowListController(AbstractTaskList abstractTaskList) {
        showListView = new ShowListView();
        arrayTaskList = abstractTaskList;

        StringBuffer listString = new StringBuffer();
        for (int i = 0; i < arrayTaskList.size(); i++) {
            listString.append("" + (i + 1) + ". " + arrayTaskList.getTask(i) + "\n");
        }


        showListView.printTaskList(listString.toString());
    }

}
