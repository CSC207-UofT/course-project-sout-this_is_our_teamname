package demoGUI.userview;

import demoGUI.handler.LoadScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoadScreen extends JFrame{
    JLabel title = new JLabel("Please enter the name and year of the timetables that you would like to load",
            JLabel.CENTER);
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JButton loadBtn = new JButton("Load");
    JButton backBtn = new JButton("Back");
    JTextField name = new JTextField(20);
    JTextField year = new JTextField(4);

    LoadScreenHandler loadscreenhandler;

    public LoadScreen() {
        super("Loading");

        loadscreenhandler = new LoadScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        title.setPreferredSize(new Dimension(0, 80));

        // Load button
        loadBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loadBtn.addActionListener(loadscreenhandler);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(loadscreenhandler);

        centerPanel.add(new JLabel("Enter Name"));
        centerPanel.add(name);
        centerPanel.add(new JLabel("Enter Year"));
        centerPanel.add(year);

        southPanel.add(loadBtn);
        southPanel.add(backBtn);

        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(centerPanel);
        contentPane.add(southPanel, BorderLayout.SOUTH);

        setFrame();
        // Visibility
        setVisible(true);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setFrame() {
        // Window's icon
        URL resource = LoadScreen.class.getClassLoader().getResource("pic2.jpg");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);


        // Set size
        setSize(800, 600);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }

    public String getNameString() {return name.getText();}

    public String getYearString() {return year.getText();}

    public static void main(String[] args) {
        new LoadScreen();

    }
}
