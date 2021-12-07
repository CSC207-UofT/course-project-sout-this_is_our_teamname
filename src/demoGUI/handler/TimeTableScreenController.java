package demoGUI.handler;

import InterfaceAdaptors.DatabaseController;
import demoGUI.userview.TimeTableScreen;
import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTableScreenController implements ActionListener {
    private final TimeTableScreen timeTableScreen;
    private final DatabaseController controller;

    public TimeTableScreenController(TimeTableScreen timeTableScreen) {

        this.timeTableScreen = timeTableScreen;
        this.controller = new DatabaseController("gui");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        //given the button (and its text), run command and open a new window
        runButton(controller, text);
    }

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
            case "Settings":
                new OperatorScreen(timeTableScreen);
                break;
            default:
                break;
        }
    }

    public DatabaseController getController() {
        return controller;
    }
}

