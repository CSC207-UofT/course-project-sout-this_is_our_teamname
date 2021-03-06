package DataGetting;

import Helpers.Constants;
import Helpers.StringToTime;
import TimeTableObjects.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * A CSVScraper class. It is a DataGetter class that gets the data from a CSV
 * file.
 *
 * Precondition: The file is a properly structured csv file. Please see the
 * sample directory for correctly formatted files.
 */
public class CSVScraper extends CourseGetter {
    // Some constants to sort the file
    private final static int SECTION = 0;
    private final static int TIME = 1;
    private final static int LOCATION = 2;
    private final static int INSTRUCTOR = 3;
    private final static int DELIVERY = 4;
    private final static int WAITLIST = 5;

    /**
     * Constructor of the CSVScraper. Reads and filters the data correctly
     * into the data hashmap.
     *
     * @param courseName the name of the course
     * @param theTerm the term of the course
     * @param theYear the course starts.
     * @exception FileNotFoundException Throws file not found exception if the information for the
     * given course is not found.
     */
    @Override
    public void CalibrateData(String courseName, String theTerm,
                              String theYear) throws FileNotFoundException {
        // Opens the file and gets an arraylist of all the lines of the file.
        String fileName =
                "src\\DataGetting\\SampleDirectory\\" + theTerm + theYear
                        + "\\" + courseName + ".csv";
        ArrayList<String> fileData = readFile(fileName);

        // Gets the admin things from the Header. The header is formatted in
        // the form of:
        // Course Code, Term, Faculty
        String[] adminDetails = fileData.remove(0).split(",");
        String term = adminDetails[1];
        String faculty = adminDetails[2];

        // Remove the header
        fileData.remove(0);
        String[][] splicedFileData = splitData(fileData);

        ArrayList<Course> courseList = new ArrayList<>();
        filterData(courseName, courseList, term, faculty, splicedFileData, theYear);
        for (Course item: courseList){
            placeToData(item.getSectionName(), item);
        }
    }

    /**
     * Filters the data and searches for the required information. Then, adds
     * the data into the data parameter in DataGetter parent class.
     *
     * This is a recursive method.
     *
     * Precondition: The file is structured correctly.
     *
     * @param courseData An arraylist of courses to sort
     * @param theTerm The Term of the course
     * @param theFaculty The Faculty offering the course
     * @param theFileData the Arraylist of all the lines of the file.
     */
    private void filterData(String courseName, ArrayList<Course> courseData,
                            String theTerm, String theFaculty,
                            String[][] theFileData, String year){
        // Base Case 1: Size 1 and this is a header.
        if (theFileData.length == 1 && headingCondition(theFileData[0])){
            // Some Parameters
            HashMap<Object[], String> TimeLocation = new HashMap<>();
            TimeLocation.put(splitDateTime(theFileData[0][TIME]),
                    theFileData[0][LOCATION]);

            courseData.add(new Course(courseName, theFileData[0][SECTION],
                    theFileData[0][INSTRUCTOR], theFaculty,
                    theFileData[0][DELIVERY], TimeLocation,
                    theTerm + " " + year)
            );

        // Base Case 2: Size 1 and this is a not a header.
        } else if (theFileData.length == 1 && subHeaderCondition(theFileData[0])) {
            courseData.get(courseData.size() - 1).addToTimeLocation(splitDateTime(theFileData[0][TIME]),
                    theFileData[0][LOCATION]);

        // Inductive Step: All other list sizes
        } else if (theFileData.length != 1){
            for (String[] item: theFileData){
                filterData(courseName, courseData, theTerm, theFaculty,
                        new String[][]{item}, year);
            }
        }
    }

    //======================== HELPER METHODS =================================
    /**
     * Read the file given by filename.
     *
     * Parts of this code were modified from
     * `https://www.w3schools.com/java/java_files_read.asp`
     *
     * Precondition: Filename is a valid filename
     *
     * @return an arraylist of all the lines of the code at filename
     * @param filename the name of the file
     */
    private ArrayList<String> readFile(String filename) throws FileNotFoundException {
        // The objects used to read a txt file
        ArrayList<String> readData = new ArrayList<>();
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);

        // Parse through the file adding each line to readData
        while (myReader.hasNextLine()) {
            readData.add(myReader.nextLine());
        }

        // Close file
        myReader.close();

        return readData;
    }

    /**
     * Split the splitableString into an arraylist of string arrays of the contents of
     * each line seperated by the comma.
     *
     * @param splittableString the splitableString that is required to be split
     * @return The Arraylist of all the splitableString in a string array
     */
    private String[][] splitData(ArrayList<String> splittableString){
            String[][] spliced = new String[splittableString.size()][];
        for (int i = 0; i < splittableString.size(); i++){
            spliced[i] = splittableString.get(i).split(",");
        }
        return spliced;
    }

    /**
     * Splits the formattedTimeString into the date, start time, end time in
     * that order
     *
     * If the time is TBA, assign the time to be 00:00:00.
     * WE WILL RESOLVE THIS IN PHASE 1.
     *
     * @param formattedTimeString the formattedTimeString of the date, start,
     *                           and end times
     * @return the object array of length 3 of the date, start time, and end
     * time: {Date (string), Start time (LocalTIme), End time (LocalTime)}
     */
    private Object[] splitDateTime(String formattedTimeString){
        // Constants
        int DATE = 0;
        int START_TIME = 1;
        int END_TIME = 3;

        String[] splicedInfo = formattedTimeString.split(" ");

        // If the splicedInfo contains a time.
        if (hasTime(splicedInfo)) {
            return new Object[]{splicedInfo[DATE],
                    StringToTime.makeTime(splicedInfo[START_TIME]),
                    StringToTime.makeTime(splicedInfo[END_TIME])};
        }
        // If the info contains TBA as time, or does not contain date or time
        else {
            return new Object[]{Constants.TBA,
                    LocalTime.of(0, 0, 0),
                    LocalTime.of(0, 0, 0)};
        }
    }

    // ============================== Predicates ===============================
    /**
     * Returns true iff the input has all the required information to be
     * formatted as a time
     *
     * @param input the string that array that needs to be checked
     * @return true iff the input has all the required information to be
     * formatted as a time
     */
    private boolean hasTime(String[] input){
        // If there are 4 items in the array (Date, StartTime, "-"
        // character, EndTime)
        return input.length == 4 && input[2].equals("-");
    }

    /**
     * Returns true iff the line is a header line
     *
     * A header line is any line that begins with the section name and has
     * the first line of information
     *
     * @param line the line that needs to be checked
     * @return true iff the line is a header line
     */
    private boolean headingCondition(String[] line){
        // If there are 5 items or more in that line
        return line.length >= 5 && !line[SECTION].equals("");
    }

    /**
     * Returns true iff the line is a subheader line
     *
     * A line is a subheader line if it only contains the time and Location.
     * It is a continuation of the previous section information
     *
     * @param line the line that needs to be checked
     * @return true iff the line is a subheader line
     */
    private boolean subHeaderCondition(String[] line){
        return line.length >= 2 && line[SECTION].equals("");
    }

    public String toString(){
        return "CSVScraper";
    }

    public static void main(String[] args) {
        CourseGetter scraper = new CSVScraper();
        try {
            LinkedHashMap<String, Course> data = scraper.retrieveData(
                    "CSC207H1", "Fall", "2021");
            for (String course : data.keySet()){
                System.out.print(course + ": ");
                System.out.println(data.get(course));
            }
        } catch (FileNotFoundException e){
            System.out.println(":(");
        }
    }
}
