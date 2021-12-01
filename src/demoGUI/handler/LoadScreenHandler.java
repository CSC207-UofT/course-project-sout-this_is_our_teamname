package demoGUI.handler;

import DataGetting.CSVUploader;
import Helpers.Constants;
import demoGUI.saveandload.ChoosingSaveLoadScreen;
import demoGUI.saveandload.LoadScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class LoadScreenHandler implements ActionListener{
    private LoadScreen loadscreen;

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
            String name = loadscreen.getNameString();
            String year = loadscreen.getYearString();
            CSVUploader loader = new CSVUploader();
            String[] terms = {Constants.FALL, Constants.WINTER};
            for (String term : terms) {
                try {
                    loader.CalibrateData(name, term, year);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void backChoosing(){
        new ChoosingSaveLoadScreen();
        loadscreen.dispose();
    }
}
