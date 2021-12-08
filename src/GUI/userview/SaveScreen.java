package GUI.userview;

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
    JLabel title = new JLabel("Please enter the name and year you would like to save your timetables with",
            JLabel.CENTER);
    JPanel centerPanel = new JPanel(null);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel name = new JLabel("Name");
    JLabel year = new JLabel("Year");
    JLabel term = new JLabel("Term");
    JButton saveBtn = new JButton("Save");
    JButton backBtn = new JButton("Back");
    JTextField nameTxt = new JTextField(20);
    JTextField yearTxt = new JTextField(4);
    JTextField termTxt = new JTextField(6);

    DatabaseController controller;
    SaveScreenHandler saveScreenHandler;

    /**
     * Constructor to set the screen
     * @param controller The DatabaseController for the GUI
     * @param screen The window viewed by the user
     */
    public SaveScreen(DatabaseController controller, TimeTableScreen screen) {
        super("Save", screen);

        this.controller = controller;

        saveScreenHandler = new SaveScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        title.setPreferredSize(new Dimension(0, 20));

        // Save button
        saveBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        saveBtn.addActionListener(saveScreenHandler);

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
        backBtn.addActionListener(saveScreenHandler);


        centerPanel.add(name);
        centerPanel.add(nameTxt);
        centerPanel.add(year);
        centerPanel.add(yearTxt);
        centerPanel.add(term);
        centerPanel.add(termTxt);

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
     * Gets the DatabaseController for the GUI
     * @return the DatabaseController for the GUI
     */
    public DatabaseController getController() {
        return controller;
    }

    /**
     * Gets the name of timetable to be saved
     * @return the name of timetable to be saved
     */
    public String getNameString() {return nameTxt.getText();}

    /**
     * Gets the year of timetable to be saved
     * @return the year of timetable to be saved
     */
    public String getYearString() {return yearTxt.getText();}

    /**
     * Gets the term of timetable to be loaded
     * @return the term of timetable to be loaded
     */
    public String getTermString() {return termTxt.getText();}


    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new SaveScreen(controller, screen);

    }
}
