package demoGUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WindowJFrame extends JFrame{
    // Components
    JButton jButton = new JButton("This is a button");
    JLabel southLabel = new JLabel("This is a label");
    JButton jb2 = new JButton("IN panel");
    JButton jb3 = new JButton("IN panel");

    // Flowlayout by default
    JPanel jPanel = new JPanel();

    public WindowJFrame() {
        super("This is the title of Jframe");

        setFrame();

        Container contentPane = getContentPane();

        contentPane.add(jButton);

        contentPane.add(southLabel, BorderLayout.EAST);

        contentPane.add(jPanel);
        jPanel.add(jb2);
        jPanel.add(jb3);

        // Visibility
        setVisible(true);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setFrame(){
        // Window's icon
        URL resource = WindowJFrame.class.getClassLoader().getResource("pic2.jpg");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        // Set size
        setSize(600, 400);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }
    public static void main(String[] args) {
        new WindowJFrame();
    }
}
