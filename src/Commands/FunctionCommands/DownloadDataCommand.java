package Commands.FunctionCommands;

import DataLoading.CSVDownloader;
import Commands.Command;
import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;

import java.io.IOException;
import java.util.ArrayList;
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
            Scanner ask = new Scanner(System.in);
            System.out.println("Enter the name for the timetables to save with (Example: My_TimeTable 2021)");
            String chosen = ask.nextLine();

            String[] keyInfo = chosen.split(" ");
            String filename = keyInfo[0];
            String year = keyInfo[1];

            HashMap<String, List<List<String>>> dataMap = getData();
            try {
                this.loader.download(dataMap, filename, year);
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
    public HashMap<String, List<List<String>>> getData(){
        HashMap<String, List<List<String>>> datalist = new HashMap<>();

        for (String term : this.manager.getTerms()) {
            datalist.put(term, TimetableToList(this.manager.getTimetable(term)));
        }
        return datalist;
    }

    /**
     * Convert a timetable to a list of lists of strings that contains the data
     *
     * @param timetable the timetable that needs to be converted
     */
    private List<List<String>> TimetableToList(TimeTable timetable) {
        List<List<String>> eventLists = new ArrayList<>();

        // Search all days in a week and get data from both the calendar and the task calendar
        for (String day : Constants.DAYS_OF_THE_WEEK) {
            // Search every hour period in a day and get data from the calendar
            for (int n = 0; n <= 23; n ++) {
                // If there are non-empty data during this hour
                if (timetable.getCalendar().get(day)[n] != null) {
                    CalendarHelper(eventLists, day, timetable.getCalendar().get(day)[n]);
                }
            }
            // If there are non-empty data in the task calendar
            if (!(timetable.getTaskCalendar().get(day).isEmpty())) {
                TaskCalendarHelper(eventLists, day, timetable.getTaskCalendar().get(day));
            }
        }
        return eventLists;
    }

    /**
     * Append one more list of data to the list of lists of strings
     *
     * @param eventLists the list of lists of strings that needs to be enlarged
     * @param day the day information of the new list of data
     * @param item the event information of the new list of data
     */
    private void CalendarHelper(List<List<String>> eventLists, String day, Events item) {
        // Format one line
        List<String> eventList = new ArrayList<>();
        eventList.add(0, day);
        eventList.add(1, item.getStartTime().toString() +
                "-" + item.getEndTime().toString());
        eventList.addAll(item.reconstruct());
        // Append the line
        eventLists.add(eventList);
    }

    /**
     * Append one more list of data to the list of lists of strings
     *
     * @param eventLists the list of lists of strings that needs to be enlarged
     * @param day the day information of the new list of data
     * @param tasks the event information of the new list of data
     */
    private void TaskCalendarHelper(List<List<String>> eventLists, String day, ArrayList<Task> tasks) {
        // Format one line
        List<String> taskList = new ArrayList<>();
        taskList.add(day);
        taskList.add("Tasks");
        for (Task task : tasks) {
            taskList.addAll(task.reconstruct());
        }
        // Append the line
        eventLists.add(taskList);
    }
}
