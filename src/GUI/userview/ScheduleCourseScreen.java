package GUI.userview;

import Helpers.Constants;
import InterfaceAdaptors.DatabaseController;
import TimeTableObjects.Course;
import GUI.handler.ScheduleCourseHandler;

import javax.swing.*;
import java.awt.*;
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
    private final static JPanel centerPanel = new JPanel(null);
    private final static JPanel southPanel =
            new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final static JLabel courseName = new JLabel("Course Name:");
    private final static JTextField nameTxt = new JTextField();
    private final static JLabel term = new JLabel("Term");
    private final static JComboBox<String> termBox = new JComboBox<>();
    private final static JLabel year = new JLabel("Year");
    private final static JComboBox<String> yearBox = new JComboBox<>();
    private final static JLabel lecture = new JLabel("Lecture");
    private final static JComboBox<String> lectureBox = new JComboBox<>();
    private final static JLabel tutorial = new JLabel("Tutorial");
    private final static JComboBox<String> tutBox = new JComboBox<>();
    private final static JLabel practical = new JLabel("Practical");
    private final static JComboBox<String> pracBox = new JComboBox<>();
    private final static JButton backBtn = new JButton("Back");
    private final static JButton searchBtn = new JButton("Search");
    private final static JButton applyBtn = new JButton("Schedule");

    ScheduleCourseHandler scheduleCourseHandler;
    
    private Course lec;
    private Course tut;
    private Course prac;
    
    /**
     * Constructor of ScheduleCourseScreen.
     * Sets label,textbox, and buttons.
     *
     * @param controller A DatabaseController object where the data comes from.
     * @param screen A TimeTableScreen object that stores course.
     */
    public ScheduleCourseScreen(DatabaseController controller, TimeTableScreen screen) {
        
        super(Constants.SCHEDULE_COURSE, controller, screen);


        scheduleCourseHandler = new ScheduleCourseHandler(this);
        Container contentPane = getContentPane();

        courseName.setFont(Constants.FONT20);
        courseName.setBounds(200, 40, 120, 40);
        nameTxt.setFont(Constants.FONT15);
        nameTxt.setBounds(320, 40, 220, 40);
        nameTxt.setToolTipText("Please insert the full code, case sensitive. eg: CSC207H1");

        year.setFont(Constants.FONT20);
        year.setBounds(200, 100, 120, 40);

        yearBox.setFont(Constants.FONT20);
        yearBox.setBounds(320, 100, 120, 40);

        term.setFont(Constants.FONT20);
        term.setBounds(200, 160, 120, 40);

        termBox.setFont(Constants.FONT20);
        termBox.setBounds(320, 160, 120, 40);

        searchBtn.setFont(Constants.FONT20);
        searchBtn.addActionListener(scheduleCourseHandler);
        searchBtn.setBounds(320, 220, 120, 40);

        lecture.setFont(Constants.FONT20);
        lecture.setBounds(40, 280, 120, 40);

        lectureBox.setFont(Constants.FONT15);
        lectureBox.setBounds(120, 280, 600, 40);

        tutorial.setFont(Constants.FONT20);
        tutorial.setBounds(40, 340, 120, 40);

        tutBox.setFont(Constants.FONT15);
        tutBox.setBounds(120, 340, 600, 40);

        practical.setFont(Constants.FONT20);
        practical.setBounds(40, 400, 120, 40);

        pracBox.setFont(Constants.FONT15);
        pracBox.setBounds(120, 400, 600, 40);

        // Back button
        backBtn.setFont(Constants.FONT20);
        backBtn.addActionListener(scheduleCourseHandler);

        // Apply button
        applyBtn.setFont(Constants.FONT20);
        applyBtn.addActionListener(scheduleCourseHandler);

        // set the year Box and term Box
        addYear();
        addTerm();

        // add to the panel
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



    // ============================ Helper Methods =================================
    /**
     * A helper method to set the frame.
     */
    protected void setFrame() {

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
        pracBox.removeAllItems();
    }

    /**
     * A helper method to add term to the termBox.
     *
     */
    private void addTerm(){
        termBox.removeAllItems();
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    /**
     * A helper method to add year to the yearBox.
     *
     */
    private void addYear(){
        yearBox.removeAllItems();
        yearBox.addItem("2021");
        yearBox.addItem("2022");
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
            lectureBox.addItem(s);
        }
    }

    /**
     * A helper method to set the tutorialBox.
     *
     * @param input a list of string that need to be put into the tutorial box.
     */
    public void setTutBox(ArrayList<String> input){
        for (String s : input) {
            tutBox.addItem(s);
        }
    }

    /**
     * A helper method to set the pracBox.
     *
     * @param input a list of string that need to be put into the practical box.
     */
    public void setpracBox(ArrayList<String> input){
        for (String s : input) {
            pracBox.addItem(s);
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
    public Course getLec() {return this.lec;}

    /**
     * A helper method to get the tutorial.
     *
     * @return the tutorial section being selected
     */
    public Course getTut() {
        return this.tut;
    }

    /**
     * A helper method to get the practical.
     *
     * @return the practical section being selected
     */
    public Course getPrac() {
        return this.prac;
    }

    /**
     *
     * The main method of the ScheduleCourseScreen.
     */
    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new ScheduleCourseScreen(controller, screen);
    }
}
