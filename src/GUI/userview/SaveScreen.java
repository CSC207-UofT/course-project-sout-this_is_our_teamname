package GUI.userview;

import Helpers.Constants;
import InterfaceAdaptors.DatabaseController;
import GUI.handler.SaveScreenHandler;

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
 * nameGuide: The guide label for name input guidelines
 * yearGuide: The guide label for year input guidelines
 * termGuide: The guide label for term input guidelines
 * saveBtn: The button to save the data when clicked
 * backBtn: The button to close the screen when clicked
 * nameTxt: The textField for the user to enter the name of timetable to be saved
 * yearTxt: The textField for the user to enter the year of timetable to be saved
 * termTxt: The textField for the user to enter the term of timetable to be saved
 *
 * controller: The DatabaseController for the GUI
 * saveScreenHandler: The handler to handle actions performed on the screen
 */
public class SaveScreen extends AbstractScreen{
    private static final JLabel title = new JLabel("Please enter the name and" +
            " year you would like to save your timetables with",
            JLabel.CENTER);
    private static final JPanel centerPanel = new JPanel(null);
    private static final JPanel southPanel =
            new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private static final JLabel name = new JLabel("Name");
    private static final JLabel year = new JLabel("Year");
    private static final JLabel term = new JLabel("Term");
    private final static JLabel nameGuide = new JLabel("Name must be no longer than 20 " +
            "characters with Capitalized first letter.");
    private final static JLabel yearGuide = new JLabel("Year must be a number.");
    private final static JLabel termGuide = new JLabel("Term must be either Fall or Winter");
    private static final JButton saveBtn = new JButton("Save");
    private static final JButton backBtn = new JButton("Back");
    private static final JTextField nameTxt = new JTextField(20);
    private static final JTextField yearTxt = new JTextField(4);
    private static final JTextField termTxt = new JTextField(6);

    SaveScreenHandler saveScreenHandler;

    /**
     * Constructor to set the screen
     * @param controller The DatabaseController for the GUI
     * @param screen The window viewed by the user
     */
    public SaveScreen(DatabaseController controller, TimeTableScreen screen) {
        super(Constants.SAVE, screen);

        saveScreenHandler = new SaveScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(Constants.FONT20);
        title.setPreferredSize(new Dimension(0, 20));

        // Save button
        saveBtn.setFont(Constants.FONT20);
        saveBtn.addActionListener(saveScreenHandler);

        name.setFont(Constants.FONT20);
        name.setBounds(200, 40, 120, 40);

        nameTxt.setFont(Constants.FONT20);
        nameTxt.setBounds(320, 40, 120, 40);

        nameGuide.setFont(Constants.FONT15);
        nameGuide.setBounds(200, 85, 600, 40);

        year.setFont(Constants.FONT20);
        year.setBounds(200, 160, 120, 40);

        yearTxt.setFont(Constants.FONT20);
        yearTxt.setBounds(320, 160, 120, 40);

        yearGuide.setFont(Constants.FONT15);
        yearGuide.setBounds(200, 205, 600, 40);

        term.setFont(Constants.FONT20);
        term.setBounds(200, 280, 120, 40);

        termTxt.setFont(Constants.FONT20);
        termTxt.setBounds(320, 280, 120, 40);

        termGuide.setFont(Constants.FONT15);
        termGuide.setBounds(200, 325, 600, 40);

        // Back button
        backBtn.setFont(Constants.FONT20);
        backBtn.addActionListener(saveScreenHandler);


        centerPanel.add(name);
        centerPanel.add(nameTxt);
        centerPanel.add(year);
        centerPanel.add(yearTxt);
        centerPanel.add(term);
        centerPanel.add(termTxt);
        centerPanel.add(nameGuide);
        centerPanel.add(yearGuide);
        centerPanel.add(termGuide);

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
     * Gets the name of timetable to be saved
     * @return the name of timetable to be saved
     */
    public String getNameString() {
        return nameTxt.getText();
    }

    /**
     * Gets the year of timetable to be saved
     * @return the year of timetable to be saved
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
        new SaveScreen(controller, screen);

    }
}