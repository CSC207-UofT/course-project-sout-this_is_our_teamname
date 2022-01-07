package DataGetting;

import TimeTableObjects.EventBuilder;
import TimeTableObjects.EventObjects.Task;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * A class that uploads a properly formatted reloadable
 * csv file to a timetable manager object
 */
public class CSVUploader extends DataGetter<Object> {
    private final EventBuilder builder;

    public CSVUploader(){
        this.builder = new EventBuilder();
    }

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
        // Get the data from the filepath
        String filepath = "src\\OutputFiles\\" + filename + "_" + term + " " + year + ".csv";
        ArrayList<String[]> data;
        try {
            data = readThroughFile(filepath);
        } catch (IOException e){
            throw new FileNotFoundException();
        }

        // Remove header
        data.remove(0);

        // Place the line into the correct object
        for (String[] line : data) {
            if (line[1].equals("Tasks")) {
                taskCalendarObjectHelper(line, term, year);
            } else {
                calendarObjectHelper(line, term, year);
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

    // ============================= Adding to Data ============================
    /**
     * A helper method for processing lines of calendar objects.
     *
     * @param line the line of data
     * @param theTerm the term of the object
     */
    private void calendarObjectHelper(String[] line, String theTerm,
                                      String theYear) {
        String theDate = line[0];
        String TimeString = line[1];
        String theName = line[3];
        String sectionCode = line[4];
        String theDescription = line[5];

        String[] times = TimeString.split("-");
        int startHour = Integer.parseInt(times[0].substring(0, 2));
        int endHour = Integer.parseInt(times[1].substring(0, 2));
        LocalTime startTime = LocalTime.of(startHour, 0, 0);
        LocalTime endTime = LocalTime.of(endHour, 0, 0);

        this.builder.calibrate(theName, theDescription, startTime, endTime,
                theDate, theTerm + " " + theYear);
        if (line[2].equals("Activity")) {
            this.placeToData(theDate + " " + TimeString,
                    this.builder.getActivity());
        } else if (line[2].equals("CourseSection")){
            this.placeToData(theDate + " " + TimeString,
                    this.builder.getCourseSection(sectionCode));
        }
    }

    /**
     * A helper method for processing lines of task calendar objects.
     *
     * @param line the line of data
     * @param theTerm the term of the object
     */
    private void taskCalendarObjectHelper(String[] line, String theTerm,
                                          String theYear) {
        ArrayList<String> lines = (ArrayList<String>) Arrays.asList(line);
        lines.remove(0);
        lines.remove(0);

        for (String taskString : lines) {
            String theDate = line[0];

            Task task = new Task(taskString, theDate, theTerm + " " + theYear);
            placeToData(taskString, task);
        }
    }

    // ================================ Helpers ================================
    /**
     * Read and return the data contained in a csv file at specific location as
     * an array of arrays of strings
     *
     * @param filepath the path of the csv file that needs to be read
     */
    private ArrayList<String[]> readThroughFile(String filepath) throws IOException {
        // Get the data
        ArrayList<String[]> allDataLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));

        // Read the data
        String fileline = reader.readLine();
        while (fileline != null) {
            String[] oneDataLine = fileline.split(",");
            allDataLines.add(oneDataLine);
            fileline = reader.readLine();
        }
        reader.close();

        return allDataLines;
    }
}
