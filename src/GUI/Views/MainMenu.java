package GUI.Views;

import GUI.Controllers.MainMenuController;

import javax.swing.*;

public class MainMenu extends JFrame{
    private JPanel MainMenu;
    private JLabel AppNameLabel;
    private JLabel MainMenuLabel;
    private JButton settingButton;
    private JButton timeTableButton;
    private JButton exitButton;
    private MainMenuController mainMenuController;

    public MainMenu() {
        super();

        mainMenuController = new MainMenuController(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainMenu);

        settingButton.addActionListener(mainMenuController);
        timeTableButton.addActionListener(mainMenuController);
        exitButton.addActionListener(mainMenuController);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TimeTableApp");
        frame.setContentPane(new MainMenu().MainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.pack();
    }
}
