package demoGUI.handler;


import demoGUI.userview.HomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handle's schedule course button when clicked
 */


public class CourseHandler implements ActionListener {
    public CourseHandler(HomeScreen homeScreen){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Schedule Course".equals(text)){
            System.out.println("OKOK");

        } else if ("Reset".equals(text)){
            System.out.println("Sure");
        }


    }
}
