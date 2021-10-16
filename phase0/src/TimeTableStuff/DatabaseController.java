package TimeTableStuff;
import DataCollection.CSVScraper;
import DataCollection.DataGetter;
import GlobalHelperMethods.StringToTime;
import TimeTableObjects.CourseStuff.Course;
import TimeTableObjects.CourseStuff.NonCourseObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class DatabaseController {
    private final DataGetter dataSource;
    private final TimeTableManager manager;

    // Some Constants:
    final String NAME = "Name";
    final String START_TIME = "Start Time";
    final String END_TIME = "End Time";
    final String LOCATION = "Location";
    final String DATE = "Date";
    final String TERM = "Term";
    final String TYPE = "Type";

    public DatabaseController(){
        // In phase 1, this will be removed and integrated with
        // operatorInterface.
        this.dataSource = new CSVScraper();
        this.manager = new TimeTableManager();
    }

    /**
     * Prints a hashmap in a way that allows the user to choose what to
     * section they want.
     *
     * @param courses the hashmap of courses
     */
    private static void promptUserToChoose(HashMap<String, Course> courses) {
        for (String course : courses.keySet()) {
            System.out.println(course + ": " + courses.get(course).toString());
        }
    }


    public void makeCourse(){
        // The user enters the section they want to search
        Scanner CourseNameScanner = new Scanner(System.in);
        System.out.println("Please Enter the course Name (eg CSC207H1 or " +
                "APS113Y1. Don't forget the 'H1'!!!): ");
        String course = CourseNameScanner.nextLine();

        // Gets the data from the datasource
        HashMap<String, Course> course_data = dataSource.getData(course);
        promptUserToChoose(course_data);

        // The user enters the section they want to search
        Scanner userChoice = new Scanner(System.in);
        System.out.println("Please choose a section (eg; LEC 0101. Only enter" +
                " the section code): ");
        String selected = userChoice.nextLine();

        // Pass this to the TimeTableManager
        Course selectedCourse = course_data.get(selected);
        manager.schedule(selectedCourse);
    }

    public void makeTimeTableObject(String theType){
        LinkedHashMap<String, String> prompts = new LinkedHashMap<>();
        prompts.put(NAME, "Enter a name for an object (eg; Dinner with Prof " +
                "Gries and Friends)");
        prompts.put(START_TIME, "Enter the Start Time (in a 12h clock format." +
                " eg: 10:00AM or 9:00PM. No space between time and AM/PM)");
        prompts.put(END_TIME, "Enter the End Time (in a 12h clock format. (" +
                "eg: 10:00AM or 9:00PM. No space between time and AM/PM)");
        prompts.put(LOCATION, "Enter the Location (eg; MY150, Home, Middle of" +
                " Nowhere)");
        prompts.put(DATE, "Enter the Day of the week (eg; Monday, Tuesday, " +
                "Wednesday, etc.)");
        prompts.put(TERM, "Enter the Term (Fall/Winter - NB will add more in " +
                "later Phases)");

        HashMap<String, String> responses = new HashMap<>();
        responses.put(TYPE, theType);

        for (String prompt : prompts.keySet()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(prompts.get(prompt) + ": ");
            responses.put(prompt, scanner.nextLine());
        }

        NonCourseObject selectedObject =
                new NonCourseObject(
                        StringToTime.makeTime(responses.get(START_TIME)),
                        StringToTime.makeTime(responses.get(END_TIME)),
                        responses.get(LOCATION),
                        responses.get(DATE),
                        responses.get(TERM),
                        responses.get(TYPE));

        manager.schedule(selectedObject);
    }

    public void getAllTimeTables(){
        TimeTable[] output = manager.getAllTimeTables();
        for (TimeTable table : output) {
            // Prints out the timetable
            System.out.println(table.toString());
        }
    }
}
