package GUI.userview;

import javax.swing.*;

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
     * Runs this Conflict Dialog
     */
    public void run(){
        JFrame jframe = new JFrame();
        this.pack();
        this.setLocationRelativeTo(jframe);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ConflictDialog cd = new ConflictDialog();
        cd.run();
    }
}
