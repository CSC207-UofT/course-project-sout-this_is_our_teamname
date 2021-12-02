
package demoGUI.handler;

import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleCourseHandler implements ActionListener {
    private ScheduleCourseScreen scheduleCourseScreen;

    public ScheduleCourseHandler(ScheduleCourseScreen scheduleCourseScreen){
        this.scheduleCourseScreen = scheduleCourseScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backUser();
        }
    }

    private void backUser(){
        scheduleCourseScreen.dispose();
    }

}

