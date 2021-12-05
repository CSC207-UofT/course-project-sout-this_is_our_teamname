package demoGUI.handler;


import DataGetting.CSVScraper;
import DataGetting.WebScraper;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.DatabaseController;
import Interfaces.InterfaceFacade;
import Interfaces.OperatorInterface;
import TimeTableContainers.TimeTableManager;
import demoGUI.userview.OperatorScreen;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
            JOptionPane.showMessageDialog(operatorScreen,"You touched Taiga's head Aww");
        } else if ("Apply".equals(text)) {
            // Get user input
            String type = operatorScreen.getDatasource();
            OperatorInterface operator = operatorScreen.getOperator();

            operatorScreen.setOperator(operator);

            JOptionPane.showMessageDialog(operatorScreen,"Successfully applied " + type);
        }
    }

    private void backHome(){
        operatorScreen.dispose();
    }

}