package Commands.FunctionCommands;

import DataLoading.CSVDownloader;
import DataLoading.DataLoader;
import Commands.Command;
import TimeTableContainers.TimeTableManager;

import java.io.IOException;
import java.util.Scanner;

public class DownloadDataCommand implements Command {
    private final TimeTableManager manager;
    private final CSVDownloader loader;

    public DownloadDataCommand(TimeTableManager manager){
        this.manager = manager;
        this.loader = new CSVDownloader();
    }

    @Override
    public void execute() {
        boolean running = true;
        while (running){
            Scanner ask = new Scanner(System.in);
            System.out.println("Enter the name for the timetables to save with");
            String chosen = ask.nextLine();

            try {
                this.loader.download(manager, chosen, "YEAR");
                running = false;
            } catch (IOException e){
                System.out.println("Cannot find timetable. Try again!");
            }
        }

        System.out.println("Downloaded");
    }
}
