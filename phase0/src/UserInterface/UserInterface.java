package UserInterface;

import TimeTableStuff.DatabaseController;
import TimeTableStuff.TimeTable;

import java.util.Scanner;

public class UserInterface {
    /**
     * Runs the UserInterface.
     *
     * @return An array of timetables.
     */
    public TimeTable[] run(){
        DatabaseController control = new DatabaseController();

        // As long as the program is running
        boolean running = true;

        while(running) {
            Scanner objectScanner = new Scanner(System.in);
            System.out.println("Please enter what type of object " +
                    "(course/life): ");
            String schedulingObject = objectScanner.nextLine();

            if (schedulingObject.equals("course")) {
                control.makeCourse();
            } else {
                control.makeTimeTableObject();
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
        return control.getAllTimeTables();
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
