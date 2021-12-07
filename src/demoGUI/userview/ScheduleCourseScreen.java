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
 * A ScheduleCourseScreen class that construct the screen of Schedule Course.
 *
 */
public class ScheduleCourseScreen extends AbstractScreen{
    // Instant Variables of the screen
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
    JLabel practical = new JLabel("Practical");
    JComboBox<String> pracBox = new JComboBox<>();
    JButton backBtn = new JButton("Back");
    JButton searchBtn = new JButton("Search");
    JButton applyBtn = new JButton("Schedule");
    JButton solverBtn = new JButton("Solver");
    ScheduleCourseHandler scheduleCourseHandler;
    Boolean conflict;

    Course lec;
    Course tut;
    Course prac;
    DatabaseController controller;


    /**
     * Constructor of ScheduleCourseScreen.
     * Sets label,textbox, and buttons.
     *
     * @param controller A DatabaseController object where the data comes from.
     * @param screen A TimeTableScreen object that stores course.
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

        practical.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        practical.setBounds(40, 400, 120, 40);

        pracBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        pracBox.setBounds(120, 400, 600, 40);

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

        // set the year Box and term Box
        addYear(yearBox);
        addTerm(termBox);

        // add to the panel
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
        centerPanel.add(practical);
        centerPanel.add(pracBox);
        southPanel.add(backBtn);
        southPanel.add(applyBtn);
        contentPane.add(centerPanel);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        setFrame();

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * The main method of the ScheduleCourseScreen.
     *
     */
    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new ScheduleCourseScreen(controller, screen);

    }

    // ============================ Helper Methods =================================
    /**
     * A helper method to set the frame.
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
     * A helper method to clear the textBox.
     */
    public void clearBoxes(){
        tutBox.removeAllItems();
        lectureBox.removeAllItems();
    }

    /**
     * A helper method to add term to the termBox.
     *
     * @param termBox the JComboBox stores the term.
     */
    private void addTerm(JComboBox<String> termBox){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    /**
     * A helper method to add year to the yearBox.
     *
     * @param yearBox the JComboBox stores the year.
     */
    private void addYear(JComboBox<String> yearBox){
        yearBox.addItem("2020");
        yearBox.addItem("2021");
    }

    /**
     * A helper method to get the course name.
     *
     * @return string representation of the item in the course TextBox.
     */
    public String getCourseName(){
        return Objects.requireNonNull(nameTxt.getText());
    }

    /**
     * A helper method to get the term.
     *
     * @return string representation of the selected item in the term Box.
     */
    public String getTerm(){
        return Objects.requireNonNull(termBox.getSelectedItem()).toString();
    }

    /**
     * A helper method to get the year.
     *
     * @return string representation of the selected item in the year Box.
     */
    public String getYear(){
        return Objects.requireNonNull(yearBox.getSelectedItem()).toString();
    }

    /**
     * A helper method to set the lectureBox.
     *
     * @param input a list of string that need to be put into the lecture box
     */
    public void setLeturebox(ArrayList<String> input){
        for (String s : input) {
            this.lectureBox.addItem(s);
        }
    }

    /**
     * A helper method to set the tutorialBox.
     *
     * @param input a list of string that need to be put into the tutorial box.
     */
    public void setTutBox(ArrayList<String> input){
        for (String s : input) {
            this.tutBox.addItem(s);
        }
    }

    /**
     * A helper method to set the pracBox.
     *
     * @param input a list of string that need to be put into the practical box.
     */
    public void setpracBox(ArrayList<String> input){
        for (String s : input) {
            this.pracBox.addItem(s);
        }
    }

    /**
     * A helper method to get the item in the tutorialBox.
     *
     * @return string representation of the selected item in tutorial box.
     */
    public String gettutBox() {
        if (tutBox.getSelectedItem() != null) {
            return Objects.requireNonNull(tutBox.getSelectedItem()).toString();
        }
        return "";
    }

    /**
     * A helper method to get the item in the lectureBox.
     *
     * @return string representation of the selected item in lecture box.
     */
    public String getlecBox() {
        if (lectureBox.getSelectedItem() != null) {
            return Objects.requireNonNull(lectureBox.getSelectedItem()).toString();
        }
        return "";
    }

    /**
     * A helper method to get the item in the pracBox.
     *
     * @return string representation of the selected item in practical box.
     */
    public String getpracBox() {
        if (pracBox.getSelectedItem() != null) {
            return Objects.requireNonNull(pracBox.getSelectedItem()).toString();
        }
        return "";
    }

    /**
     * A helper method to get the controller.
     *
     * @return the controller of the screen.
     */
    public DatabaseController getController() {
        return controller;
    }

    /**
     * A helper method to set the lecture.
     */
    public void setLecture(Course lec) {
         this.lec = lec;
    }

    /**
     * A helper method to set the tutorial.
     */
    public void setTutorial(Course tut) {
        this.tut = tut;
    }

    /**
     * A helper method to set the practical.
     */
    public void setPractical(Course prac) {
        this.prac = prac;
    }

    /**
     * A helper method to get the lecture.
     *
     * @return the lecture section being selected.
     */
    public Course getLec() {
        return this.lec;
    }

    /**
     * A helper method to get the tutorial.
     *
     * @return the tutorial section being selected
     */
    public Course getTut() {
        return this.tut;
    }

    /**
     * A helper method to see if the current course creates a conflict in the timetable.
     *
     * @return true if there is a conflict, vice versa
     */
    public Boolean getConflict() {
        return conflict;
    }

    /**
     * A helper method, this will change the conflict status of the current course being scheduled.
     *
     */
    public void setConflit(Boolean conflit) {
        this.conflict = conflit;
    }

    /**
     * A helper method to get the practical.
     *
     * @return the practical section being selected
     */
    public Course getPrac() {
        return this.prac;
    }
}
