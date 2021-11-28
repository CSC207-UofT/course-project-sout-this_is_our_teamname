package Commands.RemovalCommands;

import Commands.Command;
import Commands.CreationCommands.MakeCourseCommand;
import Commands.NeedsCourses;
import DataGetting.DataGetter;
import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A command to remove a Course Object.
 *
 * === Private Attributes ===
 * dataSource: The source where the data is from.
 * manager: The manager that will eventually remove the course
 */
public class RemoveCourse implements Command, NeedsCourses {
    private final DataGetter dataSource;
    private final TimeTableManager manager;

    /**
     * A constructor to initialize what this command is connected to
     *
     * @param manager the Manager to send to
     * @param dataSource the Source of the data of the course
     */
    public RemoveCourse(TimeTableManager manager, DataGetter dataSource) {
        this.manager = manager;
        this.dataSource = dataSource;
    }

    /**
     * Prompts the user to remove a course object
     */
    @Override
    public void execute() {
        boolean truth = true;

        while (truth) {
            // Clears the dataSource so it doesn't build up.
            dataSource.clearData();

            LinkedHashMap<String, ArrayList<Course>> course_data =
                    NeedsCourses.userInputs(dataSource);
            chooseCourseSection(course_data);
        }

    }

    // ============================= Helpers ===================================
    /**
     * Prompts the user to choose a Course section.
     * @param courseTypeToCourseMap HashMap of each type of course object to
     *                              the course object
     */
    private void chooseCourseSection(LinkedHashMap<String, ArrayList<Course>> courseTypeToCourseMap) {
        // For each component of this course (LEC/PRA/TUT)
        for (ArrayList<Course> courseComponent : courseTypeToCourseMap.values()) {
            // Display section options to User
            for (Course course : courseComponent) {
                System.out.println(course.getSectionName() + ": " + course);
            }
            InputChecker sectionChoice = new InputChecker("Please " +
                    "choose the section that you want (eg; LEC 0101. Only " +
                    "enter the section code).",
                    new MakeCourseCommand.isValidCourse(courseTypeToCourseMap.get(typeOfCourse)));
        }
    }

    // ======================== Predicates Classes =============================
    /**
     * A predicate to check if the course input is correct
     *
     * === Attributes ===
     * courseNameToCourseMap: A map of the course name to the course object
     */
    private static class isValidCourse extends Predicate {
        private final ArrayList<Course> courseNameToCourseMap;

        /**
         * Constructor.
         *
         * @param theMap the map to connect to. See above for description
         */
        private isValidCourse(ArrayList<Course> theMap){
            this.courseNameToCourseMap = theMap;
        }

        @Override
        public boolean run(String prompt) {
            return false;
        }

        /**
         * Runs the predicate
         *
         * @param prompt the prompt to ask the user
         * @return true iff the courseNameToCourseMap has the prompt as a key.
         */
}
