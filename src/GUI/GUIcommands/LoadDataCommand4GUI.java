package GUI.GUIcommands;

import Commands.Command;
import DataGetting.CSVUploader;
import Helpers.Constants;
import GUI.userview.LoadScreen;

import java.io.IOException;

/**
 *
 * Load the data to an external source
 *
 * === Attributes ===
 * screen: The window viewed by the user when loading data
 * loader: The CSVUploader to load the data in a csv file
 */
public class LoadDataCommand4GUI implements Command {
    private final LoadScreen screen;
    private final CSVUploader loader;

    /**
     * Constructor to set the command.
     * @param screen is the window viewed by the user when loading data
     */
    public LoadDataCommand4GUI(LoadScreen screen){
        this.screen = screen;
        this.loader = new CSVUploader();
    }

    /**
     * Executes the command to load the data in the csv file.
     */
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
