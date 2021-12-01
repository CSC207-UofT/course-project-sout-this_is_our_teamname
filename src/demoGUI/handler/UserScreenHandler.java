package demoGUI.handler;
import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle's schedule course button when clicked
 */
public class UserScreenHandler implements ActionListener {
    private UserScreen userScreen;

    public UserScreenHandler(UserScreen userScreen){
        this.userScreen = userScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backHome();

        } else if ("Schedule Course".equals(text)){
            System.out.println("Taiga!");
            openScheduleCourse();
        } else if ("Schedule Task/Activity".equals(text)){
            System.out.println("Aisaka!");
            openScheduleEvent();
        } else if ("Solver".equals(text)) {

        } else if ("Back".equals(text)) {
            backHome();
        }
    }

    private void backHome(){
        new HomeScreen();
        userScreen.dispose();
    }
    private void openScheduleCourse(){
        new ScheduleCourseScreen();
    }

    private void openScheduleEvent(){
        new ScheduleEventScreen();
    }

}
