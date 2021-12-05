package demoGUI.handler;

import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.DatabaseController;
import demoGUI.userview.TimeTableScreen;
import demoGUI.userview.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTableScreenController implements ActionListener {
    private TimeTableScreen timeTableScreen;
    private DatabaseController controller;

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
                new ScheduleCourseScreen(timeTableScreen);
                break;
            case "Schedule Task/Activity":
                new ScheduleEventScreen(controller);
                break;
            case "Save":
                new SaveScreen();
                break;
            case "Load":
                new LoadScreen();
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

