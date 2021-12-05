package demoGUI.userview;

import InterfaceAdaptors.DatabaseController;

import javax.swing.*;

public abstract class AbstractScreen extends JFrame {
    private DatabaseController controller;
    private TimeTableScreen screen;

    AbstractScreen(String str, TimeTableScreen screen){

        super(str);
        this.screen = screen;
    };

    //TODO protected?
    protected abstract void setFrame();

    public TimeTableScreen getScreen() {
        return screen;
    }

    public DatabaseController getController() {
        return controller;


    }
}
