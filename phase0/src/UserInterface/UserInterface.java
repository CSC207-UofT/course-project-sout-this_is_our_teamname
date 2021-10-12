package UserInterface;

import DataCollection.CSVScraper;
import DataCollection.DataGetter;
import TimeTableObjects.CourseStuff.Course;
import TimeTableObjects.Life;
import TimeTableObjects.TimeTableObject;
import TimeTableStuff.TimeTable;
import TimeTableStuff.TimeTableManager;

import javax.lang.model.type.UnionType;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;

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
     *
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
                System.out.println("Enter Course: ");
                String course = scanner.nextLine();

                // Gets the data from the datasource
                HashMap<String, Course> course_data = dataSource.getData(course);
                promptUserToChoose(course_data);

                // The user enters the section they want to search
                Scanner userChoice = new Scanner(System.in);
                System.out.println("Please choose one of the sections: ");
                String selected = userChoice.nextLine();

                Course selectedCourse = course_data.get(selected);
                manager.schedule(selectedCourse);
            } else {
                // User enters the time they want to search
                Scanner lifeTimeScanner = new Scanner(System.in);
                System.out.println("Please select the time of your life " +
                        "object (start-end; no spaces!): ");
                String lifeTime = lifeTimeScanner.nextLine();

                String[] lifeSpliced = lifeTime.split("-");

                // User enters the location
                Scanner lifeLocationScanner = new Scanner(System.in);
                System.out.println("Please enter the location of the life " +
                        "object");
                String lifeLocation = lifeLocationScanner.nextLine();

                // User enters the time they want
                Scanner lifeDescriptionScanner = new Scanner(System.in);
                System.out.println("Please write a brief description of your " +
                        "life object: ");
                String lifeDescription = lifeDescriptionScanner.nextLine();

                Life selectedObject = new Life(lifeSpliced[0], lifeSpliced[1]
                        , lifeLocation, lifeDescription);
                // You can totally change the name of this method if you
                // want!. Make sure, however, it is overloaded.
                manager.schedule(selectedObject);
            }

            // User types in the section they want to search
            Scanner continueQuestion = new Scanner(System.in);
            System.out.println("Do you want to add another course? " +
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
