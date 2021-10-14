package DataCollection;

import ConstantsAndExceptions.Constants;
import TimeTableObjects.CourseStuff.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A CSVScraper class. It is a DataGetter class that gets the data from a CSV
 * file.
 *
 * Precondition: The file is a properly structured csv file.
 */
public class CSVScraper extends DataGetter {
    // Some constants to sort the file
    private final static int SECTION = 0;
    private final static int TIME = 1;
    private final static int LOCATION = 2;
    private final static int INSTRUCTOR = 3;
    private final static int DELIVERY = 4;

    /**
     * Constructor of the CSVScraper. Reads and filters the data correctly
     * into the data hashmap.
     *
     * @param course_name the name of the course
     */
    @Override
    public void CalibrateData(String course_name){
        // Gets an arraylist of all the lines of the file.
        String fileName = "phase0\\src\\DataCollection\\SampleDirectory\\"
                + course_name + ".csv";
        ArrayList<String> fileData = readFile(fileName);

        // Gets the admin things from the Header. The header is formatted in
        // the form of:
        // Course Code, Term, Faculty
        String[] adminDetails = fileData.remove(0).split(",");
        String term = adminDetails[1];
        String faculty = adminDetails[2];
        fileData.remove(0);
        ArrayList<String[]> splicedFileData = splitData(fileData);

        filterData(term, faculty, splicedFileData);
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
    private ArrayList<String> readFile(String filename){
        ArrayList<String> readData = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                readData.add(myReader.nextLine());
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Course not found. Please Try Again!");
            e.printStackTrace();
        }
        return readData;
    }

    /**
     * Split the data into an arraylist of the an array of each string,
     * seperated by the comma.
     * @param data the data that is required to be split
     * @return The Arraylist of all the data in a string array
     */
    private ArrayList<String[]> splitData(ArrayList<String> data){
        ArrayList<String[]> splitted = new ArrayList<>();
        for (String it : data){
            splitted.add(it.split(","));
        }
        return splitted;
    }

    /**
     * Add the given data to self.data
     * @param term the term of course
     * @param currname the name of the section
     * @param faculty the associated faculty
     * @param currTimeLocation the current time and location. Given as a
     *                         HashMap of the Time -> Location
     * @param currInstructor the instructor of the course section
     * @param currDelivery the delivery method of the course.
     */
    private void addToData(String term,
                           String currname,
                           String faculty,
                           HashMap<String[], String> currTimeLocation,
                           String currInstructor,
                           String currDelivery){
        Course theCourse;
        if (term.contains(Constants.YEAR)){
            theCourse = new Course(term ,currname, currInstructor, faculty,
                    currDelivery, currTimeLocation);
        } else {
            theCourse = new Course(Constants.YEAR, currname, currInstructor,
                    faculty, currDelivery, currTimeLocation);
        }
        placeToData(currname, theCourse);
    }

    /**
     * Splits the line into the date, start time, end time in that order
     * @param line the line of the date, start, and end times
     * @return the string array of length 3 of the date, start time, and end
     * time
     */
    private String[] splitDateTime(String line){
        String[] splicedInfo = line.split(" ");

        if (splicedInfo.length == 4 && splicedInfo[2].equals("-")) {
            return new String[]{splicedInfo[0], splicedInfo[1], splicedInfo[3]};
        } else {
            return new String[]{Constants.TBA, Constants.TBA, Constants.TBA};
        }
    }

    /**
     * Filters the data and searches for the required informaton. Then, adds
     * the data into the data structure.
     *
     * Precondition: The file is structured correctly.
     *
     * @param theTerm The Term of the course
     * @param theFaculty The Faculty offering the course
     * @param theFileData the Arraylist of all the lines of the file.
     */
    private void filterData(String theTerm, String theFaculty,
                                   ArrayList<String[]> theFileData){
        String currName = "";
        HashMap<String[], String> currTimeLocation = new HashMap<>();
        String currInstructor = "";
        String currDelivery = "";
        for (String[] splittedLine : theFileData){
            if (!currName.equals("") && Arrays.equals(splittedLine, new String[]{})){
                addToData(theTerm, currName, theFaculty, currTimeLocation,
                        currInstructor, currDelivery);
            } else {
                if (!splittedLine[SECTION].equals("")) {
                    currName = splittedLine[SECTION];
                    currInstructor = splittedLine[INSTRUCTOR];
                    currDelivery = splittedLine[DELIVERY];
                    currTimeLocation = new HashMap<>();
                }
                currTimeLocation.put(splitDateTime(splittedLine[TIME]),
                        splittedLine[LOCATION]);
            }
        }
    }

    public static void main(String[] args) {
        CSVScraper a = new CSVScraper();
        HashMap<String, Course> got = a.getData("CSC207H1");
        System.out.println(got);
    }
}
