package DataCollection;

import GlobalHelpers.Constants;
import GlobalHelpers.StringToTime;
import EntitiesAndObjects.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
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
     * @param courseName the name of the course
     */
    @Override
    public void CalibrateData(String courseName){
        // Opens the file and gets an arraylist of all the lines of the file.
        String fileName = "src\\DataCollection\\SampleDirectory\\"
                + courseName + ".csv";
        ArrayList<String> fileData = readFile(fileName);

        // Gets the admin things from the Header. The header is formatted in
        // the form of:
        // Course Code, Term, Faculty
        String[] adminDetails = fileData.remove(0).split(",");
        String term = adminDetails[1];
        String faculty = adminDetails[2];

        // Remove the header
        fileData.remove(0);
        ArrayList<String[]> splicedFileData = splitData(fileData);

        filterData(term, faculty, splicedFileData);
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
     * Split the splitableString into an arraylist of string arrays of the contents of
     * each line seperated by the comma.
     *
     * @param splittableString the splitableString that is required to be split
     * @return The Arraylist of all the splitableString in a string array
     */
    private ArrayList<String[]> splitData(ArrayList<String> splittableString){
        ArrayList<String[]> splitted = new ArrayList<>();
        for (String it : splittableString){
            splitted.add(it.split(","));
        }
        return splitted;
    }

    /**
     * Add the given data to self.data
     * @param term the term of course
     * @param sectionName the name of the section
     * @param faculty the associated faculty
     * @param timeToLocationMap the current time and location. Given as a
     *                         HashMap of the Time -> Location
     * @param theInstructor the instructor of the course section
     * @param theDeliveryMethod the delivery method of the course.
     */
    private void addTermedCourseToData(String term,
                                       String sectionName,
                                       String faculty,
                                       HashMap<ArrayList<Object>,
                                               String> timeToLocationMap,
                                       String theInstructor,
                                       String theDeliveryMethod){
        Course theCourse = new Course( "Instructor: " + theInstructor + ", Faculty: "
                + faculty + ", Delivery Method: " + theDeliveryMethod, term, sectionName, timeToLocationMap);
        placeToData(sectionName, theCourse);
    }

    /**
     * Add the given data to super.data.
     *
     * @param sectionName the name of the section
     * @param faculty the associated faculty
     * @param timeToLocationMap the current time and location. Given as a
     *                         HashMap of the Time -> Location
     * @param theInstructor the instructor of the course section
     * @param theDeliveryMethod the delivery method of the course.
     */
    private void addYearCourseToData(String sectionName,
                                     String faculty,
                                     HashMap<ArrayList<Object>, String> timeToLocationMap,
                                     String theInstructor,
                                     String theDeliveryMethod){
        Course theCourse = new Course("Instructor: " + theInstructor +
                ", Faculty: " + faculty + "Delivery Method: " + theDeliveryMethod,
                Constants.YEAR, sectionName, timeToLocationMap);
        placeToData(sectionName, theCourse);
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
    private ArrayList<Object> splitDateTime(String formattedTimeString){
        String[] splicedInfo = formattedTimeString.split(" ");

        ArrayList<Object> retList = new ArrayList<>();
        if (splicedInfo.length == 4 && splicedInfo[2].equals("-")) {
            retList.add(splicedInfo[0]);
            retList.add(StringToTime.makeTime(splicedInfo[1]));
            retList.add(StringToTime.makeTime(splicedInfo[3]));
        } else {
            retList.add(Constants.TBA);
            retList.add(LocalTime.of(0,0,0));
            retList.add(LocalTime.of(0,0, 0));
        }
        return retList;
    }

    /**
     * Filters the data and searches for the required information. Then, adds
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
        // Temporary Constants to hold the information
        String currName = "";
        HashMap<ArrayList<Object>, String> currTimeLocation = new HashMap<>();
        String currInstructor = "";
        String currDelivery = "";

        // for line in FileData
        for (String[] splicedLine : theFileData){

            // If the current name is "", and splicedLine = [], then this is
            // a new line, thus we have reached the end of a section
            // information, so add the inforamtion.
            if (!currName.equals("") && Arrays.equals(splicedLine, new String[]{})){

                // Add the course to the data
                if (theTerm.equals(Constants.YEAR)){
                addYearCourseToData(currName, theFaculty, currTimeLocation,
                        currInstructor, currDelivery);
                } else {
                    addTermedCourseToData(theTerm, currName, theFaculty,
                            currTimeLocation, currInstructor, currDelivery);
                }

                // Else, that mean that we are still in the section
                // information.
            } else {
                // If there is a section, that means we are in the next section
                // Hence, recalibrate everything.
                if (!splicedLine[SECTION].equals("")) {
                    currName = splicedLine[SECTION];
                    currInstructor = splicedLine[INSTRUCTOR];
                    currDelivery = splicedLine[DELIVERY];
                    currTimeLocation = new HashMap<>();
                }

                // Add the time and location of the line.
                currTimeLocation.put(splitDateTime(splicedLine[TIME]),
                        splicedLine[LOCATION]);
            }
        }
    }

    /**
     * A main method to develop this module
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        CSVScraper a = new CSVScraper();
        HashMap<String, Course> got = a.getData("CSC207H1");
        System.out.println(got);
    }
}
