package DataLoading;

import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that downloads a timetable manager object to a properly formatted csv file
 *
 */
public class CSVDownloader{

    /**
     * Download a timetable manager object to a properly formatted csv file
     * that is reloadable but is less easy to read
     *
     * @param theManager the timetable manager that needs to be downloaded
     * @param filename the name that users want to save the file as
     * @param year the year of the timetables that users input
     */
    public void download(TimeTableManager theManager, String filename, String year) throws IOException {
        for (String term : theManager.getTerms()) {

            List<List<String>> dataLists = TimetableToList(theManager.getTimetable(term));

            FileWriter csvWriter = new FileWriter("src\\OutputFiles\\"
                    + filename + "_" + year + "_"  + term + ".csv");

            StringBuilder heading = new StringBuilder();
            String[] headingString = {"Date", "Time", "Event Type", "Event Name", "Section Code", "Description"};
            for (String word : headingString) {
                heading.append(word).append(",");
            }

            csvWriter.append(heading.append("\n"));

            for (List<String> datalist : dataLists) {
                csvWriter.append(String.join(",", datalist));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        }
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
     * @param item the event information of the new list of data
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
