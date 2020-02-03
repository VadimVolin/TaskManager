package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskView extends JFrame {

    JButton oneTimeTaskBtn = new JButton("One time task");
    JButton repeatTimeTaskBtn = new JButton("Repeat task");

    JButton addTaskBtn = new JButton("Add to list");

    public AddTaskView() {
        super("Add task panel");
        setBounds(500, 500, 300, 60);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(oneTimeTaskBtn);
        container.add(repeatTimeTaskBtn);
        oneTimeTaskBtn.setHorizontalAlignment(SwingConstants.CENTER);
        repeatTimeTaskBtn.setHorizontalAlignment(SwingConstants.CENTER);

        JFrame currentFrame = this;

        oneTimeTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.remove(oneTimeTaskBtn);
                container.remove(repeatTimeTaskBtn);
                container.add(getOneTimeTaskPane());
                currentFrame.setBounds(500,500, 400, 150);
            }
        });

        repeatTimeTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.remove(oneTimeTaskBtn);
                container.remove(repeatTimeTaskBtn);
                container.add(getRepeatTimeTaskPane());
                currentFrame.setBounds(500,500, 400, 300);
            }
        });

    }

    public JPanel getOneTimeTaskPane() {
        JPanel jPanel = new JPanel();
        JLabel textLabel = new JLabel("Task test");
        JTextField taskText = new JTextField();
        JLabel timeLabel = new JLabel("Task test");
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner timeTask = new JSpinner(dateModel);
        ((JSpinner.DefaultEditor)timeTask.getEditor()).getTextField().setEditable(false);
        jPanel.setLayout(new GridLayout(3, 2, 10, 10));
        jPanel.add(textLabel);
        jPanel.add(taskText);
        jPanel.add(timeLabel);
        jPanel.add(timeTask);
        jPanel.add(addTaskBtn);
        return jPanel;
    }

    public JPanel getRepeatTimeTaskPane() {
        JPanel jPanel = new JPanel();

        JLabel textLabel = new JLabel("Task text");
        JTextField taskText = new JTextField();

        JLabel timestartLabel = new JLabel("Time start");
        SpinnerDateModel dateStartModel = new SpinnerDateModel();
        JSpinner timeStartTask = new JSpinner(dateStartModel);
        ((JSpinner.DefaultEditor)timeStartTask.getEditor()).getTextField().setEditable(false);

        JLabel timeEndLabel = new JLabel("Time start");
        SpinnerDateModel dateEndModel = new SpinnerDateModel();
        JSpinner timeEndTask = new JSpinner(dateEndModel);
        ((JSpinner.DefaultEditor)timeEndTask.getEditor()).getTextField().setEditable(false);

        JLabel intervalInMunutesLabel = new JLabel("Interval in munutes");
        SpinnerModel model = new SpinnerNumberModel(1, 1, 60, 1);
        JSpinner minutesInterval = new JSpinner(model);
        JComponent minutesEditor = new JSpinner.NumberEditor(minutesInterval);
        minutesInterval.setEditor(minutesEditor);
        ((JSpinner.DefaultEditor)minutesInterval.getEditor()).getTextField().setEditable(false);

        JLabel intervalHoursLabel = new JLabel("Interval in hours");
        SpinnerModel hoursModel = new SpinnerNumberModel(0, 0, 24, 1);
        JSpinner hoursInterval = new JSpinner(hoursModel);
        JComponent hoursEditor = new JSpinner.NumberEditor(hoursInterval);
        hoursInterval.setEditor(hoursEditor);
        ((JSpinner.DefaultEditor)hoursInterval.getEditor()).getTextField().setEditable(false);

        JLabel intervalDaysLabel = new JLabel("Interval in days");
        SpinnerModel daysModel = new SpinnerNumberModel(0, 0, 31, 1);
        JSpinner daysInterval = new JSpinner(daysModel);
        JComponent daysEditor = new JSpinner.NumberEditor(daysInterval);
        daysInterval.setEditor(daysEditor);
        ((JSpinner.DefaultEditor)daysInterval.getEditor()).getTextField().setEditable(false);

        jPanel.setLayout(new GridLayout(7, 2, 10, 10));
        jPanel.add(textLabel);
        jPanel.add(taskText);
        jPanel.add(timestartLabel);
        jPanel.add(timeStartTask);
        jPanel.add(timeEndLabel);
        jPanel.add(timeEndTask);
        jPanel.add(intervalInMunutesLabel);
        jPanel.add(minutesInterval);
        jPanel.add(intervalHoursLabel);
        jPanel.add(hoursInterval);
        jPanel.add(intervalDaysLabel);
        jPanel.add(daysInterval);
        jPanel.add(addTaskBtn);
        return jPanel;
    }

    private void closeOperation() {
        dispose();
    }

}
