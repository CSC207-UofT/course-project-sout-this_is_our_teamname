package GUI;

import GUI.userview.TimeTableScreen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This is the entry of the GUI
 */
public class App {
    public static void main(String[] args) throws Exception {
        new TimeTableScreen();
        // here you can put the selected theme class name in JTattoo
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    }
}

