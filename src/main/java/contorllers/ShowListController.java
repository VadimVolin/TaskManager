package contorllers;

import models.AbstractTaskList;
import views.ShowListView;
import views.ShowListViewTemplate;

public class ShowListController implements ShowListTemplate{

    ShowListViewTemplate showListView;
    AbstractTaskList arrayTaskList;

    public ShowListController(AbstractTaskList abstractTaskList) {
        showListView = new ShowListView();
        arrayTaskList = abstractTaskList;
        showListView.printTaskList(arrayTaskList);
    }

}
