package demoGUI.userview;

import demoGUI.handler.ScheduleCourseHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ScheduleCourseScreen extends JFrame{

    JPanel centerPanel = new JPanel(null);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel courseName = new JLabel("Course Name:");
    JTextField nameTxt = new JTextField();
    JLabel term = new JLabel("Term");
    JComboBox<String> termBox = new JComboBox<>();
    JLabel year = new JLabel("Year");
    JComboBox<String> yearBox = new JComboBox<>();
    JLabel lecture = new JLabel("Lecture");
    JComboBox<String> lectureBox = new JComboBox<>();
    JLabel tutorial = new JLabel("Tutorial");
    JComboBox<String> tutBox = new JComboBox<>();




    JButton backBtn = new JButton("Back");
    JButton searchBtn = new JButton("Search");
    JButton applyBtn = new JButton("Schedule");

    ScheduleCourseHandler scheduleCourseHandler;

    public ScheduleCourseScreen() {
        super("Schedule Event");
        scheduleCourseHandler = new ScheduleCourseHandler(this);
        Container contentPane = getContentPane();

        courseName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        courseName.setBounds(200, 40, 120, 40);
        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameTxt.setBounds(320, 40, 220, 40);

        year.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        year.setBounds(200, 100, 120, 40);

        yearBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        yearBox.setBounds(320, 100, 120, 40);

        term.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        term.setBounds(200, 160, 120, 40);

        termBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        termBox.setBounds(320, 160, 120, 40);

        searchBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        searchBtn.setBounds(350, 220, 120, 40);

        lecture.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lecture.setBounds(200, 280, 120, 40);

        lectureBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lectureBox.setBounds(320, 280, 120, 40);

        tutorial.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tutorial.setBounds(200, 340, 120, 40);

        tutBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tutBox.setBounds(320, 340, 120, 40);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(scheduleCourseHandler);

        // Apply button
        applyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        applyBtn.addActionListener(scheduleCourseHandler);

        // Dropdown menu

        addYear(yearBox);
        addTerm(termBox);



        centerPanel.add(courseName);
        centerPanel.add(nameTxt);

        centerPanel.add(year);
        centerPanel.add(yearBox);
        centerPanel.add(term);
        centerPanel.add(termBox);
        centerPanel.add(searchBtn);
        centerPanel.add(lecture);
        centerPanel.add(lectureBox);
        centerPanel.add(tutorial);
        centerPanel.add(tutBox);
        southPanel.add(backBtn);
        southPanel.add(applyBtn);


        contentPane.add(centerPanel);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        setFrame();

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

        // Visibility
        setVisible(true);
    }




    private void addTerm(JComboBox<String> termBox){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    private void addYear(JComboBox<String> yearBox){
        yearBox.addItem("2020");
        yearBox.addItem("2021");
    }


    public static void main(String[] args) {
        new ScheduleCourseScreen();

    }


}
