package Commands.FunctionCommands;

import Commands.Command;
import DataGetting.CSVUploader;
import Helpers.Constants;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.LinkedHashMap;

/**
 *
 * Load the data to an external source
 *
 * === Attributes ===
 * loader: The CSVUploader to load the data in a csv file
 */
public class LoadDataCommand implements Command {
    private final CSVUploader loader;
    private final TimeTableManager manager;

    /**
     * Constructor to set the command.
     */
    public LoadDataCommand(TimeTableManager manager){
        this.manager = manager;
        this.loader = new CSVUploader();
    }

    /**
     * Executes the command to load the data in the csv file.
     */
    @Override
    public void execute() {
        String[] prompts = {"Enter the Filename (Example: My_TimeTable for My_TimeTable for My_TimeTable_2021_WINTER)",
                "Enter the year (Example: 2021 for My_TimeTable for " +
                        "My_TimeTable_2021_WINTER)", "Enter the term"};
        String[] responses = InputChecker.getQuestionsAnswers(prompts);

        LinkedHashMap<String, Object> data;
        try {
            data = this.loader.retrieveData(responses[0], responses[2],
                    responses[1]);
            for (Object item : data.values()){
                if (item instanceof Events) {
                    this.manager.schedule((Events) item);
                } else if (item instanceof Task){
                    this.manager.schedule((Task) item);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Invalid Input. Try again!");
        }
        System.out.println("Loaded");
    }
}
