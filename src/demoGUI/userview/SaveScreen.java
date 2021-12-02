package demoGUI.userview;

import demoGUI.handler.SaveScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SaveScreen extends JFrame{
    JLabel title = new JLabel("Please enter the name and year you would like to save your timetables with",
            JLabel.CENTER);
    JPanel centerPanel = new JPanel(null);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel name = new JLabel("Name");
    JLabel year = new JLabel("Year");
    JButton saveBtn = new JButton("Save");
    JButton backBtn = new JButton("Back");
    JTextField nameTxt = new JTextField(20);
    JTextField yearTxt = new JTextField(4);

    SaveScreenHandler savescreenhandler;

    public SaveScreen() {
        super("Saving");

        savescreenhandler = new SaveScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        title.setPreferredSize(new Dimension(0, 20));

        // Save button
        saveBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        saveBtn.addActionListener(savescreenhandler);

        name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        name.setBounds(200, 40, 120, 40);

        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        nameTxt.setBounds(320, 40, 120, 40);

        year.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        year.setBounds(200, 100, 120, 40);

        yearTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        yearTxt.setBounds(320, 100, 120, 40);
        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(savescreenhandler);


        centerPanel.add(name);
        centerPanel.add(nameTxt);
        centerPanel.add(year);
        centerPanel.add(yearTxt);

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

    public String getNameString() {return nameTxt.getText();}

    public String getYearString() {return yearTxt.getText();}

    public static void main(String[] args) {
        new SaveScreen();

    }
}