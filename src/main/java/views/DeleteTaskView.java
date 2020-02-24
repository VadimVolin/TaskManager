package views;

import models.AbstractTaskList;
import models.Task;
import util.ReadInputUtil;

public class DeleteTaskView implements DeleteTaskViewTemplate {


    public DeleteTaskView() {

        printStartInfo();

    }

    public void printStartInfo() {
        System.out.println("\t Update task:");
    }

    public void printDeleteList(AbstractTaskList abstractTaskList) {
        int i = 0;
        for (Task task : abstractTaskList) {
            i++;
            System.out.println(i + ". " + task);
        }
    }

    public int readChoosingTask(int from, int to) {
        System.out.println("Choose item for delete:");
        int index = ReadInputUtil.readIntFromInput(from, to);
        return index;
    }


}
