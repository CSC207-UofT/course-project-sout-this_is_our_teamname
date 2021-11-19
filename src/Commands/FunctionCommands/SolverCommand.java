package Commands.FunctionCommands;

import Commands.Command;
import Functions.DfsSearch;
import Functions.Puzzle;
import Functions.Solver;
import Functions.TimeTablePuzzle;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * THIS IS AN EXAMPLE OF A COMMAND OBJECT. IT IS POORLY DESIGNED ON PURPOSE.
 * TODO PLEASE IMPLEMENT THIS CLASS @TA IF THIS MESSAGE IS STILL HERE WHEN
 * TODO YOU MARK THIS, DO NOT MARK THIS!!!
 */
public class SolverCommand implements Command {
    private final TimeTableManager manager;
//    private final TimeTablePuzzle puzzle;

    public SolverCommand(TimeTableManager manager) {
        this.manager = manager;
    }



    /**
     * Treat this method as like the "main" (psvm method, or if __name__ ==
     * "__main__" method in Python).
     */
    @Override
    public void execute(){
        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();
        boolean courseCounter = true;
        while (courseCounter) {
            Scanner userCourse = new Scanner(System.in);
            System.out.println("Which course would you like to automatically schedule? " +
                            "Enter the course Name (eg CSC207H1. " + "Don't forget the 'H1'");
//            courses.add(userCourse.nextLine());
            System.out.println("Would you like to schedule another?");
            if (!Boolean.parseBoolean(userCourse.nextLine())) {
                courseCounter = false;
            }
        }

        TimeTablePuzzle puzzle = new TimeTablePuzzle(courses, this.manager);



    }


    @Override
    public String toString() {
        return "Used the Solver Function";
    }


}
