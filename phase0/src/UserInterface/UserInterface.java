package UserInterface;

import DataCollection.CSVScraper;
import DataCollection.DataGetter;
import TimeTableObjects.CourseStuff.Course;
import TimeTableObjects.Life;
import TimeTableObjects.TimeTableObject;
import TimeTableStuff.TimeTable;
import TimeTableStuff.TimeTableManager;

import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    private static DataGetter dataSource;

    public UserInterface(){
        dataSource = new CSVScraper();
    }

    /**
     * Prints a hashmap in a way that allows the user to choose what to
     * section they want.
     *
     * @param courses the hashmap of courses
     */
    private static void promptUserToChoose(HashMap<String, Course> courses){
        // TODO change to courses.values if needed!
        for (String course : courses.keySet()){
            System.out.println(course.toString());
        }
    }

    /**
     * Runs the UserInterface.
     *
     * @return An array of timetables.
     */
    public TimeTable[] run(){
        TimeTableManager manager = new TimeTableManager();

        // As long as the program is running
        boolean running = true;

        while(running) {
            Scanner objectScanner = new Scanner(System.in);
            System.out.println("Please enter what type of object " +
                    "(course/life): ");
            String schedulingObject = objectScanner.nextLine();

            if (schedulingObject.equals("course")){
                // User enters the course they want to search
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Course Name: ");
                String course = scanner.nextLine();

                // Gets the data from the datasource
                HashMap<String, Course> course_data = dataSource.getData(course);
                promptUserToChoose(course_data);

                // The user enters the section they want to search
                Scanner userChoice = new Scanner(System.in);
                System.out.println();
                String selected = userChoice.nextLine();

                Course selectedCourse = course_data.get(selected);
                manager.schedule(selectedCourse);
            } else {
                // User enters the time they want to search
                Scanner lifeTimeScanner = new Scanner(System.in);
                System.out.println();
                String lifeTime = lifeTimeScanner.nextLine();

                String[] lifeSpliced = lifeTime.split("-");

                // User enters the location
                Scanner lifeLocationScanner = new Scanner(System.in);
                System.out.println();
                String lifeLocation = lifeLocationScanner.nextLine();

                // User enters the time they want
                Scanner lifeDescriptionScanner = new Scanner(System.in);
                System.out.println();
                String lifeDescription = lifeDescriptionScanner.nextLine();

                TimeTableObject selectedObject = new Life(lifeSpliced[0],
                        lifeSpliced[1], lifeLocation, lifeDescription);

                manager.schedule(selectedObject);
            }

            // User types in the section they want to search
            Scanner continueQuestion = new Scanner(System.in);
            System.out.println("Do you want to add another object? " +
                    "(true/false):");
            String continueResponse = continueQuestion.nextLine();

            // Checks if the user wants to add any more courses.
            if (continueResponse.equals("false")){
                running = false;
            }
        }
        return manager.getAllTimeTables();
    }

    /**
     * A UserInterface. The main method of the program and the one that the
     * user interacts with.
     *
     * @param args The arguments
     */
    public static void main(String[] args) {
        UserInterface user = new UserInterface();

        TimeTable[] output =  user.run();

        for (TimeTable table : output) {
            // Prints out the timetable
            System.out.println(table.toString());
        }
        System.out.println("Here are your TimeTable!");
    }
}
