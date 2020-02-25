package views;

import models.AbstractTaskList;
import models.Task;
import util.ReadInputUtil;

public class DeleteTaskView implements DeleteTaskViewTemplate {


    public DeleteTaskView() {

        printStartInfo();

    }

    public void printStartInfo() {
        System.out.println("\t Delete task:");
    }

    public void printDeleteList(String taskList) {
        System.out.println(taskList);
    }

    public int readChoosingTask(int from, int to) {
        System.out.println("Choose item for delete:");
        int index = ReadInputUtil.readIntFromInput(from, to);
        return index;
    }


}
