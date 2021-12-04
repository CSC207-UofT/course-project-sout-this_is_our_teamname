package demoGUI.handler;

import InterfaceAdaptors.DatabaseController;
import demoGUI.userview.TimeTableScreen;
import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTableScreenController implements ActionListener {
    private TimeTableScreen timeTableScreen;

    public TimeTableScreenController(TimeTableScreen timeTableScreen) {
        this.timeTableScreen = timeTableScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        //Setup DatabaseController Obj
        DatabaseController controller = new DatabaseController("gui");
        //given the button (and its text), run command and open a new window
        runButton(controller, text);
    }

    private void runButton(DatabaseController controller, String buttonText){
        switch(buttonText){
            case "Schedule Course":
                try{
                    controller.runCommand("Schedule Course");
                } catch (Exception ignore){}
                new ScheduleCourseScreen();
            case "Schedule Task/Activity":
                try{
                    controller.runCommand("Schedule Event");
                } catch (Exception ignore){}
                new ScheduleEventScreen();
            case "Save":
                try{
                    controller.runCommand("Download Timetable");
                } catch (Exception ignore){}
                new SaveScreen();
            case "Load":
                try{
                    controller.runCommand("Load Data");
                } catch (Exception ignore){}
                new LoadScreen();
            case "Settings":
                new OperatorScreen();
        }
    }
}

