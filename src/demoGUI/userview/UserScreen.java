package demoGUI.userview;

import demoGUI.handler.UserScreenHandler;
import demoGUI.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UserScreen extends JFrame {

    FlowLayout flowLayout = new FlowLayout();
    // For timetable
    JPanel centerPanel = new JPanel(flowLayout);

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JButton courseBtn = new JButton("Schedule Course");
    JButton eventBtn = new JButton("Schedule Task/Activity");
    JButton solBtn = new JButton("Solver");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton backBtn = new JButton("Back");


    UserScreenHandler userScreenHandler;

    public UserScreen() {
        super("Timetable");

        Rectangle bounds = DimensionUtil.getBounds();

        userScreenHandler = new UserScreenHandler(this);

        Container contentPane = getContentPane();

        // Schedule course
        courseBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        courseBtn.addActionListener(userScreenHandler);

        // Schedule event
        eventBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        eventBtn.addActionListener(userScreenHandler);

        // Solver
        solBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        solBtn.addActionListener(userScreenHandler);

        // Back
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backBtn.addActionListener(userScreenHandler);

        // Save
        saveBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        saveBtn.addActionListener(userScreenHandler);

        // Load
        loadBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loadBtn.addActionListener(userScreenHandler);


        southPanel.add(backBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);

        northPanel.add(courseBtn);
        northPanel.add(eventBtn);
        northPanel.add(solBtn);
        northPanel.add(saveBtn);
        northPanel.add(loadBtn);


        contentPane.add(northPanel,BorderLayout.NORTH);

        setFrame(bounds);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        new UserScreen();
    }
}
