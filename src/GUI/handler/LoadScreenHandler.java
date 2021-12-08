package GUI.handler;

import Helpers.Constants;
import InterfaceAdaptors.GUICommandFactory;
import GUI.userview.LoadScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Handler to handle actions performed by the user when loading data
 *
 * === Attributes ===
 * loadScreen: The window viewed by the user when loading data
 */
public class LoadScreenHandler implements ActionListener {
    private final LoadScreen loadScreen;

    /**
     * Constructor to set the handler.
     * @param loadScreen is the window viewed by the user when loading data
     */
    public LoadScreenHandler(LoadScreen loadScreen){
        this.loadScreen = loadScreen;
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
            loadScreen.dispose();

        } else if (text.equals("Load")){
            try{
                // load timetables
                GUICommandFactory factory =
                        (GUICommandFactory) loadScreen.getController().getFactory();
                factory.setScreen(loadScreen);
                loadScreen.getController().runCommand(Constants.LOAD_DATA);
            } catch (Exception ignore){
                // Doesn't catch anything
            }
            // refresh timetables
            loadScreen.getScreen().refreshTimetableTabs(
                    // TODO WHY? It's like saying "hey! I'll go to Bahan from
                    //  Myhal by going through UTM!!!" SEE? IT DOESN"T MAKE
                    //  ANY SENSE!!! I would urge you to put the manager call
                    //  from GUICommandFactory and call it that way. Use
                    //  Aliasing!!!
                    loadScreen.getController().getFactory().getCourseManager());
            loadScreen.dispose();
        }
    }
}
