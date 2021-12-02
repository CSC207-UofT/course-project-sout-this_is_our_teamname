package demoGUI.handler;

import com.sun.tools.javac.Main;
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

        //TODO  use switch case here
        if ("Schedule Course".equals(text)){
            System.out.println("Taiga!");
            openScheduleCourse();
        }
        else if ("Schedule Task/Activity".equals(text)){
            System.out.println("Aisaka!");
            openScheduleEvent();
        }
        else if ("Settings".equals(text)){
            openSettings();
        }

        //TODO move solver into Schedule Course.
        else if ("Solver".equals(text)) {}
        else if ("Load".equals(text)) {openLoad();}
        else if ("Save".equals(text)) {openSave();}
    }

    private void backHome(){
        new MainMenu();
        timeTableScreen.dispose();
    }

    private void openSettings(){
        new OperatorScreen();
    }
    private void openScheduleCourse(){
        new ScheduleCourseScreen();
    }

    private void openScheduleEvent(){
        new ScheduleEventScreen();
    }

    private void openSave() {
        new SaveScreen();
    }

    private void openLoad() {
        new LoadScreen();
    }

}

