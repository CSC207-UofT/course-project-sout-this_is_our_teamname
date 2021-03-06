package GUI.handler;

import Helpers.Constants;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.GUICommandFactory;
import GUI.userview.SaveScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Handler to handle actions performed by the user when saving the timetables
 *
 * === Attributes ===
 * saveScreen: The window viewed by the user when saving the timetables
 */
public class SaveScreenHandler implements ActionListener{
    private final SaveScreen saveScreen;

    /**
     * Constructor to set the handler.
     * @param saveScreen is the window viewed by the user when saving the timetables
     */
    public SaveScreenHandler(SaveScreen saveScreen){
        this.saveScreen = saveScreen;
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
            saveScreen.dispose();

        } else if (text.equals("Save")){
            try{
                //save timetables
                GUICommandFactory factory =
                        (GUICommandFactory) saveScreen.getController().getFactory();
                factory.setScreen(saveScreen);
                saveScreen.getController().runCommand(Constants.DOWNLOAD_TIMETABLE);
            } catch (Exception ignore){
                // Catching exceptions
            }
            //refresh timetables
            saveScreen.getScreen().refreshTimetableTabs(
                    saveScreen.getController().getFactory().getCourseManager());
            saveScreen.dispose();
        }
    }
}