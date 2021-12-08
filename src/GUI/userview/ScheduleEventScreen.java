package GUI.userview;

import Helpers.Constants;
import InterfaceAdaptors.DatabaseController;
import GUI.handler.ScheduleEventHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A screen that displays the window viewed by the user
 *
 * === Attributes ===
 * centerPanel: The panel that contains all components locating at the center of the screen
 * southPanel: The panel that contains all components locating at the bottom of the screen
 * eventName: The label for nameTxt
 * nameTxt: The textField for the user to enter the name of event to be scheduled
 * startTime: The label for startTimeBox
 * startTimeBox: The comboBox to allow user to select the start time of the event to be scheduled
 * endTime: The label for endTimeBox
 * endTimeBox: The comboBox to allow user to select the end time of the event to be scheduled
 * location: The label for locationTxt
 * locationTxt: The textField for the user to enter the location of event to be scheduled
 * day: The label for dayBox
 * dayBox: The comboBox to allow user to select the weekday of the event to be scheduled
 * term: The label for termBox
 * termBox: The comboBox to allow user to select the term of the course to be scheduled
 * type: The label for typeBox
 * typeBox: The comboBox to allow user to select the type of the event to be scheduled
 * year: The label for yearTxt
 * yearTxt: The textField for the user to enter the year of event to be scheduled
 * description: The label for descriptionTxt
 * descriptionTxt: The textField for the user to enter the description of event to be scheduled
 * backBtn: The button to close the screen when clicked
 * applyBtn: The button to schedule the event when clicked
 *
 * controller: The DatabaseController for the GUI
 * scheduleEventHandler: The handler to handle actions performed on the screen
 * conflict: Whether there is conflict for selected course. True if there is, false otherwise.
 */
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
    JLabel year = new JLabel("Year");
    JTextField yearTxt = new JTextField();
    JTextField descriptionTxt = new JTextField();
    JLabel description = new JLabel("Description");
    JButton backBtn = new JButton("Back");
    JButton applyBtn = new JButton("Schedule");

    DatabaseController controller;
    ScheduleEventHandler scheduleEventHandler;
    Boolean conflict;

    /**
     * Constructor to set the screen
     * @param controller The DatabaseController for the GUI
     * @param screen The window viewed by the user
     */
    public ScheduleEventScreen(DatabaseController controller, TimeTableScreen screen) {
        super("Schedule NonCourse", screen);
        scheduleEventHandler = new ScheduleEventHandler(this);
        this.controller = controller;
        Container contentPane = getContentPane();

        conflict = false;

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

    /**
     * Sets the frame of the window for display.
     */
    protected void setFrame() {
        // Set size
        setSize(800, 650);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);

        // Visibility
        setVisible(true);
    }

    /**
     * Fills the given comboBox with times to be selected
     * @param timeBox is the comboBox to allow user to select the time of the event to be scheduled
     */
    private void addDropdowntime(JComboBox<String> timeBox) {
        for (String time : Constants.TIME) {
            timeBox.addItem(time);
        }
    }

    /**
     * Fills the given comboBox with weekdays to be selected
     * @param dayBox is the comboBox to allow user to select the weekdays of the event to be scheduled
     */
    private void addWeekday(JComboBox<String> dayBox) {
        for (String weekday : Constants.DAYS_OF_THE_WEEK) {
            dayBox.addItem(weekday);
        }
    }

    /**
     * Fills the given comboBox with terms to be selected
     * @param termBox is the comboBox to allow user to select the term of the event to be scheduled
     */
    private void addTerm(JComboBox<String> termBox){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    /**
     * Fills the given comboBox with types of event to be selected
     * @param typeBox is the comboBox to allow user to select the type of event to be scheduled
     */
    private void addType(JComboBox<String> typeBox){
        typeBox.addItem("Activity");
        typeBox.addItem("Reminder");
    }

    /**
     * Sets whether there is conflict for selected event
     * @param conflict is whether there is conflict for selected event
     */
    public void setConflict(Boolean conflict) {this.conflict = conflict;}

    /**
     * Gets the DatabaseController for the GUI
     * @return the DatabaseController for the GUI
     */
    public DatabaseController getController() {
        return controller;
    }

    /**
     * Gets the name of event to be scheduled
     * @return the name of event to be scheduled
     */
    public String getName(){
        return Objects.requireNonNull(nameTxt.getText());
    }

    /**
     * Gets the start time of event to be scheduled
     * @return the start time of event to be scheduled
     */
    public String getStartTime(){
        return Objects.requireNonNull(startTimeBox.getSelectedItem()).toString();
    }

    /**
     * Gets the end time of event to be scheduled
     * @return the end time of event to be scheduled
     */
    public String getEndTime(){
        return Objects.requireNonNull(endTimeBox.getSelectedItem()).toString();
    }

    /**
     * Gets the location of event to be scheduled
     * @return the location of event to be scheduled
     */
    public String getLocations(){
        return Objects.requireNonNull(locationTxt.getText());
    }

    /**
     * Gets the weekday of event to be scheduled
     * @return the weekday of event to be scheduled
     */
    public String getDate(){
        return Objects.requireNonNull(dayBox.getSelectedItem()).toString();
    }

    /**
     * Gets the term of event to be scheduled
     * @return the term of event to be scheduled
     */
    public String getTerm(){
        return Objects.requireNonNull(termBox.getSelectedItem()).toString();
    }

    /**
     * Gets the year of event to be scheduled
     * @return the year of event to be scheduled
     */
    public String getYear() {return Objects.requireNonNull(yearTxt.getText());}

    /**
     * Gets the type of event to be scheduled
     * @return the type of event to be scheduled
     */
    public String getEventType() {
        return Objects.requireNonNull(typeBox.getSelectedItem()).toString();
    }

    /**
     * Gets the description of event to be scheduled
     * @return the description of event to be scheduled
     */
    public String getDescription() {
        return Objects.requireNonNull(descriptionTxt.getText());
    }

    /**
     * Gets whether there is conflict for selected event
     * @return whether there is conflict for selected event
     */
    public Boolean getConflict() {return conflict;}

    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new ScheduleEventScreen(controller, screen);

    }


}
