package demoGUI.handler;


import demoGUI.userview.HomeScreen;
import demoGUI.userview.OperatorScreen;
import demoGUI.userview.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle's schedule course button when clicked
 */


public class OperatorScreenHandler implements ActionListener {
    private OperatorScreen operatorScreen;

    public OperatorScreenHandler(OperatorScreen operatorScreen){
        this.operatorScreen = operatorScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backHome();

        } else if ("Taiga".equals(text)){
            System.out.println("Taiga!");
            JOptionPane.showMessageDialog(operatorScreen,"You touched Taiga's head Aww");
        } else if ("Apply".equals(text)) {
            JOptionPane.showMessageDialog(operatorScreen,"Successfully applied");
        }
    }

    private void backHome(){
        new HomeScreen();
        operatorScreen.dispose();
    }
}