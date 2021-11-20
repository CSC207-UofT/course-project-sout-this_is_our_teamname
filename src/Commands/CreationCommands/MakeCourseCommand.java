package Commands.CreationCommands;

import Commands.Command;
import Commands.NeedsCourses;
import Controllers.CommandFactory;
import DataGetting.DataGetter;
import Helpers.ConflictException;
import TimeTableContainers.Caretaker;
import TimeTableContainers.Originator;
import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import Helpers.InputCheckers.Predicate;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTableManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A command to create a Course Object.
 *
 * === Private Attributes ===
 * dataSource: The source where the data is from.
 * manager: The manager that will eventually schedule the object
 * currentManager: The index of the TimeTableManager stored in memento
 * caretaker: Stores all memento of TimeTableManager
 * originator: Sets and gets TimeTableManager from memento
 */
public class MakeCourseCommand implements Command, NeedsCourses {
    private final DataGetter dataSource;
    private final TimeTableManager manager;
    private final ArrayList<Course> scheduledCourse;
    private final int currentManager;
    private final Originator originator;
    private final Caretaker caretaker;

    /**
     * A constructor to initialize what this command is connected to
     *
     * @param sendTo the Manager to send to
     * @param dataSource the Source of the data of the course
     * @param currentManager the index of the TimeTableManager stored
     * @param originator stores the current TimeTableManager
     * @param caretaker where all TimeTableManager states are stored
     */
    public MakeCourseCommand(TimeTableManager sendTo, DataGetter dataSource, int currentManager, Originator originator, Caretaker caretaker){
        this.manager = sendTo;
        this.dataSource = dataSource;
        this.scheduledCourse = new ArrayList<>();
        this.currentManager = currentManager;
        this.originator = originator;
        this.caretaker = caretaker;
    }

    /**
     * Prompts the user to create a course object
     */
    @Override
    public void execute() {
        boolean running = true;

        while (running){
            // Clears the dataSource so it doesn't build up.
            dataSource.clearData();

            LinkedHashMap<String, ArrayList<Course>> course_data =
                    NeedsCourses.userInputs(dataSource);
            promptUser(course_data);

            boolean isConflicted = false;
            // Pass this to the TimeTableManager. We will fix it in Phase 2
            // so that there is no lingering courses scheduled!
            for (Course item : this.scheduledCourse){
                ArrayList<CourseSection> sections = item.split();
                for (CourseSection section: sections){
                    try {
                        manager.schedule(section);
                        running = false;
                    }
                    catch (ConflictException e) {
                        isConflicted = true;
                        break;
                    }
                }
                // If there was a conflict, don't bother scheduling anything
                // else!
                if (isConflicted){
                    break;
                }
            }
            //Add to memento
            originator.setCalender(manager);
            caretaker.addMemento(currentManager, originator.storeInMemento());
            CommandFactory.addCurrentManager();

            // If there is a conflict
            if (isConflicted){
                InputChecker repeat = new InputChecker(
                        "An conflict has occurred! Course" +
                                " scheduled Unsuccessfully. Would you" +
                                " like to try again? (true/false)",
                        new isBoolean());

                String repeatInput = repeat.checkCorrectness();
                if (repeatInput.equals("false")){
                    running = false;
                }
            }
        }
    }

    /**
     * A String representation of the Command Object
     *
     * @return the string representation of the command object
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Scheduled the item");
        if (this.hasScheduled()){
            for (Course item : this.scheduledCourse){
                sb.append(item.getSectionName());
            }
            return sb.toString();
        } else {
            return "No Course Scheduled";
        }
    }

    // ============================= Helpers ===================================
    /**
     * Prompts the user
     * @param courseTypeToCourseMap HashMap of each type of course object to
     *                              the course object
     */
    private void promptUser(LinkedHashMap<String, ArrayList<Course>> courseTypeToCourseMap) {
        // For each type of course object
        for (String typeOfCourse : courseTypeToCourseMap.keySet()) {

            // Get list of all the course objects
            ArrayList<Course> listOfCourses =
                    courseTypeToCourseMap.get(typeOfCourse);

            // Prompts the user with choices
            for (Course course : listOfCourses) {
                System.out.println(course.getSectionName() + ": " + course);
            }

            InputChecker sectionChoice = new InputChecker("Please " +
                    "choose the section that you want (eg; LEC 0101. Only " +
                    "enter the section code). Enter 'Null' if you do not want to " +
                    "schedule any section: ",
                    new isValidCourse(courseTypeToCourseMap.get(typeOfCourse)));
            String selected = sectionChoice.checkCorrectness();

            if (!selected.equals("Null")){
                // Get the course to schedule
                ArrayList<Course> section =
                        courseTypeToCourseMap.get(typeOfCourse);
                Course toSchedule = findAssociatedCourse(selected, section);
                this.scheduledCourse.add(toSchedule);
            }
        }
    }

    /**
     * A helper method to find the course object of a name from a list of
     * course objects.
     *
     * @param prompt the Course name
     * @param list the list of courses
     * @return the Course with the name prompt
     */
    private Course findAssociatedCourse(String prompt, ArrayList<Course> list){
        for (Course course : list){
            if (course.getSectionName().equals(prompt)){
                return course;
            }
        }
        return null;
    }

    // ======================== Predicates Classes =============================
    /**
     * A predicate to check if the course input is correct
     *
     * === Attributes ===
     * courseNameToCourseMap: A map of the course name to the course object
     */
    private static class isValidCourse extends Predicate{
        private final ArrayList<Course> courseNameToCourseMap;

        /**
         * Constructor.
         *
         * @param theMap the map to connect to. See above for description
         */
        private isValidCourse(ArrayList<Course> theMap){
            this.courseNameToCourseMap = theMap;
        }

        /**
         * Runs the predicate
         *
         * @param prompt the prompt to ask the user
         * @return true iff the courseNameToCourseMap has the prompt as a key.
         */
        @Override
        public boolean run(String prompt) {
            if (prompt.equals("Null")){
                return true;
            } else {
                for (Course course : courseNameToCourseMap){
                    if (course.getSectionName().equals(prompt)){
                        return true;
                    }
                }
                return false;
            }
        }
    }

    /**
     * A predicate to check if an input is a boolean
     */
    private static class isBoolean extends Predicate{

        @Override
        public boolean run(String prompt) {
            return prompt.equals("true") || prompt.equals("false");
        }
    }

    // ============================ Predicates =================================
    /**
     * Return if there has already been a course been scheduled
     * @return true iff there has been a course scheduled.
     */
    protected boolean hasScheduled(){
        return scheduledCourse.size() != 0;
    }
}
