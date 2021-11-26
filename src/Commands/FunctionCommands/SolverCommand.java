package Commands.FunctionCommands;

import Commands.Command;
import Commands.NeedsCourses;
import DataGetting.DataGetter;
import Functions.DfsSearch;
import Functions.TimeTablePuzzle;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;

import java.util.*;

/**
 * A command to solve a TimeTable
 * === Private Attributes ===
 *  dataSource: The source where the data is from.
 *  manager: The manager that will eventually schedule given courses.
 */
public class SolverCommand implements Command, NeedsCourses {
    private final TimeTableManager manager;
    private final DataGetter dataSource;

    /**
     * A Constructor to create the Command
     *
     * @param manager the TimeTableManager with the TimeTables to be scheduled
     * @param dataSource the data source for all course information
     */
    public SolverCommand(TimeTableManager manager, DataGetter dataSource) {
        this.manager = manager;
        this.dataSource = dataSource;
    }


    /**
     *Prompt the User to input the courses they'd like to schedule.
     *
     */
    @Override
    public void execute(){
        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();
        boolean courseCounter = true;
        while (courseCounter) {
            // Get user input and course info
            LinkedHashMap<String, ArrayList<Course>> indivCourse =
                    NeedsCourses.userInputs(dataSource);
            // Get course code
            ArrayList<String> keyList = new ArrayList<>(indivCourse.keySet());
            Course course = indivCourse.get(keyList.get(0)).get(0);
            String courseCode = course.getCourseName();
            courses.put(courseCode, indivCourse);

            // Ask if user wants to schedule another course
            Scanner userCourse = new Scanner(System.in);
            System.out.println("Would you like to schedule another? (true/false)");
            if (!Boolean.parseBoolean(userCourse.nextLine())) {
                courseCounter = false;
            }

            // Figure out how to catch exception
        }

        TimeTablePuzzle puzzle = new TimeTablePuzzle(courses, manager);
        DfsSearch solver = new DfsSearch();
        Set<TimeTablePuzzle> seen = new HashSet<>();
        ArrayList<TimeTablePuzzle> solved = solver.solve(puzzle, seen);

       scheduleSolved(puzzle, solved.get(solved.size() - 1));
    }


    /**
     * A String representation of the Solver Command.
     *
     * @return the String representation.
     */
    @Override
    public String toString() {
        return "Used the Solver Function";
    }


    // ============================= Helpers ===================================

    /**
     * Schedule all the solved courses in the solved TimeTablePuzzle to this TimeTablePuzzle
     *
     * @param puzzle the TimeTablePuzzle with the TimeTables to be scheduled
     * @param solved the solved TimeTablePuzzle to be scheduled
     */
    public void scheduleSolved(TimeTablePuzzle puzzle, TimeTablePuzzle solved) {

        Scanner userTimeTableChoice = new Scanner(System.in);

        // Return Timetables to User
        String timeTables = (solved.getManager().toString());
        System.out.println(timeTables + " Do you like this schedule? (true/false)");
        if (Boolean.parseBoolean(userTimeTableChoice.nextLine())) {
            puzzle.schedulePuzzle(solved);
        }
    }
}
