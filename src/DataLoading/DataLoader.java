package DataLoading;


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
 * A DataLoader class. It is a class that can upload properly formatted
 * csv files to timetable manager objects and download timetable manager
 * objects to properly formatted csv files.
 *
 * Precondition: The file needs to be uploaded is a properly formatted
 * csv file.
 */
public class DataLoader {

    private static String[] days = {Constants.MONDAY, Constants.TUESDAY, Constants.WEDNESDAY,
            Constants.THURSDAY, Constants.FRIDAY, Constants.SATURDAY, Constants.SUNDAY};

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
            String[][] meaningfuldata = MeaningfulDataHelper(data);
            for (int timeindex = 0; timeindex <= 23; timeindex++) {
                for (int dateindex = 1; dateindex <= 7; dateindex++) {
                    String[] days = {Constants.MONDAY, Constants.TUESDAY, Constants.WEDNESDAY,
                            Constants.THURSDAY, Constants.FRIDAY, Constants.SATURDAY, Constants.SUNDAY};
                    if (!Objects.equals(meaningfuldata[timeindex][dateindex], " ")) {
                        String[] olddescriptionwords = meaningfuldata[timeindex][dateindex].split(" ");
                        String newdescription = DescriptionHelper(olddescriptionwords);
                        if (Objects.equals(olddescriptionwords[0], "Activity:")) {
                            ActivityHelper(ttbmanager, LocalTime.of(timeindex, 0, 0, 0),
                                    LocalTime.of(timeindex + 1, 0, 0, 0),
                                    days[dateindex - 1], term, newdescription);
                        }
                        else if (Objects.equals(olddescriptionwords[0], "Course Section:")) {
                            CourseSectionHelper(ttbmanager,
                                    LocalTime.of(timeindex, 0, 0, 0),
                                    LocalTime.of(timeindex + 1, 0, 0, 0),
                                    days[dateindex - 1], term, newdescription);
                        }
                        else {
                            TaskHelper(ttbmanager, LocalTime.of(timeindex, 0, 0, 0),
                                    LocalTime.of(timeindex + 1, 0, 0, 0),
                                    days[dateindex - 1], term, newdescription);
                        }
                    }
                }
            }
        }
    }

    /**
     * Process an array of arrays of strings to one which
     * contains only meaningful data
     *
     * @param data the array needs to be processed
     */
    private String[][] MeaningfulDataHelper(String[][] data) {
        String[][] meaningfuldata = new String[1][data.length - 2];
        for (int i = 0; i + 2 < data.length; i++) {
            meaningfuldata[i] = data[i + 2];
        }
        return meaningfuldata;
    }

    /**
     * Process an array of strings to one single string which
     * contains only important description
     *
     * @param olddescriptionwords the array needs to be processed
     */
    private String DescriptionHelper(String[] olddescriptionwords) {
        String[] newdescriptionwords = new String[olddescriptionwords.length - 1];
        for (int n = 0; n + 1 < olddescriptionwords.length; n ++) {
            newdescriptionwords[n] = olddescriptionwords[n + 1];
        }
        return String.join(" ", newdescriptionwords);
    }

    /**
     * Generate and schedule an activity object to a specific timetable manager
     *
     * @param ttbmanager the timetable manager that needs to contain the object
     * @param time1 the start time of the activity
     * @param time2 the end time of the activity
     * @param day the date of the activity
     * @param term the term of the activity
     * @param newdescription the information of the activity
     */
    private void ActivityHelper(TimeTableManager ttbmanager,
                                LocalTime time1, LocalTime time2,
                                String day, String term, String newdescription) {
        Activity activity = new Activity(time1, time2, day, term, newdescription);
        ttbmanager.getTimetable(term).schedule(activity);
    }

    /**
     * Generate and schedule a course section object to a specific timetable manager
     *
     * @param ttbmanager the timetable manager that needs to contain the object
     * @param time1 the start time of the course section
     * @param time2 the end time of the course section
     * @param day the date of the course section
     * @param term the term of the course section
     * @param newdescription the information of the course section
     */
    private void CourseSectionHelper(TimeTableManager ttbmanager,
                                     LocalTime time1, LocalTime time2,
                                     String day, String term, String newdescription) {
        CourseSection activity;
        if (newdescription.contains("(Waitlisted)")) {
            activity = new CourseSection(time1, time2, day, term,
                    newdescription.substring(0, newdescription.length() - " (Waitlisted)".length()), true);
        }
        else {
            activity = new CourseSection(time1, time2, day, term, newdescription, false);
        }
        ttbmanager.getTimetable(term).schedule(activity);
    }

    /**
     * Generate and schedule a task object to a specific timetable manager
     *
     * @param ttbmanager the timetable manager that needs to contain the object
     * @param time1 the start time of the task
     * @param time2 the end time of the task
     * @param day the date of the task
     * @param term the term of the task
     * @param newdescription the information of the task
     */
    private void TaskHelper(TimeTableManager ttbmanager,
                            LocalTime time1, LocalTime time2,
                            String day, String term, String newdescription) {
        Task activity;
        if (newdescription.contains("N/A")) {
            activity = new Task(time1, time2, day, term);
        }
        else {
            activity = new Task(time1, time2, newdescription.substring("at ".length()), day, term);
        }
        ttbmanager.getTimetable(term).schedule(activity);
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
            // Get the data
            TimeTable timetable = ttbmanager.getTimetable(term);
            List<List<String>> datalists = TimetableToListUnreloadable(timetable);

            // Open the file to read
            FileWriter csvWriter =
                    new FileWriter("src\\OutputFiles\\" + filename + "_" + term + ".csv");

            StringBuilder heading = new StringBuilder(",");
            for (String day : days){
                heading.append(day).append(",");
            }

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

            FileWriter csvWriter = new FileWriter("src\\OutputFiles\\" + filename + "_" + term + ".csv");

            StringBuilder heading = new StringBuilder(",");
            for (String day : days){
                heading.append(day).append(",");
            }

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

    /**
     * Convert a timetable to a list of lists of strings that contains the
     * data and is not reloadable
     *
     * @param timetable the timetable that needs to be converted
     */
    private List<List<String>> TimetableToListUnreloadable(TimeTable timetable) {
        List<List<String>> allDataLines = setUpDates();

        for (String day : days) {
            for (int n = 0; n <= 23; n ++) {
                // If the calendar at the date has an item
                if (timetable.getCalender().get(day)[n] != null) {
                    String desription =
                            timetable.getCalender().get(day)[n].getDescription();
                    allDataLines.get(n).add(desription);
                } else {
                    allDataLines.get(n).add(" ");
                }
            }
        }
        return allDataLines;
    }

    /**
     * Convert a timetable to a list of lists of strings that contains the
     * data and is reloadable
     *
     * @param timetable the timetable that needs to be converted
     */
    private List<List<String>> TimetableToListReloadable(TimeTable timetable) {
        List<List<String>> datalists = setUpDates();

        for (String day : days) {
            for (int n = 0; n <= 23; n ++) {
                if (timetable.getCalender().get(day)[n] != null) {
                    sortEventType(timetable, datalists, day, n);
                }
                else {
                    datalists.get(n).add(" ");
                }
            }
        }
        return datalists;
    }

    private void sortEventType(TimeTable timetable, List<List<String>> datalists, String day, int n) {
        if (timetable.getCalender().get(day)[n] instanceof Activity) {
            String toWrite =
                    "Activity: " + timetable.getCalender().get(day)[n].getDescription();
            datalists.get(n).add(toWrite);
        } else if (timetable.getCalender().get(day)[n] instanceof CourseSection) {
            sortCourseSection(timetable, datalists, day, n);
        } else if (timetable.getCalender().get(day)[n] instanceof Task) {
            sortTask(timetable, datalists, day, n);
        }
    }

    private void sortTask(TimeTable timetable, List<List<String>> datalists, String day, int n) {
        if (timetable.getCalender().get(day)[n].getDescription() == null) {
            datalists.get(n).add("Task: " + "N/A");
        } else {
            datalists.get(n).add("Task: " + timetable.getCalender().get(day)[n].getDescription());
        }
    }

    private void sortCourseSection(TimeTable timetable, List<List<String>> datalists, String day, int n) {
        if (((CourseSection) timetable.getCalender().get(day)[n]).getWaitlist()) {
            datalists.get(n).add("Course Section: " +
                    timetable.getCalender().get(day)[n].getDescription() + " (Waitlisted)");
        } else {
            datalists.get(n).add("Course Section: " +
                    timetable.getCalender().get(day)[n].getDescription());
        }
    }

    private List<List<String>> setUpDates() {
        List<List<String>> datalists = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            String time = i + ":00 ~ " + (i + 1) + ":00";
            List<String> datalist = new ArrayList<>();
            datalist.add(time);
            datalists.add(datalist);
        }
        return datalists;
    }
}
