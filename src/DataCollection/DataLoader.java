package DataCollection;


import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.Events;
import GlobalHelpers.Constants;
import TimeTableStuff.TimeTable;
import TimeTableStuff.TimeTableManager;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DataLoader class. It is a class that can upload properly formatted
 * csv files to timetable manager objects and download timetable manager
 * objects to properly formatted csv files.
 *
 * Precondition: The file needs to be uploaded is a properly formatted
 * csv file.
 */
public class DataLoader {
    /**
     * Upload a properly formatted csv file to a timetable manager object
     *
     * @param filepath the path of the csv file that needs to be uploaded
     * @param term the term of the input timetable file
     */
    public TimeTableManager upload(String filepath, String term) throws IOException {
        TimeTableManager ttbmanager = new TimeTableManager();
        String[][] data = read(filepath);
        String[][] meaningfuldata = new String[1][data.length - 1];
        for (int i = 0; i + 1 < data.length; i++) {
            meaningfuldata[i] = data[i + 1];
        }
        for (int timeindex = 0; timeindex <= 23; timeindex++) {
            for (int dateindex = 1; dateindex <= 7; dateindex++) {
                String[] days = {Constants.MONDAY, Constants.TUESDAY, Constants.WEDNESDAY,
                        Constants.THURSDAY, Constants.FRIDAY, Constants.SATURDAY, Constants.SUNDAY};
                if (!Objects.equals(meaningfuldata[timeindex][dateindex], " ")) {
                    Events activity = new Activity(LocalTime.of(timeindex, 0, 0, 0),
                            LocalTime.of(timeindex + 1, 0, 0, 0),
                            days[dateindex - 1], term, meaningfuldata[timeindex][dateindex]);
                    ttbmanager.getTimetable(term).schedule(activity);
                }
            }
        }
        return ttbmanager;
    }

    /**
     * Upload a properly formatted csv file to a timetable manager object
     * with a timetable manager object created already
     *
     * @param filepath the path of the csv file that needs to be uploaded
     * @param term the term of the input timetable file
     * @param oldttbmanager the timetable manager object that is created already
     */
    public TimeTableManager upload(String filepath, String term,
                                   TimeTableManager oldttbmanager) throws IOException {
        String[][] data = read(filepath);
        String[][] meaningfuldata = new String[1][data.length - 1];
        for (int i = 0; i + 1 < data.length; i++) {
            meaningfuldata[i] = data[i + 1];
        }
        for (int timeindex = 0; timeindex <= 23; timeindex++) {
            for (int dateindex = 1; dateindex <= 7; dateindex++) {
                String[] days = {Constants.MONDAY, Constants.TUESDAY, Constants.WEDNESDAY,
                        Constants.THURSDAY, Constants.FRIDAY, Constants.SATURDAY, Constants.SUNDAY};
                if (!Objects.equals(meaningfuldata[timeindex][dateindex], " ")) {
                    Events activity = new Activity(LocalTime.of(timeindex, 0, 0, 0),
                            LocalTime.of(timeindex + 1, 0, 0, 0),
                            days[dateindex - 1], term, meaningfuldata[timeindex][dateindex]);
                    oldttbmanager.getTimetable(term).schedule(activity);
                }
            }
        }
        return oldttbmanager;
    }

    /**
     * Download a timetable manager object to a properly formatted csv file
     *
     * @param ttbmanager the timetable manager that needs to be downloaded
     * @param filename the name that users want to save the file as
     */
    public void download(TimeTableManager ttbmanager, String filename) throws IOException {
        for (String term : ttbmanager.getTerms()) {
            TimeTable timetable = ttbmanager.getTimetable(term);
            List<List<String>> datalists = TimetableToList(timetable);
            FileWriter csvWriter = new FileWriter(filename + "_" + term + ".csv");
            csvWriter.append(" ");
            csvWriter.append(Constants.MONDAY);
            csvWriter.append(Constants.TUESDAY);
            csvWriter.append(Constants.WEDNESDAY);
            csvWriter.append(Constants.THURSDAY);
            csvWriter.append(Constants.FRIDAY);
            csvWriter.append(Constants.SATURDAY);
            csvWriter.append(Constants.SUNDAY);
            csvWriter.append("\n");
            for (List<String> datalist : datalists) {
                csvWriter.append(String.join(",", datalist));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }
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
        Object[] newlist = new Object[objects.length + 1];
        System.arraycopy(objects, 0, newlist, 0, objects.length);
        newlist[objects.length] = object;
        return newlist;
    }

    /**
     * Convert a timetable to a list of lists of strings that contains the data
     *
     * @param timetable the timetable that needs to be converted
     */
    private List<List<String>> TimetableToList(TimeTable timetable) {
        List<List<String>> datalists = new ArrayList<>(24);
        int i = 0;
        while (i <= 23) {
            String time = i + ":00 ~ " + (i + 1) + ":00";
            List<String> datalist = new ArrayList<>();
            datalist.add(time);
            datalists.add(datalist);
            i ++;
        }
        String[] days = {Constants.MONDAY, Constants.TUESDAY, Constants.WEDNESDAY,
                Constants.THURSDAY, Constants.FRIDAY, Constants.SATURDAY, Constants.SUNDAY};
        for (String day : days) {
            int n = 0;
            while (n <= 23) {
                if (timetable.getCalender().get(day)[n] != null) {
                    datalists.get(n).add(timetable.getCalender().get(day)[n].getDescription());
                }
                else {
                    datalists.get(n).add(" ");
                }
                n ++;
            }
        }
        return datalists;
    }
}
