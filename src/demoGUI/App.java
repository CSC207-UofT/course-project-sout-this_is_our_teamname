package demoGUI;

import demoGUI.userview.TimeTableScreen;

import javax.swing.*;
import java.awt.*;


/**
 * This is the entry of the GUI
 */
public class App {
    public static void main(String[] args) throws
            AWTException,
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        new TimeTableScreen();
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    }
}

