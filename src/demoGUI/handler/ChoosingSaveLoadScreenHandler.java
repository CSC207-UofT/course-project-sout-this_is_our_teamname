package demoGUI.handler;

import demoGUI.saveandload.ChoosingSaveLoadScreen;
import demoGUI.saveandload.LoadScreen;
import demoGUI.saveandload.SaveScreen;
import demoGUI.userview.HomeScreen;
import demoGUI.userview.OperatorScreen;
import demoGUI.userview.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle save button or load button when clicked
 */

public class ChoosingSaveLoadScreenHandler implements ActionListener{
    private ChoosingSaveLoadScreen choosingscreen;

    public ChoosingSaveLoadScreenHandler(ChoosingSaveLoadScreen choosingscreen){
        this.choosingscreen = choosingscreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backHome();

        } else if ("Save".equals(text)){
            new SaveScreen();
        } else if ("Load".equals(text)) {
            new LoadScreen();
        }
    }

    private void backHome(){
        new HomeScreen();
        choosingscreen.dispose();
    }
}
