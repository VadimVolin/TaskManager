package views;

import org.apache.log4j.Logger;

public class MenuView implements MenuViewTemplate{


    public MenuView() {
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
