package DataGetting;

import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Task;
import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;


import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that uploads a properly formatted reloadable
 * csv file to a timetable manager object
 */
public class CSVUploader extends DataGetter<TimeTable> {

    /**
     * Constructor of the CSVUploader. Reads and filters the data correctly
     * into the data hashmap.
     *
     * @param filename the name of the file
     * @param term the term of the timetable
     * @param year the year of the timetable
     * @exception FileNotFoundException Throws file not found exception if the information for the
     * given information of the file is not found.
     */
    @Override
    public void CalibrateData(String filename, String term,
                              String year) throws FileNotFoundException {
        String filepath = "src\\OutputFiles\\" + filename + "_" + year + "_"  + term + ".csv";
        String[][] data = read(filepath);
        String[][] meaningfuldata = MeaningfulDataHelper(data);
        TimeTable timetable = new TimeTable();
        for (String[] row : meaningfuldata) {
            if (!Objects.equals(row[1], "Tasks")) {
                String[] times = row[1].split("-")
                if (Objects.equals(row[2], "Activity")) {
                    Activity event = new Activity(LocalTime.of(Integer.valueOf(times[0].substring(0, 2)), 0, 0),
                            LocalTime.of(Integer.valueOf(times[1].substring(0, 2)), 0, 0), row[0], term, row[5]);
                    evemt.setName(row[3]);
                    timetable.schedule(event);
                }
                // TODO: Find a way to know waitlist status
                //else if (Objects.equals(row[2], "CourseSection")) {
                //    CourseSection event = new CourseSection(row[3],
                //            LocalTime.of(Integer.valueOf(times[0].substring(0, 2)), 0, 0),
                //            LocalTime.of(Integer.valueOf(times[1].substring(0, 2)), 0, 0),
                //            row[0], term, row[4], (...));
                //    event.setDescription(row[5]);
                //    timetable.schedule(event);
                //}
            }
            else {
                String[] smallmeaningfuldata = SmallMeaningfulDataHelper(row);
                for (String taskstring : smallmeaningfuldata) {
                    Task task = new Task(LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59), row[0], term);
                    task.setName(taskstring);
                    timetable.addTasks(task);
                }
            }
        }
        placeToData(term, timetable);
    }

    /**
     * Process an array of arrays of strings to one which
     * contains only meaningful data
     *
     * @param data the array needs to be processed
     */
    private String[][] MeaningfulDataHelper(String[][] data) {
        String[][] meaningfuldata = new String[1][data.length - 1];
        for (int i = 0; i + 1 < data.length; i++) {
            meaningfuldata[i] = data[i + 1];
        }
        return meaningfuldata;
    }

    /**
     * Process an array of strings to one which contains only meaningful data
     *
     * @param data the array needs to be processed
     */
    private String[] SmallMeaningfulDataHelper(String[] data) {
        String[] smallmeaningfuldata = new String[data.length - 2];
        for (int i = 0; i + 2 < data.length; i++) {
            smallmeaningfuldata[i] = data[i + 2];
        }
        return smallmeaningfuldata;
    }

    /**
     * Read and return the data contained in a csv file at specific location as
     * an array of arrays of strings
     *
     * @param filepath the path of the csv file that needs to be read
     */
    private String[][] read(String filepath) throws IOException {
        String[][] dataplus = {};
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String row;
        while ((row = reader.readLine()) != null) {
            String[] data = row.split(",");
            dataplus = (String[][]) add(dataplus, data);
        }
        reader.close();
        return dataplus;
    }

    /**
     * Return a new array with one more object added to the original array
     *
     * @param objects the original object array
     * @param object the object that needs to be added
     */
    private Object[] add(Object[] objects, Object object) {
        Object[] newList = new Object[objects.length + 1];
        System.arraycopy(objects, 0, newList, 0, objects.length);
        newList[objects.length] = object;
        return newList;
    }
}