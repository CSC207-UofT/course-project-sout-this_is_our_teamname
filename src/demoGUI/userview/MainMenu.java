package demoGUI.userview;

import demoGUI.handler.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainMenu extends JFrame{
    private JPanel MainMenu;
    private JLabel AppNameLabel;
    private JLabel MainMenuLabel;
    private JButton settingButton;
    private JButton timeTableButton;
    private JButton exitButton;

    public MainMenu() {
        super("Main Menu");

        MainMenuController mainMenuController = new MainMenuController(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainMenu);

        settingButton.addActionListener(mainMenuController);
        timeTableButton.addActionListener(mainMenuController);
        exitButton.addActionListener(mainMenuController);

        setFrame();
    }

    public void setFrame() {
        // Window's icon
        URL resource = MainMenu.class.getClassLoader().getResource("pic2.jpg");
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

    public static void main(String[] args) {
        new JFrame();
    }
}
