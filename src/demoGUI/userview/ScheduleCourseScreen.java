package demoGUI.userview;

import InterfaceAdaptors.DatabaseController;
import TimeTableObjects.Course;
import demoGUI.handler.ScheduleCourseHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A screen that displays the window viewed by the user
 *
 * === Attributes ===
 * centerPanel: The panel that contains all components locating at the center of the screen
 * southPanel: The panel that contains all components locating at the bottom of the screen
 * courseName: The label for nameTxt
 * nameTxt: The textField for the user to enter the name of course to be scheduled
 * term: The label for termBox
 * termBox: The comboBox to allow user to select the term of the course to be scheduled
 * year: The label for yearBox
 * yearBox: The comboBox to allow user to select the year of the course to be scheduled
 * lecture: The label for lectureBox
 * lectureBox: The comboBox to allow user to select the lecture of the course to be scheduled
 * tutorial: The label for tutBox
 * tutBox: The comboBox to allow user to select the tutorial of the course to be scheduled
 * backBtn: The button to close the screen when clicked
 * searchBtn: The button to search for course information on current data source
 * applyBtn: The button to schedule the course selected when clicked
 * solverBtn: THe button to solve for a timetable for all selected courses when clicked
 *
 * scheduleCourseHandler: The handler to handle actions performed on the screen
 * conflict: Whether there is conflict for selected course. True if there is, false otherwise.
 * lec: Stores all the lecture sections for selected course
 * tut: Stores all the tutorial sections for selected course
 * controller: The DatabaseController for the GUI
 */
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
    Boolean conflict;

    Course lec;
    Course tut;

    DatabaseController controller;

    /**
     * Constructor to set the screen
     * @param controller The DatabaseController for the GUI
     * @param screen The window viewed by the user
     */
    public ScheduleCourseScreen(DatabaseController controller, TimeTableScreen screen) {
        super("Schedule Course", screen);
        this.controller = controller;
        scheduleCourseHandler = new ScheduleCourseHandler(this);
        Container contentPane = getContentPane();

        conflict = false;

        courseName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        courseName.setBounds(200, 40, 120, 40);
        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameTxt.setBounds(320, 40, 220, 40);
        nameTxt.setToolTipText("Please insert the full code, case sensitive. eg: CSC207H1");

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

    /**
     * Sets the frame of the window for display.
     */
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

    /**
     * Remove all items in tutBox and lectureBox
     */
    public void clearBoxes(){
        tutBox.removeAllItems();
        lectureBox.removeAllItems();
    }

    /**
     * Fills the given comboBox with terms to be selected
     * @param termBox is the comboBox to allow user to select the term of the course to be scheduled
     */
    private void addTerm(JComboBox<String> termBox){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    /**
     * Fills the given comboBox with years to be selected
     * @param yearBox is the comboBox to allow user to select the year of the course to be scheduled
     */
    private void addYear(JComboBox<String> yearBox){
        yearBox.addItem("2020");
        yearBox.addItem("2021");
    }

    /**
     * Fills lectureBox with lecture sections of given course
     * @param input is all the lecture sections for selected course
     */
    public void addLecture(ArrayList<String> input){
        for (String s : input) {
            this.lectureBox.addItem(s);
        }
    }

    /**
     * Fills tutBox with tutorial sections of given course
     * @param input is all the tutorial sections for selected course
     */
    public void addTutorial(ArrayList<String> input){
        for (String s : input) {
            this.tutBox.addItem(s);
        }
    }

    /**
     * Sets the lecture sections for selected course
     * @param lec is all the lecture sections for selected course
     */
    public void setLecture(Course lec) {
        this.lec = lec;
    }

    /**
     * Sets the tutorial sections for selected course
     * @param tut is all the tutorial sections for selected course
     */
    public void setTutorial(Course tut) {
        this.tut = tut;
    }

    /**
     * Sets whether there is conflict for selected course
     * @param conflict is whether there is conflict for selected course
     */
    public void setConflit(Boolean conflict) {this.conflict = conflict;}

    /**
     * Gets the lecture sections for selected course
     * @return all the lecture sections for selected course
     */
    public Course getLec() {
        return this.lec;
    }

    /**
     * Gets the tutorial sections for selected course
     * @return all the tutorial sections for selected course
     */
    public Course getTut() {
        return this.tut;
    }

    /**
     * Gets whether there is conflict for selected course
     * @return whether there is conflict for selected course
     */
    public Boolean getConflict() {return conflict;}

    /**
     * Gets the name of course to be scheduled
     * @return the name of course to be scheduled
     */
    public String getCourseName(){
        return Objects.requireNonNull(nameTxt.getText());
    }

    /**
     * Gets the term of course to be scheduled
     * @return the term of course to be scheduled
     */
    public String getTerm(){
        return Objects.requireNonNull(termBox.getSelectedItem()).toString();
    }

    /**
     * Gets the year of course to be scheduled
     * @return the year of course to be scheduled
     */
    public String getYear(){
        return Objects.requireNonNull(yearBox.getSelectedItem()).toString();
    }

    /**
     * Gets the lecture of course to be scheduled
     * @return the lecture of course to be scheduled
     */
    public String getLecBox() {
        return Objects.requireNonNull(lectureBox.getSelectedItem()).toString();
    }

    /**
     * Gets the tutorial of course to be scheduled
     * @return the tutorial of course to be scheduled
     */
    public String getTutBox() {
        return Objects.requireNonNull(tutBox.getSelectedItem()).toString();
    }

    /**
     * Gets the DatabaseController for the GUI
     * @return the DatabaseController for the GUI
     */
    public DatabaseController getController() {
        return controller;
    }


    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new ScheduleCourseScreen(controller, screen);

    }



}
