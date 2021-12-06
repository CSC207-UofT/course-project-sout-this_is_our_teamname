package demoGUI.userview;

import InterfaceAdaptors.DatabaseController;
import TimeTableObjects.Course;
import demoGUI.handler.ScheduleCourseHandler;

import javax.swing.*;
import java.awt.*;
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class ScheduleCourseScreen extends AbstractScreen{

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
    JButton solverBtn = new JButton("Solver");

    ScheduleCourseHandler scheduleCourseHandler;

    Course lec;
    Course tut;

    DatabaseController controller;

    public ScheduleCourseScreen(DatabaseController controller, TimeTableScreen screen) {
        super("Schedule Course", screen);
        this.controller = controller;
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
        searchBtn.addActionListener(scheduleCourseHandler);
        searchBtn.setBounds(250, 220, 120, 40);

        lecture.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lecture.setBounds(40, 280, 120, 40);

        lectureBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lectureBox.setBounds(120, 280, 600, 40);

        tutorial.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tutorial.setBounds(40, 340, 120, 40);

        tutBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tutBox.setBounds(120, 340, 600, 40);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(scheduleCourseHandler);

        // Apply button
        applyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        applyBtn.addActionListener(scheduleCourseHandler);

        // Solver button
        solverBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        solverBtn.addActionListener(scheduleCourseHandler);
        solverBtn.setBounds(450, 220, 120, 40);
        // Dropdown menu

        addYear(yearBox);
        addTerm(termBox);


        centerPanel.add(courseName);
        centerPanel.add(nameTxt);
        centerPanel.add(solverBtn);
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

    protected void setFrame() {
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

    public void clearBoxes(){
        tutBox.removeAllItems();
        lectureBox.removeAllItems();
    }

    private void addTerm(JComboBox<String> termBox){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    private void addYear(JComboBox<String> yearBox){
        yearBox.addItem("2020");
        yearBox.addItem("2021");
    }

    public String getCourseName(){
        return Objects.requireNonNull(nameTxt.getText());
    }

    public String getTerm(){
        return Objects.requireNonNull(termBox.getSelectedItem()).toString();

    }

    public String getYear(){
        return Objects.requireNonNull(yearBox.getSelectedItem()).toString();

    }

    public void setLeturebox(ArrayList<String> input){
       for(int i = 0; i < input.size(); i++){
           this.lectureBox.addItem(input.get(i));
        }
    }


    public void setTutBox(ArrayList<String> input){
        for (int i = 0; i < input.size(); i++){
            this.tutBox.addItem(input.get(i));
        }
    }

    public String gettutBox() {
        return Objects.requireNonNull(tutBox.getSelectedItem()).toString();

    }

    public String getlecBox() {
        return Objects.requireNonNull(lectureBox.getSelectedItem()).toString();

    }

    public DatabaseController getController() {
        return controller;
    }

    public void setLecture(Course lec) {
         this.lec = lec;
    }

    public void setTutorial(Course tut) {
        this.tut = tut;
    }

    public Course getLec() {
        return this.lec;
    }

    public Course getTut() {
        return this.tut;
    }

    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new ScheduleCourseScreen(controller, screen);

    }



}
