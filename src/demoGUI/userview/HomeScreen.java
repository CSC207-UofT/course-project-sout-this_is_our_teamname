package demoGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class homeScreen extends JFrame{

    JLabel title = new JLabel("TimeTable", JLabel.CENTER);
    FlowLayout flowLayout = new FlowLayout();
    JPanel centerPanel = new JPanel(flowLayout);
    JButton cmd1 = new JButton("Schedule Course");
    JButton cmd2 = new JButton("Schedule Event");
    
    SystemTray systemTray;
    public homeScreen() throws AWTException {
        super("TimeTable Scheduler");
        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        cmd1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cmd2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        centerPanel.add(cmd1);
        centerPanel.add(cmd2);


        // Layout
        Spring cmd1Width = Spring.width(cmd1);
        Spring cmd2Width = Spring.width(cmd2);
        Spring spaceWidth = Spring.constant(20);
        Spring childWidth = Spring.sum(Spring.sum(cmd1Width, cmd2Width), spaceWidth);
        int offsetX = childWidth.getValue()/2;


        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(centerPanel);


        setFrame();
        // Visibility
        setVisible(true);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public void setFrame() throws AWTException {
        // Window's icon
        URL resource = WindowJFrame.class.getClassLoader().getResource("pic2.jpg");
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
        setSize(600, 400);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }
    
    
    public static void main(String[] args) throws AWTException {
        new homeScreen();
    }
}
