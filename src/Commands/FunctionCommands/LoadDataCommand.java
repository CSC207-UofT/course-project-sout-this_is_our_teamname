package Commands.FunctionCommands;

import Commands.Command;
import Commands.CreationCommands.MakeCourseCommand;
import DataLoading.DataLoader;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTableManager;

import java.io.IOException;
import java.util.Scanner;

public class LoadDataCommand implements Command {
    private final TimeTableManager manager;
    private final DataLoader loader;

    public LoadDataCommand(TimeTableManager manager){
        this.manager = manager;
        this.loader = new DataLoader();
    }

    @Override
    public void execute() {
        String[] prompts = {"Enter the Filename", "Enter the year", "Enter " +
                "the term"};
        boolean running = true;
        while (running){
            String[] responses = InputChecker.getQuestionsAnswers(prompts);

            try {
                this.loader.upload(responses[0], responses[1] + responses[2],
                        this.manager);
                running = false;
            } catch (IOException e){
                System.out.println("Invalid Input. Try again!");
            }
        }
        System.out.println("Loaded");
    }
}
