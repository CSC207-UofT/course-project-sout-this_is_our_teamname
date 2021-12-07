package demoGUI.handler;

import Helpers.Constants;
import Helpers.StringToTime;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.GUICommandFactory;
import demoGUI.GUIcommands.GUIMakeEventCommand;
import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class ScheduleEventHandler implements ActionListener {
    private final ScheduleEventScreen scheduleEventScreen;

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
                CommandFactory factory = scheduleEventScreen.getController().getFactory();
                ((GUICommandFactory) factory).setScreen(scheduleEventScreen);
                scheduleEventScreen.getController().runCommand(Constants.SCHEDULE_EVENT);
            } catch (Exception ignore){}
            //refresh timetables
            scheduleEventScreen.getScreen().refreshTimetableTabs(
                    scheduleEventScreen.getController().getFactory().getCourseManager());
            //opens conflict dialog when there is a conflict in scheduling
            if (scheduleEventScreen.getConflict()){
            ConflictDialog.main(new String[]{""});
            }
            scheduleEventScreen.dispose();

        }
    }

    private void backUser(){
        scheduleEventScreen.dispose();
    }

}

