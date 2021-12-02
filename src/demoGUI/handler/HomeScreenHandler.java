package demoGUI.handler;


import Interfaces.InterfaceFacade;
import Interfaces.OperatorInterface;
import Interfaces.UserInterface;
import demoGUI.userview.HomeScreen;
import demoGUI.userview.OperatorScreen;
import demoGUI.userview.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle's schedule course button when clicked
 */


public class HomeScreenHandler implements ActionListener {
      private HomeScreen homeScreen;
//    private UserInterface user = new UserInterface();
//    private OperatorInterface operator = user.getOperator();
    public HomeScreenHandler(HomeScreen homeScreen){
        this.homeScreen = homeScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        UserInterface user = new UserInterface();
        //OperatorInterface operator = user.getOperator();
        if ("Operator".equals(text)){
            //openOperator(operator);
        } else if ("User".equals(text)){
            operUser();
        } else if ("Exit".equals(text)){
            homeScreen.dispose();
        }
    }

    private void operUser() {
        new UserScreen();
        homeScreen.dispose();
    }

    private void openOperator(OperatorInterface operator){
        new OperatorScreen(operator);
        homeScreen.dispose();
    }
}
