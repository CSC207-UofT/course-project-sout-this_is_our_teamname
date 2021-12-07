package demoGUI.handler;

import Interfaces.OperatorInterface;
import demoGUI.userview.OperatorScreen;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles events on the setting screen.
 */
public class OperatorScreenHandler implements ActionListener {
    private final OperatorScreen operatorScreen;


    public OperatorScreenHandler(OperatorScreen operatorScreen){
        this.operatorScreen = operatorScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backHome();
        } else if ("Apply".equals(text)) {
            // Get user input
            String type = operatorScreen.getDatasource();
            OperatorInterface operator = operatorScreen.getOperator();

            operatorScreen.setOperator(operator);

            // Showing prompts
            JOptionPane.showMessageDialog(operatorScreen,"Successfully applied " + type);
        }
    }
    
    /** 
     * Dispose the current operatorScreen
     */
    private void backHome(){
        operatorScreen.dispose();
    }

}