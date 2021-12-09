package GUI.handler;

import Helpers.Constants;
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
        if (text.equals("Back")){
            scheduleEventScreen.dispose();
        }
        else if (text.equals("Schedule")) {
            try{
                //schedule events
                GUICommandFactory factory = (GUICommandFactory) scheduleEventScreen.getController().getFactory();
                factory.setScreen(scheduleEventScreen);
                scheduleEventScreen.getController().runCommand(Constants.SCHEDULE_EVENT);
                scheduleEventScreen.clearBoxes();
            } catch (Exception ignore){
                // catches nothing
            }
            //refresh timetables
            scheduleEventScreen.getScreen().refreshTimetableTabs(
                    // TODO WHY? It's like saying "hey! I'll go to Bahan from
                    //  Myhal by going through UTM!!!" SEE? IT DOESN"T MAKE
                    //  ANY SENSE!!! I would urge you to put the manager call
                    //  from GUICommandFactory and call it that way. Use
                    //  Aliasing!!!
                    scheduleEventScreen.getController().getFactory().getCourseManager());
            scheduleEventScreen.dispose();
        }
    }
}
