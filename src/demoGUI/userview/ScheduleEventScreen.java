package demoGUI.userview;

import demoGUI.handler.ScheduleEventHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ScheduleEventScreen extends JFrame{

    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel eventName = new JLabel("Event Name");
    JTextField nameTxt = new JTextField();
    JLabel startTime = new JLabel("Start Time");
    JComboBox<String> startTimeBox = new JComboBox<>();
    JLabel endTime = new JLabel("End Time");
    JComboBox<String> endTimeBox = new JComboBox<>();
    JLabel location = new JLabel("Location");
    JTextField locationTxt = new JTextField();
    JLabel day = new JLabel("Day of the week");
    JComboBox<String> dayBox = new JComboBox<>();
    JLabel term = new JLabel("Term");
    JComboBox<String> termBox = new JComboBox<>();
    JLabel type = new JLabel("Type");
    JComboBox<String> typeBox = new JComboBox<>();
    JButton backBtn = new JButton("Back");
    JButton applyBtn = new JButton("Apply");

    ScheduleEventHandler scheduleEventHandler;

    public ScheduleEventScreen() {
        super("Schedule Event");
        scheduleEventHandler = new ScheduleEventHandler(this);
        Container contentPane = getContentPane();

        eventName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameTxt.setPreferredSize(new Dimension(100, 30));
        startTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        endTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        location.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        locationTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        locationTxt.setPreferredSize(new Dimension(100, 30));
        day.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        term.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        type.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        // Back button
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(scheduleEventHandler);

        // Apply button
        applyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        applyBtn.addActionListener(scheduleEventHandler);

        // Dropdown menu
        addDropdowntime(startTimeBox);
        addDropdowntime(endTimeBox);
        addWeekday(dayBox);
        addTerm(termBox);
        addType(typeBox);


        centerPanel.add(eventName);
        centerPanel.add(nameTxt);
        centerPanel.add(startTime);
        centerPanel.add(startTimeBox);
        centerPanel.add(endTime);
        centerPanel.add(endTimeBox);
        centerPanel.add(location);
        centerPanel.add(locationTxt);
        centerPanel.add(day);
        centerPanel.add(dayBox);
        centerPanel.add(term);
        centerPanel.add(termBox);
        centerPanel.add(type);
        centerPanel.add(typeBox);

        southPanel.add(backBtn);
        southPanel.add(applyBtn);


        contentPane.add(centerPanel);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        setFrame();

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void setFrame() {
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

        // Visibility
        setVisible(true);
    }

    private void addDropdowntime(JComboBox<String> Timebox) {
        Timebox.addItem("01:00AM");
        Timebox.addItem("02:00AM");
        Timebox.addItem("03:00AM");
        Timebox.addItem("04:00AM");
        Timebox.addItem("05:00AM");
        Timebox.addItem("06:00AM");
        Timebox.addItem("07:00AM");
        Timebox.addItem("08:00AM");
        Timebox.addItem("09:00AM");
        Timebox.addItem("10:00AM");
        Timebox.addItem("11:00AM");
        Timebox.addItem("12:00AM");
        Timebox.addItem("13:00PM");
        Timebox.addItem("14:00PM");
        Timebox.addItem("15:00PM");
        Timebox.addItem("16:00PM");
        Timebox.addItem("17:00PM");
        Timebox.addItem("18:00PM");
        Timebox.addItem("19:00PM");
        Timebox.addItem("20:00PM");
        Timebox.addItem("21:00PM");
        Timebox.addItem("22:00PM");
        Timebox.addItem("23:00PM");
        Timebox.addItem("12:00PM");
    }

    private void addWeekday(JComboBox<String> dayBox) {
        dayBox.addItem("Monday");
        dayBox.addItem("Tuesday");
        dayBox.addItem("Wednesday");
        dayBox.addItem("Thursday");
        dayBox.addItem("Friday");
        dayBox.addItem("Saturday");
        dayBox.addItem("Sunday");
    }

    private void addTerm(JComboBox<String> termBox){
        termBox.addItem("Fall");
        termBox.addItem("Winter");
    }

    private void addType(JComboBox<String> typeBox){
        typeBox.addItem("Activity");
        typeBox.addItem("Task");
    }
    public static void main(String[] args) {
        new ScheduleEventScreen();

    }

}
