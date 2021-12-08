package Commands.FunctionCommands;

import Commands.Command;
import DataGetting.CSVUploader;
import Helpers.Constants;
import Helpers.InputCheckers.InputChecker;

import java.io.IOException;

/**
 *
 * Load the data to an external source
 *
 * === Attributes ===
 * loader: The CSVUploader to load the data in a csv file
 */
public class LoadDataCommand implements Command {
    private final CSVUploader loader;

    /**
     * Constructor to set the command.
     */
    public LoadDataCommand(){
        this.loader = new CSVUploader();
    }

    /**
     * Executes the command to load the data in the csv file.
     */
    @Override
    public void execute() {
        String[] prompts = {"Enter the Filename (Example: My_TimeTable for My_TimeTable for My_TimeTable_2021_WINTER)",
                "Enter the year (Example: 2021 for My_TimeTable for My_TimeTable_2021_WINTER)"};
        boolean running = true;
        while (running){
            String[] responses = InputChecker.getQuestionsAnswers(prompts);

            try {
                for (String term : new String[]{Constants.WINTER, Constants.SUMMER}){
                    this.loader.CalibrateData(responses[0], term, responses[1]);
                }
                running = false;
            } catch (IOException e){
                System.out.println("Invalid Input. Try again!");
            }
        }
        System.out.println("Loaded");
    }
}
