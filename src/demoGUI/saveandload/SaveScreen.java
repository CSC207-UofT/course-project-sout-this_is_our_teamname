package demoGUI.saveandload;

import demoGUI.handler.SaveScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class SaveScreen extends JFrame{
    JLabel title = new JLabel("Please enter the name and year you would like to save your timetables with",
            JLabel.CENTER);
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JButton saveBtn = new JButton("Save");
    JButton backBtn = new JButton("Back");
    JTextField name = new JTextField(20);
    JTextField year = new JTextField(4);

    SaveScreenHandler savescreenhandler;

    public SaveScreen() {
        super("Saving");

        savescreenhandler = new SaveScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        // Save button
        saveBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        saveBtn.addActionListener(savescreenhandler);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(savescreenhandler);

        centerPanel.add(new JLabel("Enter Name"));
        centerPanel.add(name);
        centerPanel.add(new JLabel("Enter Year"));
        centerPanel.add(year);

        southPanel.add(saveBtn);
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
        URL resource = SaveScreen.class.getClassLoader().getResource("pic2.jpg");
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
        new SaveScreen();

    }
}
