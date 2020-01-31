package views;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("TaskManager");
        setBounds(150, 150, 800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
        System.out.println("helloss");
    }

}
