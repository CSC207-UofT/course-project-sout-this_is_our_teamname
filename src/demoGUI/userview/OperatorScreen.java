package demoGUI.userview;

import demoGUI.handler.HomeScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * This is the homeScreen of the GUI
 */

// TODO Tray Icon P8, Get text by click enter P9 9:50. jTable for timetable at P10 8:50
public class OperatorScreen extends JFrame{

    JLabel title = new JLabel("TimeTable", JLabel.CENTER);
    FlowLayout flowLayout = new FlowLayout();
    JPanel centerPanel = new JPanel(flowLayout);
    JButton cmd1 = new JButton("User");
    JButton cmd2 = new JButton("Operator");

    // For tray icon refer to bilibili video P8
    SystemTray systemTray;


    HomeScreenHandler courseHandler;

    public OperatorScreen() {
        super("Setting");

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        // Schedule course
        cmd1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cmd1.addActionListener(courseHandler);

        // Schedule event
        cmd2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cmd2.addActionListener(courseHandler);


        centerPanel.add(cmd1);
        centerPanel.add(cmd2);


        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(centerPanel);


        setFrame();
        // Visibility
        setVisible(true);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void setFrame() {
        // Window's icon
        URL resource = OperatorScreen.class.getClassLoader().getResource("pic2.jpg");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);



//        if (SystemTray.isSupported()){
//            systemTray = SystemTray.getSystemTray();
//            TrayIcon trayIcon = new TrayIcon(image);
//            systemTray.add(trayIcon);
//        }
//
//        this.addWindowFocusListener(new WindowAdapter() {
//            @Override
//            public void windowIconified(WindowEvent e) {
//               homeScreen.this.dispose();
//            }
//        });
//        trayIcon


        // Set size
        setSize(800, 600);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }


    public static void main(String[] args) throws AWTException {
        new OperatorScreen();
    }
}

