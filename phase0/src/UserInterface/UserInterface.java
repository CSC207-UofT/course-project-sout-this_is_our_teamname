package UserInterface;

import ConstantsAndExceptions.Constants;
import DataCollection.CSVScraper;
import DataCollection.DataGetter;
import TimeTableObjects.Parents.InputData;
import TimeTableObjects.Life;
import TimeTableObjects.Parents.SearchingData;
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
     * Runs the UserInterface.
     *
     * @return An array of timetables.
     */
//    public TimeTable[] run(){
//        TimeTableManager manager = new TimeTableManager();
//
//        // As long as the program is running
//        boolean running = true;
//
//        while(running) {
//            Scanner objectScanner = new Scanner(System.in);
//            System.out.println("Please enter what type of object " +
//                    "(course/life): ");
//            String schedulingObject = objectScanner.nextLine();
//
//            if (schedulingObject.equals("course")){
//                // User enters the course they want to search
//                Scanner scanner = new Scanner(System.in);
//                System.out.println("Enter Course Name: ");
//                String course = scanner.nextLine();
//
//                // Gets the data from the datasource
//                HashMap<String, Course> course_data = dataSource.getData(course);
//                promptUserToChoose(course_data);
//
//                // The user enters the section they want to search
//                Scanner userChoice = new Scanner(System.in);
//                System.out.println("Please choose a section");
//                String selected = userChoice.nextLine();
//
//                Course selectedCourse = course_data.get(selected);
//
//                manager.schedule(selectedCourse);
//            } else {
//                // User enters the time they want to search
//                Scanner lifeTimeScanner = new Scanner(System.in);
//                System.out.println();
//                String lifeTime = lifeTimeScanner.nextLine();
//
//                String[] lifeSpliced = lifeTime.split("-");
//
//                // User enters the location
//                Scanner lifeLocationScanner = new Scanner(System.in);
//                System.out.println();
//                String lifeLocation = lifeLocationScanner.nextLine();
//
//                // User enters the time they want
//                Scanner lifeDescriptionScanner = new Scanner(System.in);
//                System.out.println();
//                String lifeDescription = lifeDescriptionScanner.nextLine();
//
//                TimeTableObject selectedObject = new Life(lifeSpliced[0],
//                        lifeSpliced[1], lifeLocation, lifeDescription);
//
//                manager.schedule(selectedObject);
//            }
//
//            // User types in the section they want to search
//            Scanner continueQuestion = new Scanner(System.in);
//            System.out.println("Do you want to add another object? " +
//                    "(true/false):");
//            String continueResponse = continueQuestion.nextLine();
//
//            // Checks if the user wants to add any more courses.
//            if (continueResponse.equals("false")){
//                running = false;
//            }
//        }
//        return manager.getAllTimeTables();
//    }
    public TimeTable[] run(){
        TimeTableManager manager = new TimeTableManager();

        // As long as the program is running
        boolean running = true;

        while(running) {
            Scanner objectScanner = new Scanner(System.in);
            System.out.println("Please enter what type of object " +
                    "(life/course): ");
            String schedulingObject = objectScanner.nextLine();

            if (schedulingObject.equals("course")){
                SearchingData promptMaker = new SearchingData();
                SearchingData selectedObject = promptMaker.promptUser(dataSource);
                manager.schedule(selectedObject);
            } else {
                InputData promptMaker = new InputData();
                HashMap<String, String> data = promptMaker.getPrompt();
                InputData selectedObject = null; // TODO IMPLEMENT LIFE / InputData Object
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
