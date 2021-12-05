package demoGUI.handler;

import Helpers.Constants;
import Helpers.StringToTime;
import demoGUI.GUIcommands.GUIMakeEventCommand;
import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class ScheduleEventHandler implements ActionListener {
    private ScheduleEventScreen scheduleEventScreen;

    public ScheduleEventHandler(ScheduleEventScreen scheduleEventScreen){
        this.scheduleEventScreen = scheduleEventScreen;
    }
    //TODO add exception diag window for when inputs are incorrect, such as endtime equal or earlier then starttime
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backUser();
        }
        else if ("Schedule".equals(text)) {
            try{
                //schedule events
                scheduleEventScreen.getController().getFactory().setScreen(scheduleEventScreen);
                scheduleEventScreen.getController().runCommand(Constants.SCHEDULE_EVENT);
            } catch (Exception ignore){}
            //refresh timetables
            scheduleEventScreen.getScreen().refreshTimetableTabs(
                    scheduleEventScreen.getController().getFactory().getCourseManager());
            scheduleEventScreen.dispose();

        }
    }

    private void backUser(){
        scheduleEventScreen.dispose();
    }

}

