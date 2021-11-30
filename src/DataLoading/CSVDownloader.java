package DataLoading;

import TimeTableObjects.EventObjects.Task;
import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Events;


import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that downloads a timetable manager object to a properly formatted csv file
 *
 */
public class CSVDownloader extends DataLoader {

    /**
     * Download a timetable manager object to a properly formatted csv file
     * that is reloadable but is less easy to read
     *
     * @param ttbmanager the timetable manager that needs to be downloaded
     * @param filename the name that users want to save the file as
     * @param year the year of the timetables that users input
     */
    @Override
    public void download(TimeTableManager ttbmanager, String filename, String year) throws IOException {
        for (String term : ttbmanager.getTerms()) {

            TimeTable timetable = ttbmanager.getTimetable(term);
            List<List<String>> datalists = TimetableToList(timetable);

            FileWriter csvWriter = new FileWriter("src\\OutputFiles\\"
                    + filename + "_" + year + "_"  + term + ".csv");

            StringBuilder heading = new StringBuilder();
            heading.append("Date").append(",");
            heading.append("Time").append(",");
            heading.append("Event Type").append(",");
            heading.append("Event Name").append(",");
            heading.append("Section Code").append(",");
            heading.append("Description").append(",");

            csvWriter.append(heading.append("\n"));

            for (List<String> datalist : datalists) {
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
        List<List<String>> datalists = new ArrayList<>();

        for (String day : Constants.DAYS_OF_THE_WEEK) {
            for (int n = 0; n <= 23; n ++) {
                if (timetable.getCalendar().get(day)[n] != null) {
                    List<String> datalist = new ArrayList<>();
                    datalist.add(0, day);
                    Events item = timetable.getCalendar().get(day)[n];
                    datalist.add(1, item.getStartTime().toString() +
                            "-" + item.getEndTime().toString());
                    datalist.addAll(timetable.getCalendar().get(day)[n].reconstruct());
                    datalists.add(datalist);
                }
            }
            if (!(timetable.getTaskCalendar().get(day).isEmpty())) {
                List<String> tasklist = new ArrayList<>();
                tasklist.add(day);
                tasklist.add("Tasks");
                for (Task task : timetable.getTaskCalendar().get(day)) {
                    tasklist.addAll(task.reconstruct());
                }
                datalists.add(tasklist);
            }
        }
        return datalists;
    }
}
