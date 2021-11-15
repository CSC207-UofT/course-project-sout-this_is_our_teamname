package FunctionsAndCommands.Commands.FunctionCommands;

import DataCollection.DataLoader;
import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.TimeTableManager;

import java.io.IOException;
import java.util.Scanner;

public class SaveDataCommand implements Command {
    private final TimeTableManager manager;
    private final DataLoader loader;

    public SaveDataCommand(TimeTableManager manager){
        this.manager = manager;
        this.loader = new DataLoader();
    }

    @Override
    public void execute() {
        boolean running = true;
        while (running){
            Scanner ask = new Scanner(System.in);
            System.out.println("Enter the TimeTable to Save (Fall/Winter)");
            String chosen = ask.nextLine();

            try {
                this.loader.DownloadToUnreloadable(manager, chosen);
                running = false;
            } catch (IOException e){
                System.out.println("Cannot find timetable. Try again!");
            }
        }
        System.out.println("Saved");
    }
}
