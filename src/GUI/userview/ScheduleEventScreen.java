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
 */
public class ScheduleEventScreen extends AbstractScreen{
    private static final JPanel centerPanel = new JPanel(null);
    private static final JPanel southPanel =
            new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private static final JLabel eventName = new JLabel("Event Name:");
    private static final JTextField nameTxt = new JTextField();
    private static final JLabel startTime = new JLabel("Start Time");
    private static final JComboBox<String> startTimeBox = new JComboBox<>();
    private static final JLabel endTime = new JLabel("End Time");
    private static final JComboBox<String> endTimeBox = new JComboBox<>();
    private static final JLabel location = new JLabel("Location");
    private static final JTextField locationTxt = new JTextField();
    private static final JLabel day = new JLabel("Day");
    private static final JComboBox<String> dayBox = new JComboBox<>();
    private static final JLabel term = new JLabel("Term");
    private static final JComboBox<String> termBox = new JComboBox<>();
    private static final JLabel type = new JLabel("Type");
    private static final JComboBox<String> typeBox = new JComboBox<>();
    private static final JLabel year = new JLabel("Year");
    private static final JTextField yearTxt = new JTextField();
    private static final JTextField descriptionTxt = new JTextField();
    private static final JLabel description = new JLabel("Description");
    private static final JButton backBtn = new JButton("Back");
    private static final JButton applyBtn = new JButton("Schedule");

    ScheduleEventHandler scheduleEventHandler;

    /**
     * Constructor to set the screen
     * @param controller The DatabaseController for the GUI
     * @param screen The window viewed by the user
     */
    public ScheduleEventScreen(DatabaseController controller, TimeTableScreen screen) {
        super("Schedule NonCourse", screen);
        scheduleEventHandler = new ScheduleEventHandler(this);
        Container contentPane = getContentPane();

        eventName.setFont(Constants.FONT20);
        eventName.setBounds(200, 40, 120, 40);
        nameTxt.setFont(Constants.FONT15);
        nameTxt.setBounds(320, 40, 220, 40);

        startTime.setFont(Constants.FONT20);
        startTime.setBounds(200, 100, 120, 40);
        startTimeBox.setFont(Constants.FONT20);
        startTimeBox.setBounds(320, 100, 120, 40);

        endTime.setFont(Constants.FONT20);
        endTime.setBounds(200, 160, 120, 40);
        endTimeBox.setFont(Constants.FONT20);
        endTimeBox.setBounds(320, 160, 120, 40);

        location.setFont(Constants.FONT20);
        location.setBounds(200, 220, 120, 40);
        locationTxt.setFont(Constants.FONT15);
        locationTxt.setPreferredSize(new Dimension(100, 30));
        locationTxt.setBounds(320, 220, 120, 40);

        day.setFont(Constants.FONT20);
        day.setBounds(200, 280, 120, 40);
        dayBox.setFont(Constants.FONT20);
        dayBox.setBounds(320, 280, 120, 40);

        term.setFont(Constants.FONT20);
        term.setBounds(200, 340, 120, 40);
        termBox.setFont(Constants.FONT20);
        termBox.setBounds(320, 340, 120, 40);

        year.setFont(Constants.FONT20);
        year.setBounds(200, 400, 120, 40);
        yearTxt.setFont(Constants.FONT20);
        yearTxt.setBounds(320, 400, 120, 40);

        type.setFont(Constants.FONT20);
        type.setBounds(200, 460, 120, 40);
        typeBox.setFont(Constants.FONT20);
        typeBox.setBounds(320, 460, 120, 40);
        typeBox.setToolTipText("Activity is an event with start time and end time, " +
                "reminder is not since it is a whole day event.");

        description.setFont(Constants.FONT20);
        description.setBounds(200, 520, 120, 40);
        descriptionTxt.setFont(Constants.FONT15);
        descriptionTxt.setBounds(320, 520, 400, 40);

        // Back button
        backBtn.setFont(Constants.FONT20);
        backBtn.addActionListener(scheduleEventHandler);

        // Apply button
        applyBtn.setFont(Constants.FONT20);
        applyBtn.addActionListener(scheduleEventHandler);

        // Dropdown menu
        addDropdowntime(startTimeBox);
        addDropdowntime(endTimeBox);
        addWeekday();
        addTerm();
        addType();


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

        southPanel.add(applyBtn);
        southPanel.add(backBtn);


        contentPane.add(centerPanel);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        setFrame();

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * A helper method to clear the textBox.
     */
    public void clearBoxes(){
        nameTxt.setText("");
        locationTxt.setText("");
        yearTxt.setText("");
        descriptionTxt.setText("");
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
     */
    private void addWeekday() {
        for (String weekday : Constants.DAYS_OF_THE_WEEK) {
            dayBox.addItem(weekday);
        }
    }

    /**
     * Fills the given comboBox with terms to be selected
     */
    private void addTerm(){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    /**
     * Fills the given comboBox with types of event to be selected
     */
    private void addType(){
        typeBox.addItem("Activity");
        typeBox.addItem("Reminder");
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
    public String getYear() {
        return Objects.requireNonNull(yearTxt.getText());
    }

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

    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new ScheduleEventScreen(controller, screen);

    }
}
