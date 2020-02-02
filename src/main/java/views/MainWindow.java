package views;

import models.Task;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class MainWindow extends JFrame {

    JPanel btnPanel;
    JButton addBtn;
    JButton deleteBtn;
    JButton updateBtn;

    JPanel listPanel;
    JList<Task> taskList;

    public MainWindow() {
        super("TaskManager");
        setLocation(200, 150);
        setSize(620, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        addBtn = new JButton("Add task");
        deleteBtn = new JButton("Delete task");
        updateBtn = new JButton("Update task");

        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(updateBtn);
        btnPanel.setAlignmentX(Component.TOP_ALIGNMENT);


        listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        taskList = new JList<>();
        taskList.setListData(new Task[] {
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now()),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now(), LocalDateTime.now(), 15),
                new Task("Test Task", LocalDateTime.now())
        });

        taskList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        taskList.setVisibleRowCount(20);
        JScrollPane listScroller = new JScrollPane(taskList);
        listScroller.setPreferredSize(new Dimension(600, 400));
        listPanel.add(listScroller);
        listPanel.setAlignmentX(Component.TOP_ALIGNMENT);


        Container mainContainer = getContentPane();
        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainContainer.add(btnPanel);
        mainContainer.add(listPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }

}