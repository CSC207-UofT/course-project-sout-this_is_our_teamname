package demoGUI.userview;

import demoGUI.handler.TimeTableScreenController;
import demoGUI.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TimeTableScreen extends JFrame{
    private JPanel TimeTableRootPanel;
    private JPanel ButtonsPanel;
    private JPanel TabsPanel;
    private JPanel TimeTablePanel;
    private JButton courseButton;
    private JButton taskActivityButton;
    private JButton solverButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton settingsButton;
    private JPanel TitilePanel;
    private JLabel titleLable;
    private JTable table1;
    TimeTableScreenController timeTableScreenController;

    public TimeTableScreen() {
        super();

        timeTableScreenController = new TimeTableScreenController(this);
        this.setContentPane(TimeTableRootPanel);

        // add button functions
        courseButton.addActionListener(timeTableScreenController);
        taskActivityButton.addActionListener(timeTableScreenController);
        solverButton.addActionListener(timeTableScreenController);
        saveButton.addActionListener(timeTableScreenController);
        loadButton.addActionListener(timeTableScreenController);
        settingsButton.addActionListener(timeTableScreenController);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Rectangle bounds = DimensionUtil.getBounds();
        setFrame(bounds);
    }

    private void setFrame(Rectangle bounds) {
        // Window's icon
        URL resource = OperatorScreen.class.getClassLoader().getResource("pic2.jpg");
        assert resource != null;
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        setBounds(bounds);

        // Full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);

        // Visibility
        setVisible(true);
    }

    public static void main(String[] args) throws AWTException {
        new TimeTableScreen();
    }
}
