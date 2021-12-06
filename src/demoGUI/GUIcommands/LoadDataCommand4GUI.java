package demoGUI.GUIcommands;

import Commands.Command;
import DataGetting.CSVUploader;
import Helpers.Constants;
import demoGUI.userview.LoadScreen;

import java.io.IOException;

/**
 *
 * Load the data to an external source
 */
public class LoadDataCommand4GUI implements Command {
    private final LoadScreen screen;
    private final CSVUploader loader;

    public LoadDataCommand4GUI(LoadScreen screen){
        this.screen = screen;
        this.loader = new CSVUploader();
    }

    @Override
    public void execute() {
        String filename = screen.getNameString();
        String year = screen.getYearString();
        boolean running = true;
        while (running){
            try {
                for (String term : new String[]{Constants.WINTER, Constants.SUMMER}){
                    this.loader.CalibrateData(filename, term, year);
                }
                running = false;
            } catch (IOException e){
                System.out.println("Invalid Input. Try again!");
            }
        }
        System.out.println("Loaded");
    }
}
