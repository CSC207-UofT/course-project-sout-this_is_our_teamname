package FunctionsAndCommands.Commands.CreationCommands;

import DataCollection.DataGetter;
import EntitiesAndObjects.Course;
import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.TimeTableManager;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A command to create a Course Object.
 *
 * === Private Attributes ===
 * dataSource: The source where the data is from.
 * manager: The manager that will eventually schedule the object
 */
public class MakeCourseCommand extends Command {
    private final DataGetter dataSource;
    private final TimeTableManager manager;

    /**
     * A constructor to initialize what this command is connected to
     *
     * @param sendTo the Manager to send to
     * @param dataSource the Source of the data of the course
     */
    public MakeCourseCommand(TimeTableManager sendTo, DataGetter dataSource){
        this.manager = sendTo;
        this.dataSource = dataSource;
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

    /**
     * Prompts the user to create a course object
     */
    @Override
    public void execute() {
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
        manager.schedule(course_data.get(selected));
    }
}
