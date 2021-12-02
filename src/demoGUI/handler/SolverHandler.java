
package demoGUI.handler;

import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolverHandler implements ActionListener {
    private SolverScreen solverScreen;

    public SolverHandler(SolverScreen solverScreen){
        this.solverScreen = solverScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backUser();
        }
    }

    private void backUser(){
        solverScreen.dispose();
    }

}
