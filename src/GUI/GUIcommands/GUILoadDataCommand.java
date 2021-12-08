package GUI.GUIcommands;

import Commands.Command;
import DataGetting.CSVUploader;
import Helpers.Constants;
import GUI.userview.LoadScreen;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 *
 * Load the data to an external source
 *
 * === Attributes ===
 * screen: The window viewed by the user when loading data
 * loader: The CSVUploader to load the data in a csv file
 */
public class GUILoadDataCommand implements Command {
    private final LoadScreen screen;
    private final CSVUploader loader;
    private final TimeTableManager manager;

    /**
     * Constructor to set the command.
     * @param screen is the window viewed by the user when loading data
     */
    public GUILoadDataCommand(TimeTableManager manager, LoadScreen screen){
        this.screen = screen;
        this.loader = new CSVUploader();
        this.manager = manager;
    }

    /**
     * Executes the command to load the data in the csv file.
     */
    @Override
    public void execute() {
        String filename = screen.getNameString();
        String year = screen.getYearString();
        String term = screen.getTermString();

        LinkedHashMap<String, Object> data;
        try {
            data = this.loader.retrieveData(filename, term, year);
            for (Object item : data.values()){
                if (item instanceof Events) {
                    this.manager.schedule((Events) item);
                } else if (item instanceof Task){
                    this.manager.schedule((Task) item);
                }
            }
        } catch (IOException e){
            System.out.println("Invalid Input. Try again!");
        }
        System.out.println("Loaded");
    }
}
