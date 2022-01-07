package Commands.FunctionCommands;

import DataLoading.CSVDownloader;
import Commands.Command;
import TimeTableContainers.TimeTableManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Downloads the data for the user to see
 */
public class DownloadDataCommand implements Command {
    private final TimeTableManager manager;
    private final CSVDownloader loader;

    public DownloadDataCommand(TimeTableManager manager){
        this.manager = manager;
        this.loader = new CSVDownloader();
    }

    /**
     * Executes the command to download the timetable.
     */
    @Override
    public void execute() {
        boolean running = true;
        while (running){
            Scanner nameOfTimetableScanner = new Scanner(System.in);
            System.out.println("Enter the name for the timetables to save with (Capitalize first letter)");
            String nameOfTimetable = nameOfTimetableScanner.nextLine();

            Scanner yearScanner = new Scanner(System.in);
            System.out.println("Enter the year for the timetables to save " +
                    "(Year must be a number):  ");
            String year = yearScanner.nextLine();

            Scanner termScanner = new Scanner(System.in);
            System.out.println("Enter the term(Term must be either Fall or Winter): ");
            String term = termScanner.nextLine();

            HashMap<String, List<List<String>>> dataMap = getData(term + " " + year);
            try {
                this.loader.download(dataMap, nameOfTimetable);
                running = false;
            } catch (IOException e){
                System.out.println("Cannot find timetable. Try again!");
            }
        }

        System.out.println("Downloaded");
    }

    /**
     * Gets the data from the manager.
     * @return hashmap of list of events in the timetable for each term.
     */
    public HashMap<String, List<List<String>>> getData(String theTerm){
        HashMap<String, List<List<String>>> datalist = new HashMap<>();

        for (String term : this.manager.getTerms()) {
            if (term.equals(theTerm)){
                datalist.put(term, this.manager.getTimetable(term).toList());
            }
        }
        return datalist;
    }
}
