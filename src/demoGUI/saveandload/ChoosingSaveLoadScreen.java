package demoGUI.saveandload;

import demoGUI.handler.ChoosingSaveLoadScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class ChoosingSaveLoadScreen extends JFrame {
    JLabel title = new JLabel("I would like to", JLabel.CENTER);
    JPanel centerPanel = new JPanel();
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton backBtn = new JButton("Back");

    ChoosingSaveLoadScreenHandler choosinghandler;

    public ChoosingSaveLoadScreen() {
        super("Save or Load?");

        choosinghandler = new ChoosingSaveLoadScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        // Save button
        saveBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        saveBtn.addActionListener(choosinghandler);

        // Load button
        loadBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loadBtn.addActionListener(choosinghandler);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(choosinghandler);

        centerPanel.add(saveBtn);
        centerPanel.add(loadBtn);
        centerPanel.add(backBtn);

        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(centerPanel);

        setFrame();
        // Visibility
        setVisible(true);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setFrame() {
        // Window's icon
        URL resource = ChoosingSaveLoadScreen.class.getClassLoader().getResource("pic2.jpg");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);


        // Set size
        setSize(800, 600);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }

    public static void main(String[] args) {
        new ChoosingSaveLoadScreen();

    }
}