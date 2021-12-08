package demoGUI.userview;

import Interfaces.OperatorInterface;
import demoGUI.handler.OperatorScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

/**
 * This is the homeScreen of the GUI
 *
 * === Attributes ===
 * title: The label to display the tile of this screen
 * datasource: The label for dataBox
 * centerPanel: The panel that contains all components locating at the center of the screen
 * southPanel: The panel that contains all components locating at the bottom of the screen
 * banBtn: The button to ban command selected when clicked
 * backBtn: The button to close the screen when clicked
 * applyBtn: The button to apply changes to the data source when clicked
 * dataBox: The comboBox that holds all the possible data source for user to select
 *
 * operator: The OperatorInterface to sets the DataGetter and bans functions by operator
 * operatorScreenHandler: The handler to handle actions performed on the screen
 */
// TODO Tray Icon P8, Get text by click enter P9 9:50. jTable for timetable at P10 8:50
public class OperatorScreen extends AbstractScreen{
    JLabel title = new JLabel("Setting", JLabel.CENTER);
    JLabel datasource = new JLabel("Data source");
    JPanel centerPanel = new JPanel(null);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton banBtn = new JButton("Taiga");
    JButton backBtn = new JButton("Back");
    JButton applyBtn = new JButton("Apply");
    JComboBox<String> dataBox = new JComboBox<>();

    OperatorInterface operator;
    OperatorScreenHandler operatorScreenHandler;

    /**
     * Constructor to set the screen
     * @param screen The window viewed by the user
     */
    public OperatorScreen(TimeTableScreen screen) {
        super("Settings", screen);
        //this.operator = operator;
        operatorScreenHandler = new OperatorScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        datasource.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        datasource.setBounds(240, 30,120, 30);
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
        dataBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dataBox.addItem("Webscraper");
        dataBox.addItem("CSVscraper");
        dataBox.addItem("Default");
        dataBox.setBounds(400, 30,120, 30);

        centerPanel.add(dataBox);
        centerPanel.add(datasource);
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
    }

    /**
     * Gets the OperatorInterface currently in use
     * @return the OperatorInterface
     */
    public OperatorInterface getOperator() {
        return operator;
    }

    /**
     * Sets the OperatorInterface to sets the DataGetter and bans functions by operator
     * @param operator the OperatorInterface
     */
    public void setOperator(OperatorInterface operator) {
        this.operator = operator;
    }

    /**
     * Gets the string representation of the selected data source user chooses
     * @return the string representation of the selected data source
     */
    public String getDatasource(){
        return Objects.requireNonNull(dataBox.getSelectedItem()).toString();
    }

}

