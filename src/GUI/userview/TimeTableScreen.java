package GUI.userview;

import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;

import GUI.handler.TimeTableScreenHandler;
import GUI.util.DimensionUtil;

import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * A screen that displays the window viewed by the user
 *
 * === Attributes ===
 * timeTableRootPanel: The panel that contains all components on the screen
 * buttonsPanel: The panel that contains all the buttons on the screen
 * tabsPanel: The panel that contains the tabbedPane on the screen
 * courseButton: The button that opens ScheduleCourseScreen when clicked
 * taskActivityButton: The button that opens ScheduleEventScreen when clicked
 * saveButton: The button that opens SaveScreen when clicked
 * loadButton: The buttons that opens LoadScreen when clicked
 * titlePanel: The panel that contains the title
 * titleLabel: The label to display the tile of this screen
 * timetableTabs: The tabbedPane that contains the timetable for each term as a new tab
 *
 * timeTableScreenHandler: The handler to handle actions performed on the screen
 */
public class TimeTableScreen extends JFrame {
    private JPanel timeTableRootPanel;
    private JPanel buttonsPanel;
    private JPanel tabsPanel;
    private JButton courseButton;
    private JButton taskActivityButton;
    private JButton saveButton;
    private JButton loadButton;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JTabbedPane timetableTabs;

    TimeTableScreenHandler timeTableScreenHandler;

    /**
     * Constructor to set the screen
     */
    public TimeTableScreen() {
        super();

        timeTableScreenHandler = new TimeTableScreenHandler(this);
        this.setContentPane(timeTableRootPanel);

        // add button functions
        courseButton.addActionListener(timeTableScreenHandler);
        taskActivityButton.addActionListener(timeTableScreenHandler);
        saveButton.addActionListener(timeTableScreenHandler);
        loadButton.addActionListener(timeTableScreenHandler);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Rectangle bounds = DimensionUtil.getBounds();
        setFrame(bounds);
    }

    /**
     * Refreshes the timetableTabs every time the user schedules a new Event
     * @param manager is the TimeTableManager that stores timetables for each term
     */
    public void refreshTimetableTabs(TimeTableManager manager) {
        timetableTabs.removeAll();
        //cycle through the timetables in manager using the name of the timetables
        for (String term : manager.getTerms()) {
            JPanel panel = new JPanel();

            //add timetable
            JTable table = fillTimeTable(manager.getTimetable(term));
            table.setRowHeight(20);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setFillsViewportHeight(true);

            Dimension size = new Dimension(144,20);
            //add reminder label
            JLabel reminderLabel = new JLabel("Reminder: ");
            reminderLabel.setPreferredSize(new Dimension(133, 20));
            panel.add(reminderLabel);

            addComboBox(manager, term, panel, table, size);
            TableColumn firstColumn = table.getColumnModel().getColumn(0);
            firstColumn.setPreferredWidth(150);

            panel.add(table);
            timetableTabs.addTab(term, panel);
        }
    }

    /** adds dropdown menus for reminders.
     *
     * @param manager a timetable manager that holds all the timetables
     * @param term  which is also the name of a timetable
     * @param panel the panel where the dropdown menu will be
     * @param table the Jtable where the timetable will display
     * @param size a dimension to be used by Jcomponents
     */
    private void addComboBox(TimeTableManager manager, String term, JPanel panel, JTable table, Dimension size) {
        for (int i=0; i <=6; i++){
            JComboBox<String> comboBox = new JComboBox<>();
            for (Task task : manager.getTimetable(term).getTaskCalendar().
                            get(Constants.DAYS_OF_THE_WEEK[i]).toArray(new Task[0])) {
                comboBox.addItem(task.getName());
            }
            comboBox.setLocation(i * 150, 200);
            comboBox.setPreferredSize(size);
            panel.add(comboBox);

            //set table column with
            TableColumn tableColumn = table.getColumnModel().getColumn(i+1);
            tableColumn.setPreferredWidth(150);
        }
    }

    /**
     * Helper method to fill an empty table with Events from given TimeTable
     * @param table is the TimeTable with Events to be filled into the table
     * @return a table filled with Events from the TimeTable
     */
    private JTable fillTimeTable(TimeTable table) {
            JTable jtable = new JTable(25, 8){

                /**
                 * Shows the string content of the Jtable cell that the mouse is on
                 * @param mouseEvent is the current activity of the mouse
                 * @return the string content of the Jtable cell that the mouse is on
                 *
                 * Citation: https://stackoverflow.com/questions/9467093/how-to-add-a-tooltip-to-a-cell-in-a-jtable
                 */
                public String getToolTipText(MouseEvent mouseEvent) {
                    String tip = null;
                    java.awt.Point p = mouseEvent.getPoint();
                    int rowIndex = rowAtPoint(p);
                    int colIndex = columnAtPoint(p);

                    try {
                        tip = getValueAt(rowIndex, colIndex).toString();
                    } catch (RuntimeException e1) {
                        //catch null pointer exception if mouse is over an empty line
                    }
                    return tip;
                }
            };
        setTable(jtable);

        //schedule no task objects
        scheduleNonTask(table, jtable);
        return jtable;
    }

    /**
     *  sets the skeleton of the Jtable for populating later
     * @param jtable is the what will be displaying a timetable
     */
    private void setTable(JTable jtable) {
        //set times
        for (int i = 0; i <= 23; i++) {
            String time = Constants.TIME[i];
            jtable.getModel().setValueAt(time, i+1, 0);
        }
        for (int i = 0; i <= 6; i++) {
            //set days
            String day = Constants.DAYS_OF_THE_WEEK[i];
            jtable.getModel().setValueAt(day, 0, i + 1);
        }

        //set the hour column width
        TableColumnModel columns = jtable.getColumnModel();
        columns.getColumn(0).setPreferredWidth(20);

        //Center the text on all columns
        DefaultTableCellRenderer centreRenderer = new DefaultTableCellRenderer();
        centreRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 8; i++) {
            columns.getColumn(i).setCellRenderer(centreRenderer);
        }
    }

    /** Schedules non task objects in Jtable for display
     *
     * @param table is the timetable to be displayed on Jtable
     * @param jtable is what will be displayed
     */
    private void scheduleNonTask(TimeTable table, JTable jtable) {
        for (String day : table.getCalendar().keySet()) {
            int columnIndex = Arrays.asList(Constants.DAYS_OF_THE_WEEK).indexOf(day) + 1;

            for (int i = 0; i <= 23; i++) {
                Events event = table.getCalendar().get(day)[i];
                if (event != null) {
                    int rowIndex = i + 1;
                    jtable.getModel().setValueAt(event, rowIndex, columnIndex);
                }
            }
        }
    }

    /**
     * Sets the frame of the window for display.
     *
     * @param bounds is the bounds from the screen
     */
    private void setFrame (Rectangle bounds){
        setBounds(bounds);
            setSize(1200, 700);
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

    public static void main (String[]args) throws AWTException {
        new TimeTableScreen();
    }
}
