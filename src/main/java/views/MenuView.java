package views;

import org.apache.log4j.Logger;

public class MenuView {

    final static Logger logger = Logger.getLogger(MenuView.class);

    public MenuView() {
        System.out.println("\tMenu:");
        System.out.println("1. Add task");
        System.out.println("2. Update task");
        System.out.println("3. Delete task");
        System.out.println("4. Print task list");
        System.out.println("5. Exit");
    }

}
