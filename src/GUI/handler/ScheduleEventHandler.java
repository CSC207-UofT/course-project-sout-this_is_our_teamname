package GUI.handler;

import Helpers.Constants;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.GUICommandFactory;
import GUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Handler to handle actions performed by the user when scheduling non-course Event
 *
 * === Attributes ===
 * scheduleEventScreen: The window viewed by the user when scheduling non-course Event
 */
public class ScheduleEventHandler implements ActionListener {
    private final ScheduleEventScreen scheduleEventScreen;

    /**
     * Constructor to set the handler.
     * @param scheduleEventScreen is the window viewed by the user when scheduling non-course Event
     */
    public ScheduleEventHandler(ScheduleEventScreen scheduleEventScreen){
        this.scheduleEventScreen = scheduleEventScreen;
    }

    /**
     * Handles the action user performed on the screen
     * @param e the action user performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            scheduleEventScreen.dispose();
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

            scheduleEventScreen.dispose();

        }
    }
}

