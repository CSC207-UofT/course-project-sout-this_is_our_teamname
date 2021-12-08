package DataGetting;

import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Task;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * A class that uploads a properly formatted reloadable
 * csv file to a timetable manager object
 */
public class CSVUploader extends DataGetter<Object> {

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
        ArrayList<String[]> data;
        try {
            data = ReadThroughFile(filepath);
        } catch (IOException e){
            throw new FileNotFoundException();
        }
        data.remove(0);
        for (String[] line : data) {
            if (!line[1].equals("Tasks")) {
                CalendarObjectHelper(line, term, year);
            }
            else {
                TaskCalendarObjectHelper(line, term);
            }
        }
    }

    /**
     * A method to help get the data for from the loader
     * @param courseName the name of the Course
     * @param term the term of the course
     * @param year the year of the course
     * @return an arraylist of things for the loader
     * @throws FileNotFoundException if the file is not found
     */
    @Override
    public LinkedHashMap<String, Object> retrieveData(String courseName,
                                                      String term, String year) throws FileNotFoundException {
        CalibrateData(courseName, term, year);
        return super.getData();
    }

    /**
     * Read and return the data contained in a csv file at specific location as
     * an array of arrays of strings
     *
     * @param filepath the path of the csv file that needs to be read
     */
    private ArrayList<String[]> ReadThroughFile(String filepath) throws IOException {
        ArrayList<String[]> AllDataLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String fileline;
        while ((fileline = reader.readLine()) != null) {
            String[] OneDataLine = fileline.split(",");
            AllDataLines.add(OneDataLine);
        }
        reader.close();
        return AllDataLines;
    }

    /**
     * A helper method for processing lines of calendar objects.
     *
     * @param line the line of data
     * @param theTerm the term of the object
     * @param theYear the year of the object
     */
    private void CalendarObjectHelper(String[] line, String theTerm, String theYear) {
        if (line[2].equals("Activity")) {
            AddActivityToData(line[1], line[0], theTerm, line[5], line[3]);
        } else if (line[2].equals("CourseSection")){
            AddCourseSectionToData(line[3], line[1], line[0], theTerm, theYear, line[4], line[5]);
        }
    }

    /**
     * A helper method for processing lines of task calendar objects.
     *
     * @param line the line of data
     * @param theTerm the term of the object
     */
    private void TaskCalendarObjectHelper(String[] line, String theTerm) {
        String[] FirstTwoRemoved = RemoveFirstTwo(line);
        for (String taskString : FirstTwoRemoved) {
            AddTaskToData(line[0], theTerm, taskString);
        }
    }

    /**
     * Process an array of strings to one which contains only meaningful data
     *
     * @param data the array needs to be processed
     */
    private String[] RemoveFirstTwo(String[] data) {
        String[] FirstTwoRemoved = new String[data.length - 2];
        for (int i = 0; i + 2 < data.length; i++) {
            FirstTwoRemoved[i] = data[i + 2];
        }
        return FirstTwoRemoved;
    }


    /**
     * Create and add an Activity to data
     *
     * @param TimeString the string of the period of the time of the activity
     * @param theDate the date of the activity
     * @param theTerm the term of the activity
     * @param theDescription the description of the activity
     * @param theName the name of the activity
     */
    private void AddActivityToData(String TimeString, String theDate, String theTerm,
                               String theDescription, String theName) {
        String[] times = TimeString.split("-");
        Activity event =
                new Activity(
                        LocalTime.of(Integer.parseInt(times[0].substring(0, 2)), 0, 0),
                        LocalTime.of(Integer.parseInt(times[1].substring(0, 2)), 0, 0),
                        theDate,
                        theTerm,
                        theDescription);
        event.setName(theName);
        placeToData(theDate + " " + TimeString, event);
    }

    /**
     * Create and add a CourseSection to data
     *
     * @param CourseName the course name of the course section
     * @param TimeString the string of the period of the time of the course section
     * @param theDate the date of the course section
     * @param theTerm the term of the course section
     * @param theYear the year of the course section
     * @param SectionCode the section code of the course section
     * @param theDescription the description of the activity
     */
    private void AddCourseSectionToData(String CourseName, String TimeString, String theDate, String theTerm,
                                       String theYear, String SectionCode, String theDescription) {
        String[] times = TimeString.split("-");
        CourseSection event =
                new CourseSection(
                        CourseName,
                        LocalTime.of(Integer.parseInt(times[0].substring(0, 2)), 0, 0),
                        LocalTime.of(Integer.parseInt(times[1].substring(0, 2)), 0, 0),
                        theDate,
                        theTerm + " " + theYear,
                        SectionCode);
        event.setName(CourseName);
        event.setDescription(theDescription);
        placeToData(theDate + " " + TimeString, event);
    }

    /**
     * Create and add a Task to data
     *
     * @param theDate the date of the course section
     * @param theTerm the term of the course section
     * @param theName the name of the activity
     */
    private void AddTaskToData(String theDate, String theTerm, String theName) {
        Task task = new Task(
                LocalTime.of(0, 0, 0),
                LocalTime.of(23, 59, 59),
                theDate,
                theTerm);
        task.setName(theName);
        placeToData(theName, task);
    }
}
