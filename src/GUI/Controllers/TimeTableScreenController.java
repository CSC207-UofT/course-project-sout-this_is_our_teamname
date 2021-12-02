package GUI.Controllers;

import GUI.Views.TimeTableScreen;
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

        if ("Back".equals(text)){backHome();}

        else if ("Schedule Course".equals(text)){
            System.out.println("Taiga!");
            openScheduleCourse();
        }
        else if ("Schedule Task/Activity".equals(text)){
            System.out.println("Aisaka!");
            openScheduleEvent();
        }

        //TODO move solver into Schedule Course.
        else if ("Solver".equals(text)) {}
        else if ("Load".equals(text)) {openLoad();}
        else if ("Save".equals(text)) {openSave();}
    }

    private void backHome(){
        new HomeScreen();
        timeTableScreen.dispose();
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

