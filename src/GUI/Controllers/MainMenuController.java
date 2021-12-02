package GUI.Controllers;


import GUI.Views.MainMenu;
import demoGUI.userview.OperatorScreen;
import demoGUI.userview.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle's schedule course button when clicked
 */


public class MainMenuController implements ActionListener {
    private MainMenu mainMenu;

    public MainMenuController(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Settins".equals(text)){
            openOperator();
        } else if ("TimeTable".equals(text)){
            operUser();
        } else if ("Exit".equals(text)){
            mainMenu.dispose();
        }
    }

    private void operUser() {
        new UserScreen();
        mainMenu.dispose();
    }

    private void openOperator(){
        new OperatorScreen();
        mainMenu.dispose();
    }
}
