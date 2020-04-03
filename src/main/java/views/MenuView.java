package views;

public class MenuView implements MenuViewTemplate {


    public MenuView() {

        System.out.println("\tChoose your type:");
        System.out.println("1.Create new list");
        System.out.println("2.Open exist file");
        System.out.println("3.Continue current list");

    }

    public int chooseStartAction() {
        System.out.println("Choose menu item:");
        int chosenItem;
        chosenItem = ReadInputUtil.readIntFromInput(1, 3);
        return chosenItem;
    }

    public int goToCurrentView() {
        System.out.println("Choose menu item:");
        int chosenItem;
        chosenItem = ReadInputUtil.readIntFromInput(1, 7);
        return chosenItem;
    }

    @Override
    public String readFilePath() {
        System.out.println("Write file path:");
        String path = ReadInputUtil.readStringFromInput();
        return path;
    }

    @Override
    public void printInfo() {
        System.out.println("\tMenu:");
        System.out.println("1. Add task");
        System.out.println("2. Update task");
        System.out.println("3. Delete task");
        System.out.println("4. Print task list");
        System.out.println("5. Calendar list");
        System.out.println("6. Clean list");
        System.out.println("7. Exit");
    }
}
