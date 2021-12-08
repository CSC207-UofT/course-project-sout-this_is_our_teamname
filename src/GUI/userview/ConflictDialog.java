package GUI.userview;

import javax.swing.*;
import java.awt.event.*;

/**
 * Displays the conflict dialog if there is conflict in the Event to be scheduled
 *
 * === Attributes ===
 * contentPane: The panel that contains all components locating on the dialog
 * okButton: The button to close the dialog when clicked
 * conflictLabel: The label displaying the conflict message
 */
public class ConflictDialog extends JDialog {
    private JPanel contentPane;
    private JButton okButton;
    private JLabel conflictLabel;

    /**
     * Constructor to set the dialog
     */
    public ConflictDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        okButton.addActionListener(e -> onOK());
    }

    /**
     * Dispose the dialog when button is clicked
     */
    private void onOK() {
        // add your code here
        dispose();
    }

    //TODO: delete if not used
    /**
     * Dispose the dialog when button is clicked
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        ConflictDialog dialog = new ConflictDialog();
        dialog.pack();
        dialog.setLocationRelativeTo(jframe);
        dialog.setVisible(true);
    }
}
