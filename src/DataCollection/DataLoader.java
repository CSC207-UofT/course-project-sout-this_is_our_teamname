package DataCollection;

import EntitiesAndObjects.Course;
import GlobalHelpers.Constants;
import GlobalHelpers.StringToTime;
import TimeTableStuff.TimeTableManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A DataLoader class. It is a CSVScraper class that can upload properly
 * formatted csv files to timetables and download timetables to properly
 * formatted csv files.
 *
 * Precondition: The file needs to be uploaded is a properly formatted
 * csv file. Please see the sample directory for correctly formatted files.
 */
public class DataLoader extends CSVScraper {
    /**
     * Upload a properly formatted csv file to a timetable
     *
     * @param filename the path of the csv file that needs to be uploaded
     */
    public TimeTableManager upload(String filename) throws FileNotFoundException {
        ArrayList<String> file_data = readFile(filename);
        String[] adminDetails = file_data.remove(0).split(",");
        String term = adminDetails[1];
        String faculty = adminDetails[2];
        file_data.remove(0);
        String[][] splicedFileData = splitData(file_data);
        ArrayList<Course> courseList = new ArrayList<>();
        filterData(courseList, term, faculty, splicedFileData);
        TimeTableManager timetables = new TimeTableManager();
        for (Course item : courseList) {
            timetables.schedule(item);
        }
        return timetables;
    }

    /**
     * Download a timetable to a properly formatted csv file
     *
     * @param timetables the timetable manager that needs to be downloaded
     */
    public void download(TimeTableManager timetables) {
        
    }

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
        ArrayList<String> readData = new ArrayList<>();
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            readData.add(myReader.nextLine());
        }
        myReader.close();

        return readData;
    }

    /**
     * Filters the data and searches for the required information. Then, adds
     * the data into the data structure.
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
    private void filterData(ArrayList<Course> courseData, String theTerm,
                            String theFaculty, String[][] theFileData){
        // Base Case 1: Size 1 and this is a header.
        if (theFileData.length == 1 && headingCondition(theFileData[0])){
            // Some Parameters
            HashMap<Object[], String> TimeLocation = new HashMap<>();
            TimeLocation.put(splitDateTime(theFileData[0][1]),
                    theFileData[0][2]);
            boolean hasWaitList = theFileData[0][5].equals("0");

            courseData.add(new Course(theFileData[0][0],
                            theFileData[0][3], theFaculty,
                            theFileData[0][4], TimeLocation, theTerm, hasWaitList
                    )
            );

            // Base Case 2: Size 1 and this is a not a header.
        } else if (theFileData.length == 1 && subHeaderCondition(theFileData[0])) {
            courseData.get(courseData.size() - 1).addToTimeLocation(splitDateTime(theFileData[0][1]),
                    theFileData[0][2]);

            // Inductive Step: All other list sizes
        } else if (theFileData.length != 1){
            for (String[] item: theFileData){
                filterData(courseData, theTerm, theFaculty,
                        new String[][]{item});
            }
        }
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
     * @return the string array of length 3 of the date, start time, and end
     * time
     */
    private Object[] splitDateTime(String formattedTimeString){
        String[] splicedInfo = formattedTimeString.split(" ");

        Object[] retList;
        if (hasTime(splicedInfo)) {
            retList = new Object[]{splicedInfo[0], StringToTime.makeTime(splicedInfo[1]),
                    StringToTime.makeTime(splicedInfo[3])};
        } else {
            retList = new Object[]{Constants.TBA, LocalTime.of(0, 0, 0),
                    LocalTime.of(0, 0, 0)};
        }
        return retList;
    }

    /**
     * Returns true iff the input has all the required information to be
     * formatted as a time
     *
     * @param input the string that array that needs to be checked
     * @return true iff the input has all the required information to be
     * formatted as a time
     */
    private boolean hasTime(String[] input){
        return input.length == 4 && input[2].equals("-");
    }

    /**
     * Returns true iff the line is a header line
     *
     * @param line the line that needs to be checked
     * @return true iff the line is a header line
     */
    private boolean headingCondition(String[] line){
        return line.length >= 5 && !line[0].equals("");
    }

    /**
     * Returns true iff the line is a subheader line
     *
     * @param line the line that needs to be checked
     * @return true iff the line is a subheader line
     */
    private boolean subHeaderCondition(String[] line){
        return line.length >= 2 && line[0].equals("");
    }
}
