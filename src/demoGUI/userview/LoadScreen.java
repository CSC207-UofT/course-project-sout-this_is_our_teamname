package demoGUI.userview;

import InterfaceAdaptors.DatabaseController;
import demoGUI.handler.LoadScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * A screen that displays the window viewed by the user
 *
 * === Attributes ===
 * title: The label to display the tile of this screen
 * centerPanel: The panel that contains all components locating at the center of the screen
 * southPanel: The panel that contains all components locating at the bottom of the screen
 * name: The label for nameTxt
 * year: The label for yearTxt
 * loadBtn: The button to load the data when clicked
 * backBtn: The button to close the screen when clicked
 * nameTxt: The textField for the user to enter the name of timetable to be loaded
 * yearTxt: The textField for the user to enter the year of timetable to be loaded
 *
 * controller: The DatabaseController for the GUI
 * loadScreenHandler: The handler to handle actions performed on the screen
 */
public class LoadScreen extends AbstractScreen{
    JLabel title = new JLabel("Please enter the name and year of the timetables that you would like to load",
            JLabel.CENTER);
    JPanel centerPanel = new JPanel(null);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel name = new JLabel("Name");
    JLabel year = new JLabel("Year");
    JButton loadBtn = new JButton("Load");
    JButton backBtn = new JButton("Back");
    JTextField nameTxt = new JTextField(20);
    JTextField yearTxt = new JTextField(4);

    DatabaseController controller;
    LoadScreenHandler loadScreenHandler;

    /**
     * Constructor to set the screen
     * @param controller The DatabaseController for the GUI
     * @param screen The window viewed by the user
     */
    public LoadScreen(DatabaseController controller, TimeTableScreen screen) {
        super("Load", screen);

        this.controller = controller;

        loadScreenHandler = new LoadScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        title.setPreferredSize(new Dimension(0, 20));

        // Load button
        loadBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loadBtn.addActionListener(loadScreenHandler);

        name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        name.setBounds(200, 40, 120, 40);

        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        nameTxt.setBounds(320, 40, 120, 40);

        year.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        year.setBounds(200, 100, 120, 40);

        yearTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        yearTxt.setBounds(320, 100, 120, 40);

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(loadScreenHandler);

        centerPanel.add(name);
        centerPanel.add(nameTxt);
        centerPanel.add(year);
        centerPanel.add(yearTxt);

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
    public String getNameString() {return nameTxt.getText();}

    /**
     * Gets the year of timetable to be loaded
     * @return the year of timetable to be loaded
     */
    public String getYearString() {return yearTxt.getText();}

    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController("gui");
        TimeTableScreen screen = new TimeTableScreen();
        new LoadScreen(controller, screen);

    }
}
