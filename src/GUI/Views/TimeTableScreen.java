package GUI.Views;

import GUI.Controllers.TimeTableScreenController;

import javax.swing.*;

public class TimeTableScreen extends JFrame{
    private JPanel TimeTableView;
    private JButton courseButton;
    private JButton taskActivityButton;
    private JButton solverButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton backButton;
    TimeTableScreenController timeTableScreenController;

    public TimeTableScreen() {
        super();

        timeTableScreenController = new TimeTableScreenController(this);
        this.setContentPane(TimeTableView);

        // add button functions
        courseButton.addActionListener(timeTableScreenController);
        taskActivityButton.addActionListener(timeTableScreenController);
        solverButton.addActionListener(timeTableScreenController);
        saveButton.addActionListener(timeTableScreenController);
        loadButton.addActionListener(timeTableScreenController);
        backButton.addActionListener(timeTableScreenController);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TimeTableView");
        frame.setContentPane(new TimeTableScreen().TimeTableView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,900);
        frame.pack();
        frame.setVisible(true);
    }
}
