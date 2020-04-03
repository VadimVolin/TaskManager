package views;

public interface DeleteTaskViewTemplate {

    void printDeleteList(String taskLst);

    int readChoosingTask(int from, int to);

    void printStartInfo();

}
