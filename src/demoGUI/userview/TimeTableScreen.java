package demoGUI.userview;

import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Events;
import demoGUI.handler.TimeTableScreenController;
import demoGUI.util.DimensionUtil;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.Locale;

public class TimeTableScreen extends JFrame{
    private JPanel TimeTableRootPanel;
    private JPanel ButtonsPanel;
    private JPanel TabsPanel;
    private JButton courseButton;
    private JButton taskActivityButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton settingsButton;
    private JPanel TitlePanel;
    private JLabel titleLable;
    private JTable timeTable;
    private JScrollPane TimeTableScrollPane;
    private JTabbedPane timeTableTabs;
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
        createEmptyTable();
    }

    public void refreshTimetableTabs(TimeTableManager manager){
        timeTableTabs.removeAll();
        for (String term : manager.getTerms()) {
//            JTable table = createEmptyTable();
            JTable table = fillTimeTable(manager.getTimetable(term));
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setFillsViewportHeight(true);
            timeTableTabs.addTab(term, table);
//            JComponent panel = new JPanel();
//            panel.add(fillTimeTable(manager.getTimetable(term)));
//            timeTableTabs.addTab(term, panel);

        }
    }

//TODO conflict window pop up
    private JTable createEmptyTable(){
        JTable jtable = new JTable(25, 8);
        for (int i=0; i<=23; i++) {
            String time = Constants.TIME[i];
            jtable.getModel().setValueAt(time, i+1, 0);
        }
        for (int i=0;i<=6;i++){
            String day = Constants.DAYS_OF_THE_WEEK[i];
            jtable.getModel().setValueAt(day, 0, i+1);
        }
        //set the hour column width
        TableColumnModel columns = jtable.getColumnModel();
        columns.getColumn(0).setPreferredWidth(20);
        //Center the text on all columns
        DefaultTableCellRenderer centreRenderer = new DefaultTableCellRenderer();
        centreRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 8; i ++) {
            columns.getColumn(i).setCellRenderer(centreRenderer);
        }
        return jtable;
    }

    private JTable fillTimeTable(TimeTable table){
        JTable jtable = createEmptyTable();

        for (String day : table.getCalendar().keySet()) {
            int columnIndex =Arrays.asList(Constants.DAYS_OF_THE_WEEK).indexOf(day)+1;

            for (int i=0;i<=23;i++){
                Events event = table.getCalendar().get(day)[i];
                if (event != null) {
                    int rowIndex = i+1;
                    jtable.getModel().setValueAt(event.getName() + ": " + event, rowIndex, columnIndex);
                }
            }
        }
        return jtable;
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
//        setSize(1200, 600);
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
