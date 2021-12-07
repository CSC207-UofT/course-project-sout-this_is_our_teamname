package demoGUI.userview;

import javax.swing.*;
import java.awt.event.*;

public class ConflictDialog extends JDialog {
    private JPanel contentPane;
    private JButton OKbutton;
    private JLabel conflictLabel;


    public ConflictDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(OKbutton);

        OKbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

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
