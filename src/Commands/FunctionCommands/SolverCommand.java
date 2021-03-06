package Commands.FunctionCommands;

import Commands.NeedsCoursesCommand;
import DataGetting.CourseGetter;
import DataGetting.WebScraper;
import Functions.DfsSearch;
import Functions.TimeTablePuzzle;
import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;

import java.util.*;

/**
 *
 * A command to solve a TimeTable
 * === Private Attributes ===
 * dataSource: The source where the data is from.
 * manager: The manager that will eventually schedule given courses.
 */
public class SolverCommand extends NeedsCoursesCommand {
    private final CourseGetter dataSource;
    private final TimeTableManager manager;

    /**
     * A Constructor to create the Command
     *
     * @param manager    the TimeTableManager with the TimeTables to be scheduled
     * @param dataSource the data source for all course information
     */
    public SolverCommand(TimeTableManager manager, CourseGetter dataSource) {
        this.manager = manager;
        this.dataSource = dataSource;
    }


    /**
     * Prompt the User to input the courses they'd like to schedule.
     */
    @Override
    public void execute() {
        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();
        boolean courseCounter = true;
        while (courseCounter) {
            // Get user input and course info
            dataSource.clearData();
            LinkedHashMap<String, ArrayList<Course>>indivCourse = userInputs(dataSource);

            // Get course code. Also useful to prevent courses aren't
            // scheduled twice!
            ArrayList<String> keyList = new ArrayList<>(indivCourse.keySet());
            Course course = indivCourse.get(keyList.get(0)).get(0);
            String courseCode = course.getCourseName();
            courses.put(courseCode, indivCourse);

            // Ask if user wants to schedule another course
            InputChecker checker = new InputChecker(
                    "Would you like to schedule another? (true/false)",
                    new isBoolean());
            String userChoice = checker.checkCorrectness();

            if (!Boolean.parseBoolean(userChoice)) {
                courseCounter = false;
            }

            // Figure out how to catch exception
        }

        TimeTablePuzzle puzzle = new TimeTablePuzzle(courses, manager,
                hashmapToArraylist(courses), new ArrayList<>());
        DfsSearch solver = new DfsSearch();
        Set<String> seen = new HashSet<>();
        ArrayList<TimeTablePuzzle> solved = solver.solve(puzzle, seen);

        if (solved.size() == 0){
            System.out.println("No possible combinations found!");
        } else {
            scheduleSolved(puzzle, solved.get(solved.size() - 1));
        }
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
    public void scheduleSolved(TimeTablePuzzle puzzle,
                               TimeTablePuzzle solved) {
        // Return Timetables to User
        String timeTables = solved.getManager().toString();

        InputChecker checker = new InputChecker(timeTables
                + " Do you like this schedule? (true/false)", new isBoolean());
        String userTimeTableChoice = checker.checkCorrectness();
        if (Boolean.parseBoolean(userTimeTableChoice)) {
            puzzle.schedulePuzzle(solved);
        }
    }

    /**
     * Converts the arraylist of courses in 2-dimensional hashmap to courses in 2-dimensional arraylist.
     * @param courses is the arraylist of courses in 2-dimensional hashmap
     * @return courses in 2-dimensional arraylist
     */
    private ArrayList<ArrayList<Course>> hashmapToArraylist(HashMap<String, HashMap<String, ArrayList<Course>>> courses){
        ArrayList<ArrayList<Course>> twoDimensionalList = new ArrayList<>();
        for (HashMap<String, ArrayList<Course>> cor : courses.values()){
            twoDimensionalList.addAll(cor.values());
        }
        return twoDimensionalList;
    }

    public static void main(String[] args) {
        SolverCommand cmd = new SolverCommand(new TimeTableManager(), new WebScraper());
        cmd.execute();
    }

    private static class isBoolean extends Predicate{
        @Override
        public boolean run(String prompt) {
            return prompt.equals("true") || prompt.equals("false");
        }
    }
}
