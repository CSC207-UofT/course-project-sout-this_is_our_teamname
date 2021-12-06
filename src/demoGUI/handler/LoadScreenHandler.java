package demoGUI.handler;

import Helpers.Constants;
import demoGUI.userview.LoadScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadScreenHandler implements ActionListener{
    private final LoadScreen loadscreen;

    public LoadScreenHandler(LoadScreen loadscreen){
        this.loadscreen = loadscreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backChoosing();

        } else if ("Load".equals(text)){
            try{
                //load timetables
                loadscreen.getController().getFactory().setScreen(loadscreen);
                loadscreen.getController().runCommand(Constants.LOAD_DATA);
            } catch (Exception ignore){}
            //refresh timetables
            loadscreen.getScreen().refreshTimetableTabs(
                    loadscreen.getController().getFactory().getCourseManager());
            loadscreen.dispose();
        }
    }

    private void backChoosing(){
        loadscreen.dispose();
    }
}
