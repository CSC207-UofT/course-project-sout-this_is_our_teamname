package GUI.userview;

import Helpers.Constants;
import InterfaceAdaptors.DatabaseController;
import GUI.handler.LoadScreenHandler;

import javax.swing.*;
import java.awt.*;

/**
 * A screen that displays the window viewed by the user
 *
 * === Attributes ===
 * title: The label to display the tile of this screen
 * centerPanel: The panel that contains all components locating at the center of the screen
 * southPanel: The panel that contains all components locating at the bottom of the screen
 * name: The label for nameTxt
 * year: The label for yearTxt
 * term: The label for termTxt
 * loadBtn: The button to load the data when clicked
 * backBtn: The button to close the screen when clicked
 * nameTxt: The textField for the user to enter the name of timetable to be loaded
 * yearTxt: The textField for the user to enter the year of timetable to be loaded
 * termTxt: The textField for the user to enter the term of timetable to be saved
 *
 * controller: The DatabaseController for the GUI
 * loadScreenHandler: The handler to handle actions performed on the screen
 */
public class LoadScreen extends AbstractScreen{
    private final static JLabel title = new JLabel("Please enter the name " +
            "and year of the timetables that you would like to load",
            JLabel.CENTER);

    // Layout information
    private final static JPanel centerPanel = new JPanel(null);
    private final static JPanel southPanel =
            new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final static JLabel name = new JLabel("Name");
    private final static JLabel year = new JLabel("Year");
    private final static JLabel term = new JLabel("Term");
    private final static JButton loadBtn = new JButton("Load");
    private final static JButton backBtn = new JButton("Back");
    private final static JTextField nameTxt = new JTextField(20);
    private final static JTextField yearTxt = new JTextField(4);
    private final static JTextField termTxt = new JTextField(6);

    // TODO Controller can be inherited. See class AbstractScreen
    DatabaseController controller;
    LoadScreenHandler loadScreenHandler;

    /**
     * Constructor to set the screen
     * @param controller The DatabaseController for the GUI
     * @param screen The window viewed by the user
     */
    public LoadScreen(DatabaseController controller, TimeTableScreen screen) {
        // TODO Use Constants
        super("Load", screen);

        this.controller = controller;

        loadScreenHandler = new LoadScreenHandler(this);

        Container contentPane = getContentPane();

        // TODO Make `new Font("Times New Roman", Font.PLAIN, 20)` into a
        //  constant rather than repeating over and over, FONT20 FONT15 example
        //  below

        title.setFont(Constants.FONT15);
        title.setPreferredSize(new Dimension(0, 20));

        // Load button
        loadBtn.setFont(Constants.FONT20);
        loadBtn.addActionListener(loadScreenHandler);

        name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        name.setBounds(200, 40, 120, 40);

        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        nameTxt.setBounds(320, 40, 120, 40);

        year.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        year.setBounds(200, 100, 120, 40);

        yearTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        yearTxt.setBounds(320, 100, 120, 40);

        term.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        term.setBounds(200, 160, 120, 40);

        termTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        termTxt.setBounds(320, 160, 120, 40);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(loadScreenHandler);

        centerPanel.add(name);
        centerPanel.add(nameTxt);
        centerPanel.add(year);
        centerPanel.add(yearTxt);
        centerPanel.add(term);
        centerPanel.add(termTxt);

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

    /**
     * Sets the frame of the window for display.
     */
    protected void setFrame() {
        // Set size
        setSize(800, 600);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }

    /**
     * TODO WHY??? THIS IS INHERITED!!!
     *
     * Gets the DatabaseController for the GUI
     * @return the DatabaseController for the GUI
     */
    public DatabaseController getController() {
        return controller;
    }

    /**
     * Gets the name of timetable to be loaded
     * @return the name of timetable to be loaded
     */
    public String getNameString() {
        return nameTxt.getText();
    }

    /**
     * Gets the year of timetable to be loaded
     * @return the year of timetable to be loaded
     */
    public String getYearString() {
        return yearTxt.getText();
    }

    /**
     * Gets the term of timetable to be loaded
     * @return the term of timetable to be loaded
     */
    public String getTermString() {
        return termTxt.getText();
    }

    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new LoadScreen(controller, screen);
    }
}
