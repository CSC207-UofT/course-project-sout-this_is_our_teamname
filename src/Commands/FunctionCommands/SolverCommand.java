package Commands.FunctionCommands;

import Commands.Command;
import Commands.NeedsCourses;
import DataGetting.DataGetter;
import Functions.DfsSearch;
import Functions.Puzzle;
import Functions.Solver;
import Functions.TimeTablePuzzle;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;

import java.util.*;

/**
 * THIS IS AN EXAMPLE OF A COMMAND OBJECT. IT IS POORLY DESIGNED ON PURPOSE.
 * TODO PLEASE IMPLEMENT THIS CLASS @TA IF THIS MESSAGE IS STILL HERE WHEN
 * TODO YOU MARK THIS, DO NOT MARK THIS!!!
 */
public class SolverCommand implements Command, NeedsCourses {
    private final TimeTableManager manager;
//    private final TimeTablePuzzle puzzle;
    private final DataGetter dataSource;

    public SolverCommand(TimeTableManager manager, DataGetter dataSource) {
        this.manager = manager;
        this.dataSource = dataSource;
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

        TimeTablePuzzle puzzle = new TimeTablePuzzle(courses, this.manager);
        DfsSearch solver = new DfsSearch();
        Set<String> seen = new HashSet<>();
        ArrayList<Puzzle> solved = solver.solve(puzzle, seen);

        Scanner userTimeTableChoice = new Scanner(System.in);
        int lastIndex = solved.size() - 1;

        // Return Timetables to User
        if (solved.get(lastIndex) instanceof TimeTablePuzzle) {
            // TimeTable[] timeTables = ((TimeTablePuzzle) solved.get(lastIndex)).getManager().getAllTimeTables();
            String timeTables = ((TimeTablePuzzle) solved.get(lastIndex)).getManager().toString();
            System.out.println(timeTables + " Do you like this schedule? (true/false)");
            if (Boolean.parseBoolean(userTimeTableChoice.nextLine())) {
                puzzle.schedulePuzzle((TimeTablePuzzle) solved.get(lastIndex));
            }
        }
    }


    @Override
    public String toString() {
        return "Used the Solver Function";
    }

    // ============================ Predicates =================================


}
