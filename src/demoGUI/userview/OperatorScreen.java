package demoGUI.userview;

import Interfaces.OperatorInterface;
import demoGUI.handler.OperatorScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

/**
 * This is the setting screen of the GUI.
 */
public class OperatorScreen extends AbstractScreen{

    OperatorInterface operator;
    OperatorScreenHandler operatorScreenHandler;

    JLabel title = new JLabel("Setting", JLabel.CENTER);
    JLabel datasource = new JLabel("Data source");
    JPanel centerPanel = new JPanel(null);
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton banBtn = new JButton("Taiga");
    JButton backBtn = new JButton("Back");
    JButton applyBtn = new JButton("Apply");
    JComboBox<String> dataBox = new JComboBox<>();

    /**
     * Construct an OperatorScreen with the given screen.
     * @param screen A TimeTableScreen that stores controller.
     */
    public OperatorScreen(TimeTableScreen screen) {

        super("Settings", screen);

        operatorScreenHandler = new OperatorScreenHandler(this);

        Container contentPane = getContentPane();

        // Title
        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        datasource.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        datasource.setBounds(240, 30,120, 30);

        // Ban button
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

        // Add the components to panel.
        centerPanel.add(dataBox);
        centerPanel.add(datasource);
        southPanel.add(applyBtn);
        southPanel.add(backBtn);

        // Add panel to contentpane
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
     * Screen size, icon and display settings.
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
     * Get the current operator for the screen
     * 
     * @return operator an OperatorInterface
     */
    public OperatorInterface getOperator() {
        return operator;
    }

    /**
     * Get the current operator for the screen.
     *
     * @param operator an OperatorInterface
     */
    public void setOperator(OperatorInterface operator) {
        this.operator = operator;
    }

    /**
     * Get the current datasource in the databox.
     *
     * @return A string representation of the choice in data box.
     */
    public String getDatasource(){
        return Objects.requireNonNull(dataBox.getSelectedItem()).toString();
    }

}

