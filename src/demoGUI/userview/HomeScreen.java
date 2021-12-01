package demoGUI.userview;

import demoGUI.handler.HomeScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomeScreen extends JFrame{

    JLabel title = new JLabel("TimeTable", JLabel.CENTER);
    FlowLayout flowLayout = new FlowLayout();
    JPanel centerPanel = new JPanel(flowLayout);
    JButton userBtn = new JButton("User");
    JButton operatorBtn = new JButton("Operator");
    
    HomeScreenHandler homeScreenHandler;

    public HomeScreen() {
        super("TimeTable Scheduler");

        homeScreenHandler = new HomeScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        userBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userBtn.addActionListener(homeScreenHandler);
        operatorBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        operatorBtn.addActionListener(homeScreenHandler);

        centerPanel.add(userBtn);
        centerPanel.add(operatorBtn);


        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(centerPanel);


        setFrame();
        // Visibility
        setVisible(true);

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public void setFrame() {
        // Window's icon
        URL resource = HomeScreen.class.getClassLoader().getResource("pic2.jpg");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        

        // Set size
        setSize(600, 400);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);
    }
    
    
    public static void main(String[] args) throws AWTException {
        new HomeScreen();
    }
}
