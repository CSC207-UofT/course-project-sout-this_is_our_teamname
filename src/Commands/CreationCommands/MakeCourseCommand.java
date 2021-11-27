package Commands.CreationCommands;

import Commands.Command;
import Commands.NeedsCourses;
import DataGetting.CourseGetter;
import Helpers.ConflictException;
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
 */
public class MakeCourseCommand implements Command, NeedsCourses {
    private final CourseGetter dataSource;
    private final TimeTableManager manager;
    private final ArrayList<Course> scheduledCourse;

    /**
     * A constructor to initialize what this command is connected to
     *
     * @param sendTo the Manager to send to
     * @param dataSource the Source of the data of the course
     */
    public MakeCourseCommand(TimeTableManager sendTo, CourseGetter dataSource){
        this.manager = sendTo;
        this.dataSource = dataSource;
        this.scheduledCourse = new ArrayList<>();
    }

    /**
     * Prompts the user to create a course object
     */
    @Override
    public void execute() {
        // Clears the dataSource so it doesn't build up.
        dataSource.clearData();

        LinkedHashMap<String, ArrayList<Course>> course_data =
                NeedsCourses.userInputs(dataSource);
        promptUser(course_data);

        ArrayList<CourseSection> sections = new ArrayList<>();

        boolean hasConflict = checkConflicts(sections);

        if (!hasConflict){
            // Pass this to the TimeTableManager.
            for (CourseSection item : sections){
                try {
                    manager.schedule(item);
                } catch (ConflictException e) {
                    System.out.println("ERROR MakeCourseCommand");
                }
            }
        } else {
            System.out.println("A Conflict has occurred. Please Try Again!");
        }
    }

    /**
     * A String representation of the Command Object
     *
     * @return the string representation of the command object
     */
    @Override
    public String toString(){
        StringBuilder temporaryString = new StringBuilder("Scheduled the item" +
                " ");
        if (this.hasScheduled()){
            for (Course item : this.scheduledCourse){
                temporaryString.append(item.getSectionName()).append(" ");
            }
            return temporaryString.toString();
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

    /**
     * Checks if there is a conflict in scheduling a course. Add courseSection
     * to `sections` of there are no conflicts
     * @param sections the list of courses
     * @return true iff there is a conflict. Returns false otherwise.
     */
    private boolean checkConflicts(ArrayList<CourseSection> sections) {
        for (Course course : this.scheduledCourse){
            ArrayList<CourseSection> conflictCheckSections = course.split();
            for (CourseSection sectionOfCourse : conflictCheckSections){
                if (manager.checkConflicts(sectionOfCourse)){
                    sections.add(sectionOfCourse);
                } else {
                    return true;
                }
            }
        }
        return false;
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

    // ============================ Predicates =================================
    /**
     * Return if there has already been a course been scheduled
     * @return true iff there has been a course scheduled.
     */
    protected boolean hasScheduled(){
        return scheduledCourse.size() != 0;
    }
}
