package demoGUI.handler;

import DataLoading.CSVDownloader;
import demoGUI.saveandload.ChoosingSaveLoadScreen;
import demoGUI.saveandload.SaveScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveScreenHandler implements ActionListener{
    private SaveScreen savescreen;

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
            String name = savescreen.getNameString();
            String year = savescreen.getYearString();
            CSVDownloader loader = new CSVDownloader();
            //loader.download((...), name, year);
        }
    }

    private void backChoosing(){
        new ChoosingSaveLoadScreen();
        savescreen.dispose();
    }
}
