package DataLoading;

import TimeTableObjects.EventObjects.Task;
import Helpers.Constants;
// TODO. In short, I don't think timetable should appear here at all. If
//  timetable is here, there is a problem with the dependency still!
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Events;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that downloads a timetable manager object to a properly formatted csv file
 *
 */
public class CSVDownloader extends DataLoader {

    /**
     * Download a timetable manager object to a properly formatted csv file
     * that is reloadable but is less easy to read
     *
     * @param theManager the timetable manager that needs to be downloaded
     * @param filename the name that users want to save the file as
     * @param year the year of the timetables that users input
     */
    @Override
    public void download(TimeTableManager theManager, String filename, String year) throws IOException {
        for (String term : theManager.getTerms()) {

            // TODO Merge the two lines
            TimeTable timetable = theManager.getTimetable(term);
            List<List<String>> dataLists = TimetableToList(timetable);

            FileWriter csvWriter = new FileWriter("src\\OutputFiles\\"
                    + filename + "_" + year + "_"  + term + ".csv");

            StringBuilder heading = new StringBuilder();
            // TODO Use a for Loop on these or instantiate them directly. No
            //  need for repeated statements like this
            heading.append("Date").append(",");
            heading.append("Time").append(",");
            heading.append("Event Type").append(",");
            heading.append("Event Name").append(",");
            heading.append("Section Code").append(",");
            heading.append("Description").append(",");

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
     * TODO This method is too long. Break it off into helper methods! Also
     * TODO add comments and refactor the names to help readability!
     * TODO (i.e. dataLists and dataList. That's just confusing. I think it
     * TODO would be good to name them better) See naming conventions in
     * TODO JavaAndExceptions.pdf in Week 4.
     *
     * @param timetable the timetable that needs to be converted
     */
    private List<List<String>> TimetableToList(TimeTable timetable) {
        List<List<String>> dataLists = new ArrayList<>();

        for (String day : Constants.DAYS_OF_THE_WEEK) {
            for (int n = 0; n <= 23; n ++) {
                if (timetable.getCalendar().get(day)[n] != null) {
                    List<String> datalist = new ArrayList<>();
                    datalist.add(0, day);
                    Events item = timetable.getCalendar().get(day)[n];
                    datalist.add(1, item.getStartTime().toString() +
                            "-" + item.getEndTime().toString());
                    datalist.addAll(timetable.getCalendar().get(day)[n].reconstruct());
                    dataLists.add(datalist);
                }
            }
            if (!(timetable.getTaskCalendar().get(day).isEmpty())) {
                List<String> taskList = new ArrayList<>();
                taskList.add(day);
                taskList.add("Tasks");
                for (Task task : timetable.getTaskCalendar().get(day)) {
                    taskList.addAll(task.reconstruct());
                }
                dataLists.add(taskList);
            }
        }
        return dataLists;
    }
}
