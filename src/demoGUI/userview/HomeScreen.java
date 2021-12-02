package demoGUI.userview;

import demoGUI.handler.HomeScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomeScreen extends JFrame{

    JLabel title = new JLabel("TimeTable", JLabel.CENTER);

    JPanel centerPanel = new JPanel(null);
    JButton userBtn = new JButton("User");
    JButton operatorBtn = new JButton("Operator");
    JButton exitBtn = new JButton("Exit");
    HomeScreenHandler homeScreenHandler;

    public HomeScreen() {
        super("TimeTable Scheduler");

        homeScreenHandler = new HomeScreenHandler(this);

        Container contentPane = getContentPane();

        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(0, 80));

        userBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userBtn.setBounds(220,20,160,40);
        userBtn.addActionListener(homeScreenHandler);
        operatorBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        operatorBtn.setBounds(220, 70 , 160, 40);
        operatorBtn.addActionListener(homeScreenHandler);

        exitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exitBtn.setBounds(220, 120, 160, 40);
        exitBtn.addActionListener(homeScreenHandler);


        centerPanel.add(userBtn);
        centerPanel.add(operatorBtn);
        centerPanel.add(exitBtn);

        title.setOpaque(false);
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(centerPanel);

        setFrame();

        // Terminate program
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public void setFrame() {
        // Window's icon
        URL resource = HomeScreen.class.getClassLoader().getResource("pic2.jpg");
        assert resource != null;
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        

        // Set size
        setSize(600, 400);

        // Show in the middle
        setLocationRelativeTo(null);

        // Fixed size
        setResizable(false);

        // Visibility
        setVisible(true);
    }
    
    
    public static void main(String[] args) throws AWTException {
        new HomeScreen();
    }
}
