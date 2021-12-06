package demoGUI.userview;

import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;
import demoGUI.handler.TimeTableScreenController;
import demoGUI.util.DimensionUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.TableView;
import java.awt.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class TimeTableScreen extends JFrame {
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
    private JTabbedPane timeTableTabs;
    private JPanel UnderTabsPanel;
    private JScrollPane TimeTableScrollPane;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private JComboBox<String> comboBox4;
    private JComboBox<String> comboBox5;
    private JComboBox<String> comboBox6;
    private JComboBox<String> comboBox7;
    private JLabel reminderLabel;
    TimeTableScreenController timeTableScreenController;
    List<TableCellEditor> editors = new ArrayList<>(7);

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
    }

    public void refreshTimetableTabs(TimeTableManager manager) {
        timeTableTabs.removeAll();
        //cycle through the timetables in manager using the name of the timetables
        for (String term : manager.getTerms()) {
            JPanel panel = new JPanel();

            //add timetable
            JTable table = fillTimeTable(manager.getTimetable(term));
            table.setRowHeight(20);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setFillsViewportHeight(true);

            Dimension size = new Dimension(144,20);
            //add reminder laber
            JLabel reminderLabel = new JLabel("Reminder: ");
            reminderLabel.setPreferredSize(new Dimension(133, 20));
            panel.add(reminderLabel);
            //add comboxBox
            for (int i=0; i <=6; i++){
                JComboBox<String> comboBox = new JComboBox<String>();
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
            TableColumn firstColumn = table.getColumnModel().getColumn(0);
            firstColumn.setPreferredWidth(150);

            panel.add(table);
            timeTableTabs.addTab(term, panel);
        }
    }

//TODO needs window pop up when there is a conflict


    //TODO extract method here
    private JTable fillTimeTable(TimeTable table) {
            JTable jtable = new JTable(25, 8);
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

            //schedule no task objects
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
        return jtable;
        }


        private void setFrame (Rectangle bounds){
            // Window's icon
            URL resource = OperatorScreen.class.getClassLoader().getResource("pic2.jpg");
            assert resource != null;
            Image image = new ImageIcon(resource).getImage();
            setIconImage(image);

            setBounds(bounds);

            // Full screen
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setLocationRelativeTo(null);
            setSize(1200, 900);
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
