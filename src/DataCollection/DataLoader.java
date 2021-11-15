package DataCollection;


import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.CourseSection;
import EntitiesAndObjects.TimeTableObjects.Events;
import EntitiesAndObjects.TimeTableObjects.Task;
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
     * Upload a properly formatted reloadable csv file to a timetable
     * manager object
     *
     * @param filepath the path of the csv file that needs to be uploaded
     * @param term the term of the input timetable file
     */
    public void upload(String filepath, String term, TimeTableManager ttbmanager) throws IOException {
        String[][] data = read(filepath);
        if (!Objects.equals(data[0][0], "(Reloadable)")) {
            throw new RuntimeException("File uploaded is not reloadable!");
        }
        else {
            String[][] meaningfuldata = new String[1][data.length - 2];
            for (int i = 0; i + 2 < data.length; i++) {
                meaningfuldata[i] = data[i + 2];
            }
            for (int timeindex = 0; timeindex <= 23; timeindex++) {
                for (int dateindex = 1; dateindex <= 7; dateindex++) {
                    String[] days = {Constants.MONDAY, Constants.TUESDAY, Constants.WEDNESDAY,
                            Constants.THURSDAY, Constants.FRIDAY, Constants.SATURDAY, Constants.SUNDAY};
                    if (!Objects.equals(meaningfuldata[timeindex][dateindex], " ")) {
                        String[] olddescriptionwords = meaningfuldata[timeindex][dateindex].split(" ");
                        String[] newdescriptionwords = new String[olddescriptionwords.length - 1];
                        for (int n = 0; n + 1 < olddescriptionwords.length; n ++) {
                            newdescriptionwords[n] = olddescriptionwords[n + 1];
                        }
                        String newdescription = String.join(" ", newdescriptionwords);
                        if (Objects.equals(olddescriptionwords[0], "Activity:")) {
                            Events activity = new Activity(LocalTime.of(timeindex, 0, 0, 0),
                                    LocalTime.of(timeindex + 1, 0, 0, 0),
                                    days[dateindex - 1], term, newdescription);
                            ttbmanager.getTimetable(term).schedule(activity);
                        }
                        else if (Objects.equals(olddescriptionwords[0], "Course Section:")) {
                            Events activity;
                            if (newdescription.contains("(Waitlisted)")) {
                                activity = new
                                        CourseSection(LocalTime.of(timeindex, 0, 0, 0),
                                        LocalTime.of(timeindex + 1, 0, 0, 0),
                                        days[dateindex - 1], term, newdescription.substring(0,
                                        newdescription.length() - " (Waitlisted)".length()), true);
                            }
                            else {
                                activity = new
                                        CourseSection(LocalTime.of(timeindex, 0, 0, 0),
                                        LocalTime.of(timeindex + 1, 0, 0, 0),
                                        days[dateindex - 1], term, newdescription, false);
                            }
                            ttbmanager.getTimetable(term).schedule(activity);
                        }
                        else {
                            Events activity;
                            if (newdescription.contains("N/A")) {
                                activity = new Task(LocalTime.of(timeindex, 0, 0, 0),
                                        LocalTime.of(timeindex + 1, 0, 0, 0),
                                        days[dateindex - 1], term);
                            }
                            else {
                                activity = new Task(LocalTime.of(timeindex, 0, 0, 0),
                                        LocalTime.of(timeindex + 1, 0, 0, 0),
                                        newdescription.substring("at ".length()), days[dateindex - 1], term);
                            }
                            ttbmanager.getTimetable(term).schedule(activity);
                        }
                    }
                }
            }
        }
    }

    /**
     * Download a timetable manager object to a properly formatted csv file
     * that is not reloadable but is easier to read
     *
     * @param ttbmanager the timetable manager that needs to be downloaded
     * @param filename the name that users want to save the file as
     */
    public void DownloadToUnreloadable(TimeTableManager ttbmanager, String filename) throws IOException {
        for (String term : ttbmanager.getTerms()) {
            TimeTable timetable = ttbmanager.getTimetable(term);
            List<List<String>> datalists = TimetableToListUnreloadable(timetable);
            FileWriter csvWriter = new FileWriter(filename + "_" + term + ".csv");
            csvWriter.append(" " + ",");
            csvWriter.append(Constants.MONDAY + ",");
            csvWriter.append(Constants.TUESDAY + ",");
            csvWriter.append(Constants.WEDNESDAY + ",");
            csvWriter.append(Constants.THURSDAY + ",");
            csvWriter.append(Constants.FRIDAY + ",");
            csvWriter.append(Constants.SATURDAY + ",");
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
     * Download a timetable manager object to a properly formatted csv file
     * that is reloadable but is less easy to read
     *
     * @param ttbmanager the timetable manager that needs to be downloaded
     * @param filename the name that users want to save the file as
     */
    public void DownloadToReloadable(TimeTableManager ttbmanager, String filename) throws IOException {
        for (String term : ttbmanager.getTerms()) {
            TimeTable timetable = ttbmanager.getTimetable(term);
            List<List<String>> datalists = TimetableToListReloadable(timetable);
            FileWriter csvWriter = new FileWriter(filename + "_" + term + ".csv");
            csvWriter.append("(Reloadable)");
            csvWriter.append("\n");
            csvWriter.append(" " + ",");
            csvWriter.append(Constants.MONDAY + ",");
            csvWriter.append(Constants.TUESDAY + ",");
            csvWriter.append(Constants.WEDNESDAY + ",");
            csvWriter.append(Constants.THURSDAY + ",");
            csvWriter.append(Constants.FRIDAY + ",");
            csvWriter.append(Constants.SATURDAY + ",");
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
     * Convert a timetable to a list of lists of strings that contains the
     * data and is not reloadable
     *
     * @param timetable the timetable that needs to be converted
     */
    private List<List<String>> TimetableToListUnreloadable(TimeTable timetable) {
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

    /**
     * Convert a timetable to a list of lists of strings that contains the
     * data and is reloadable
     *
     * @param timetable the timetable that needs to be converted
     */
    private List<List<String>> TimetableToListReloadable(TimeTable timetable) {
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
                    if (timetable.getCalender().get(day)[n] instanceof Activity) {
                        datalists.get(n).add("Activity: " + timetable.getCalender().get(day)[n].getDescription());
                    }
                    else if (timetable.getCalender().get(day)[n] instanceof CourseSection) {
                        if (((CourseSection) timetable.getCalender().get(day)[n]).getWaitlist()) {
                            datalists.get(n).add("Course Section: " +
                                    timetable.getCalender().get(day)[n].getDescription() + " (Waitlisted)");
                        }
                        else {
                            datalists.get(n).add("Course Section: " +
                                    timetable.getCalender().get(day)[n].getDescription());
                        }
                    }
                    else if (timetable.getCalender().get(day)[n] instanceof Task) {
                        if (timetable.getCalender().get(day)[n].getDescription() == null) {
                            datalists.get(n).add("Task: " + "N/A");
                        }
                        else {
                            datalists.get(n).add("Task: " + timetable.getCalender().get(day)[n].getDescription());
                        }
                    }
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
