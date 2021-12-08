package demoGUI.handler;

import InterfaceAdaptors.DatabaseController;
import demoGUI.userview.TimeTableScreen;
import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Handler to handle actions performed by the user on the main screen
 *
 * === Attributes ===
 * timeTableScreen: The window displaying the timetable
 * controller: The DatabaseController for GUI
 */
public class TimeTableScreenHandler implements ActionListener {
    private final TimeTableScreen timeTableScreen;
    private final DatabaseController controller;

    /**
     * Constructor to set the handler.
     * @param timeTableScreen is the window displaying the timetable
     */
    public TimeTableScreenHandler(TimeTableScreen timeTableScreen) {
        this.timeTableScreen = timeTableScreen;
        this.controller = new DatabaseController("gui");
    }

    /**
     * Handles the action user performed on the screen
     * @param e the action user performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        //given the button (and its text), run command and open a new window
        runButton(controller, text);
    }

    /**
     * Perform action based on the button pressed by the user
     * @param controller The DatabaseController for GUI
     * @param buttonText The text of the button pressed by the user
     */
    private void runButton(DatabaseController controller, String buttonText){
        switch(buttonText){
            case "Schedule Course":
                new ScheduleCourseScreen(controller, timeTableScreen);
                break;
            case "Schedule Activity/Reminder":
                new ScheduleEventScreen(controller, timeTableScreen);
                break;
            case "Save":
                new SaveScreen(controller, timeTableScreen);
                break;
            case "Load":
                new LoadScreen(controller, timeTableScreen);
                break;
            default:
                break;
        }
    }

    /**
     * Gets the DatabaseController for the GUI
     * @return the DatabaseController for the GUI
     */
    public DatabaseController getController() {
        return controller;
    }
}

