package demoGUI.handler;

import Helpers.Constants;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.GUICommandFactory;
import demoGUI.userview.SaveScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveScreenHandler implements ActionListener{
    private final SaveScreen savescreen;

    public SaveScreenHandler(SaveScreen savescreen){
        this.savescreen = savescreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backChoosing();

        } else if ("Save".equals(text)){
            try{
                //save timetables
                CommandFactory factory = savescreen.getController().getFactory();
                ((GUICommandFactory) factory).setScreen(savescreen);
                savescreen.getController().runCommand(Constants.DOWNLOAD_TIMETABLE);
            } catch (Exception ignore){}
            //refresh timetables
            savescreen.getScreen().refreshTimetableTabs(
                    savescreen.getController().getFactory().getCourseManager());
            savescreen.dispose();
        }
    }

    private void backChoosing(){
        savescreen.dispose();
    }
}
