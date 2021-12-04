package demoGUI.handler;

import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleEventHandler implements ActionListener {
    private ScheduleEventScreen scheduleEventScreen;

    public ScheduleEventHandler(ScheduleEventScreen scheduleEventScreen){
        this.scheduleEventScreen = scheduleEventScreen;
    }
    //TODO add exception diag window for when inputs are incorrect, such as endtime equal or earlier then starttime
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backUser();
        }
    }

    private void backUser(){
        scheduleEventScreen.dispose();
    }

}

