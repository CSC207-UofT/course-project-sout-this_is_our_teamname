package demoGUI.handler;


import demoGUI.userview.HomeScreen;
import demoGUI.userview.OperatorScreen;
import demoGUI.userview.TimeTableScreen;
import demoGUI.userview.UserScreen;

import javax.swing.*;
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
            openOperator();
        } else if ("User".equals(text)){
            operUser();
        } else if ("Exit".equals(text)){
            homeScreen.dispose();
        }
    }

    private void operUser() {
        new TimeTableScreen();
        homeScreen.dispose();
    }

    private void openOperator(){
        new OperatorScreen();
        homeScreen.dispose();
    }
}
