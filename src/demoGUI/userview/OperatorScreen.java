package demoGUI.userview;

import demoGUI.handler.OperatorScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * This is the homeScreen of the GUI
 */

// TODO Tray Icon P8, Get text by click enter P9 9:50. jTable for timetable at P10 8:50
public class OperatorScreen extends JFrame{

    JLabel title = new JLabel("This is the operator", JLabel.CENTER);
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton srcBtn = new JButton("Aisaka");
    JButton banBtn = new JButton("Taiga");
    JButton backBtn = new JButton("Back");
    JButton applyBtn = new JButton("Apply");
    JComboBox dataBox = new JComboBox<String>();
    // For tray icon refer to bilibili video P8
    SystemTray systemTray;


    OperatorScreenHandler operatorScreenHandler;

    public OperatorScreen() {
        super("Setting");

        operatorScreenHandler = new OperatorScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        // Schedule course
        srcBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        srcBtn.addActionListener(operatorScreenHandler);

        // Schedule event
        banBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        banBtn.addActionListener(operatorScreenHandler);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(operatorScreenHandler);

        // Dropdown menu
        dataBox.addItem("Webscraper");
        dataBox.addItem("CSVscraper");


        centerPanel.add(srcBtn);
        centerPanel.add(dataBox);

        southPanel.add(banBtn);
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
        URL resource = OperatorScreen.class.getClassLoader().getResource("pic2.jpg");
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
        new OperatorScreen();
    }
}

