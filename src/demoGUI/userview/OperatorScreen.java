package demoGUI.userview;

import Interfaces.OperatorInterface;
import demoGUI.handler.OperatorScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

/**
 * This is the homeScreen of the GUI
 */

// TODO Tray Icon P8, Get text by click enter P9 9:50. jTable for timetable at P10 8:50
public class OperatorScreen extends JFrame{
    OperatorInterface operator;
    JLabel title = new JLabel("This is the operator", JLabel.CENTER);
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton srcBtn = new JButton("Aisaka");
    JButton banBtn = new JButton("Taiga");
    JButton backBtn = new JButton("Back");
    JButton applyBtn = new JButton("Apply");
    JComboBox<String> dataBox = new JComboBox<>();


    OperatorScreenHandler operatorScreenHandler;

    public OperatorScreen(OperatorInterface operator) {
        super("Setting");
        this.operator = operator;
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

        // Apply button
        applyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        applyBtn.addActionListener(operatorScreenHandler);

        // Dropdown menu
        dataBox.addItem("Webscraper");
        dataBox.addItem("CSVscraper");
        dataBox.addItem("Default");

        centerPanel.add(srcBtn);
        centerPanel.add(dataBox);

        southPanel.add(applyBtn);
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
        assert resource != null;
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        // Set size
        setSize(800, 600);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }

    public OperatorInterface getOperator() {
        return operator;
    }

    public void setOperator(OperatorInterface operator) {
        this.operator = operator;
    }

    public String getDatasource(){
        return Objects.requireNonNull(dataBox.getSelectedItem()).toString();
    }

}

