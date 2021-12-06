package demoGUI.userview;

import Helpers.Constants;
import InterfaceAdaptors.DatabaseController;
import demoGUI.handler.ScheduleEventHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class ScheduleEventScreen extends AbstractScreen{

    JPanel centerPanel = new JPanel(null);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel eventName = new JLabel("Event Name:");
    JTextField nameTxt = new JTextField();
    JLabel startTime = new JLabel("Start Time");
    JComboBox<String> startTimeBox = new JComboBox<>();
    JLabel endTime = new JLabel("End Time");
    JComboBox<String> endTimeBox = new JComboBox<>();
    JLabel location = new JLabel("Location");
    JTextField locationTxt = new JTextField();
    JLabel day = new JLabel("Day");
    JComboBox<String> dayBox = new JComboBox<>();
    JLabel term = new JLabel("Term");
    JComboBox<String> termBox = new JComboBox<>();
    JLabel type = new JLabel("Type");
    JComboBox<String> typeBox = new JComboBox<>();
    JButton backBtn = new JButton("Back");
    JButton applyBtn = new JButton("Schedule");
    JTextField descriptionTxt = new JTextField();
    JLabel description = new JLabel("Description");
    JLabel year = new JLabel("Year");
    JTextField yearTxt = new JTextField();

    DatabaseController controller;

    ScheduleEventHandler scheduleEventHandler;

    public ScheduleEventScreen(DatabaseController controller, TimeTableScreen screen) {
        super("Schedule NonCourse", screen);
        scheduleEventHandler = new ScheduleEventHandler(this);
        this.controller = controller;
        Container contentPane = getContentPane();

        eventName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        eventName.setBounds(200, 40, 120, 40);
        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameTxt.setBounds(320, 40, 220, 40);

        startTime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        startTime.setBounds(200, 100, 120, 40);
        startTimeBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        startTimeBox.setBounds(320, 100, 120, 40);

        endTime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        endTime.setBounds(200, 160, 120, 40);
        endTimeBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        endTimeBox.setBounds(320, 160, 120, 40);

        location.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        location.setBounds(200, 220, 120, 40);
        locationTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        locationTxt.setPreferredSize(new Dimension(100, 30));
        locationTxt.setBounds(320, 220, 120, 40);

        day.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        day.setBounds(200, 280, 120, 40);
        dayBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dayBox.setBounds(320, 280, 120, 40);

        term.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        term.setBounds(200, 340, 120, 40);
        termBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        termBox.setBounds(320, 340, 120, 40);

        year.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        year.setBounds(200, 400, 120, 40);
        yearTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        yearTxt.setBounds(320, 400, 120, 40);

        type.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        type.setBounds(200, 460, 120, 40);
        typeBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        typeBox.setBounds(320, 460, 120, 40);
        typeBox.setToolTipText("Activity is an event with start time and end time, " +
                "reminder is not since it is a whole day event.");

        description.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        description.setBounds(200, 520, 120, 40);
        descriptionTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        descriptionTxt.setBounds(320, 520, 400, 40);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(scheduleEventHandler);

        // Apply button
        applyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        applyBtn.addActionListener(scheduleEventHandler);

        // Dropdown menu
        addDropdowntime(startTimeBox);
        addDropdowntime(endTimeBox);
        addWeekday(dayBox);
        addTerm(termBox);
        addType(typeBox);


        centerPanel.add(eventName);
        centerPanel.add(nameTxt);
        centerPanel.add(startTime);
        centerPanel.add(startTimeBox);
        centerPanel.add(endTime);
        centerPanel.add(endTimeBox);
        centerPanel.add(location);
        centerPanel.add(locationTxt);
        centerPanel.add(day);
        centerPanel.add(dayBox);
        centerPanel.add(term);
        centerPanel.add(termBox);
        centerPanel.add(year);
        centerPanel.add(yearTxt);
        centerPanel.add(type);
        centerPanel.add(typeBox);
        centerPanel.add(description);
        centerPanel.add(descriptionTxt);


        southPanel.add(backBtn);
        southPanel.add(applyBtn);


        contentPane.add(centerPanel);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        setFrame();

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    protected void setFrame() {
        // Window's icon
        URL resource = OperatorScreen.class.getClassLoader().getResource("pic2.jpg");
        assert resource != null;
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);


        // Set size
        setSize(800, 650);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);

        // Visibility
        setVisible(true);
    }

    private void addDropdowntime(JComboBox<String> Timebox) {
        for (String time : Constants.TIME) {
            Timebox.addItem(time);
        }
    }

    private void addWeekday(JComboBox<String> dayBox) {
        for (String weekday : Constants.DAYS_OF_THE_WEEK) {
            dayBox.addItem(weekday);
        }
    }

    private void addTerm(JComboBox<String> termBox){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    private void addType(JComboBox<String> typeBox){
        typeBox.addItem("Activity");
        typeBox.addItem("Reminder");
    }

    public DatabaseController getController() {
        return controller;
    }

    public String getName(){
        return Objects.requireNonNull(nameTxt.getText());
    }

    public String getStartTime(){
        return Objects.requireNonNull(startTimeBox.getSelectedItem()).toString();
    }

    public String getEndTime(){
        return Objects.requireNonNull(endTimeBox.getSelectedItem()).toString();
    }

    public String getLocations(){
        return Objects.requireNonNull(locationTxt.getText());
    }

    public String getDate(){
        return Objects.requireNonNull(dayBox.getSelectedItem()).toString();
    }

    public String getTerm(){
        return Objects.requireNonNull(termBox.getSelectedItem()).toString();
    }

    public String getYear() {return Objects.requireNonNull(yearTxt.getText());}

    public String getEventType() {
        return Objects.requireNonNull(typeBox.getSelectedItem()).toString();
    }

    public String getDescription() {
        return Objects.requireNonNull(descriptionTxt.getText());
    }

    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new ScheduleEventScreen(controller, screen);

    }


}
