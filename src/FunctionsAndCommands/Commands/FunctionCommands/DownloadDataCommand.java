package FunctionsAndCommands.Commands.FunctionCommands;

import DataCollection.DataLoader;
import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.TimeTableManager;

import java.io.IOException;
import java.util.Scanner;

public class DownloadDataCommand implements Command {
    private final TimeTableManager manager;
    private final DataLoader loader;

    public DownloadDataCommand(TimeTableManager manager){
        this.manager = manager;
        this.loader = new DataLoader();
    }

    @Override
    public void execute() {
        boolean running = true;
        while (running){
            Scanner ask = new Scanner(System.in);
            System.out.println("Enter the name for the timetables to save with");
            String chosen = ask.nextLine();

            try {
                this.loader.DownloadToReloadable(manager, chosen);
                running = false;
            } catch (IOException e){
                System.out.println("Cannot find timetable. Try again!");
            }
        }

        System.out.println("Downloaded");
    }
}
