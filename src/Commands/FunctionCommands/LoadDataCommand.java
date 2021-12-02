package Commands.FunctionCommands;

import Commands.Command;
import DataGetting.CSVUploader;
import Helpers.InputCheckers.InputChecker;

import java.io.IOException;

public class LoadDataCommand implements Command {
    private final CSVUploader loader;

    public LoadDataCommand(){
        this.loader = new CSVUploader();
    }

    @Override
    public void execute() {
        String[] prompts = {"Enter the Filename",
                "Enter the year",
                "Enter the term"};
        boolean running = true;
        while (running){
            String[] responses = InputChecker.getQuestionsAnswers(prompts);

            try {
                this.loader.CalibrateData(responses[0],
                        responses[1] + responses[2], "Something Else");
                running = false;
            } catch (IOException e){
                System.out.println("Invalid Input. Try again!");
            }
        }
        System.out.println("Loaded");
    }
}
