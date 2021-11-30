package demoGUI.handler;


import demoGUI.userview.HomeScreen;
import demoGUI.userview.OperatorScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle's schedule course button when clicked
 */


public class HomeScreenHandler implements ActionListener {
    private HomeScreen homeScreen;
    public HomeScreenHandler(HomeScreen homeScreen){
        this.homeScreen = homeScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Operator".equals(text)){
            setting();

        } else if ("Reset".equals(text)){
            System.out.println("Sure");
        }


    }

    private void setting(){
        new OperatorScreen();
        homeScreen.dispose();
    }
}
