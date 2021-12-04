package demoGUI.userview;

import Helpers.Constants;
import demoGUI.handler.TimeTableScreenController;
import demoGUI.util.DimensionUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.net.URL;

public class TimeTableScreen extends JFrame{
    private JPanel TimeTableRootPanel;
    private JPanel ButtonsPanel;
    private JPanel TabsPanel;
    private JPanel TimeTablePanel;
    private JButton courseButton;
    private JButton taskActivityButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton settingsButton;
    private JPanel TitilePanel;
    private JLabel titleLable;
    private JTable timeTable;
    private JScrollPane TimeTableScrollPane;
    TimeTableScreenController timeTableScreenController;

    public TimeTableScreen() {
        super();

        timeTableScreenController = new TimeTableScreenController(this);
        this.setContentPane(TimeTableRootPanel);

        // add button functions
        courseButton.addActionListener(timeTableScreenController);
        taskActivityButton.addActionListener(timeTableScreenController);
        saveButton.addActionListener(timeTableScreenController);
        loadButton.addActionListener(timeTableScreenController);
        settingsButton.addActionListener(timeTableScreenController);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Rectangle bounds = DimensionUtil.getBounds();
        setFrame(bounds);
        createTable();
    }

    private void createTable(){
        //TODO change Object to Events?
        Object[][] data = {{"00:00AM"},{"01:00AM"},{"02:00AM"},{"03:00AM"},{"04:00AM"},{"05:00AM"},{"06:00AM"},
                {"07:00AM"}, {"08:00AM"},{"09:00AM"},{"10:00AM"},{"11:00AM"},{"12:00PM"},{"01:00PM"},{"02:00PM"},
                {"03:00PM"},{"04:00PM"},{"05:00PM"},{"06:00PM"},{"07:00PM"}, {"08:00PM"},{"09:00PM"},{"10:00PM"},
                {"11:00PM"}};
        timeTable.setModel(new DefaultTableModel(
                data,
                new String[]{"",Constants.MONDAY,Constants.TUESDAY,Constants.WEDNESDAY,Constants.THURSDAY,
                        Constants.FRIDAY,Constants.SATURDAY,Constants.SUNDAY}
        ));
        //set the hour column width
        TableColumnModel columns = timeTable.getColumnModel();
        columns.getColumn(0).setPreferredWidth(20);
        //Center the text on all columns
        DefaultTableCellRenderer centreRenderer = new DefaultTableCellRenderer();
        centreRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 8; i ++) {
            columns.getColumn(i).setCellRenderer(centreRenderer);
        }
    }

    private void setFrame(Rectangle bounds) {
        // Window's icon
        URL resource = OperatorScreen.class.getClassLoader().getResource("pic2.jpg");
        assert resource != null;
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        setBounds(bounds);

//        // Full screen
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setLocationRelativeTo(null);
        setSize(1200, 600);
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
