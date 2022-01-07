package GUI.userview;

import InterfaceAdaptors.DatabaseController;

import javax.swing.*;

/**
 * An abstract class that displays the screen viewed by the user
 *
 * === Attributes ===
 * controller: The DatabaseController for the GUI
 * screen: The window viewed by the user
 */
public abstract class AbstractScreen extends JFrame {
    protected final DatabaseController controller;
    private final TimeTableScreen screen;

    /**
     * Constructor to set the screen
     * @param str The name of the screen
     * @param screen The window viewed by the user
     */
    public AbstractScreen(String str, TimeTableScreen screen){
        super(str);
        this.screen = screen;
        this.controller = screen.timeTableScreenHandler.getController();
    }

    /**
     * Sets the frame of the window for display.
     */
    protected abstract void setFrame();

    /**
     * Gets the screen
     * @return the screen
     */
    public TimeTableScreen getScreen() {
        return screen;
    }

    /**
     * Gets the DatabaseController for the GUI
     * @return the DatabaseController for the GUI
     */
    public DatabaseController getController() {
        return controller;


    }
}