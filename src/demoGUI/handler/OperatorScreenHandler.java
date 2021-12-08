package demoGUI.handler;


import Interfaces.OperatorInterface;
import demoGUI.userview.OperatorScreen;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles schedule course button when clicked.
 *
 * === Attributes ===
 * operatorScreen: The window viewed by user when selecting operator
 */
public class OperatorScreenHandler implements ActionListener {
    private final OperatorScreen operatorScreen;

    /**
     * Constructor to set the handler.
     * @param operatorScreen is the window viewed by the user when selecting operator
     */
    public OperatorScreenHandler(OperatorScreen operatorScreen){
        this.operatorScreen = operatorScreen;
    }

    /**
     * Handles the action user performed on the screen
     * @param e the action user performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            operatorScreen.dispose();
        } else if ("Taiga".equals(text)){
            //TODO what is this??
            JOptionPane.showMessageDialog(operatorScreen,"You touched Taiga's head Aww");
        } else if ("Apply".equals(text)) {
            // Get user input
            String type = operatorScreen.getDatasource();
            OperatorInterface operator = operatorScreen.getOperator();

            operatorScreen.setOperator(operator);

            JOptionPane.showMessageDialog(operatorScreen,"Successfully applied " + type);
        }
    }
}
