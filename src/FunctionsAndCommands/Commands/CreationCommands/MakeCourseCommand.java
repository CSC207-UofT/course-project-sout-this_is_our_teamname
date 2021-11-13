package FunctionsAndCommands.Commands.CreationCommands;

import DataCollection.DataGetter;
import EntitiesAndObjects.Course;
import EntitiesAndObjects.TimeTableObjects.CourseSection;
import FunctionsAndCommands.Commands.Command;
import GlobalHelpers.InputCheckers.Predicate;
import GlobalHelpers.InputCheckers.InputChecker;
import TimeTableStuff.TimeTableManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * A command to create a Course Object.
 *
 * === Private Attributes ===
 * dataSource: The source where the data is from.
 * manager: The manager that will eventually schedule the object
 */
public class MakeCourseCommand implements Command {
    private final DataGetter dataSource;
    private final TimeTableManager manager;
    private final ArrayList<Course> scheduledCourse;

    /**
     * A constructor to initialize what this command is connected to
     *
     * @param sendTo the Manager to send to
     * @param dataSource the Source of the data of the course
     */
    public MakeCourseCommand(TimeTableManager sendTo, DataGetter dataSource){
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

        HashMap<String, Course> course_data = new HashMap<>();

        boolean validCourseChecker = true;
        while (validCourseChecker){
            // The user enters the section they want to search
            Scanner userChoice = new Scanner(System.in);
            System.out.println("Please Enter the course Name (eg CSC207H1. " +
                    "Don't forget the 'H1'!!!): ");
            String course = userChoice.nextLine();

            try {
                // Gets the data from the datasource
                course_data = dataSource.getData(course);
                validCourseChecker = false;
            } catch (FileNotFoundException e) {
                System.out.println("Course not found. Please try again!");
            }
        }

        promptUser(course_data, splitByType(course_data));

        // Pass this to the TimeTableManager
        for (Course item : this.scheduledCourse){
            ArrayList<CourseSection> sections = item.split();
            for (CourseSection section: sections){
            manager.schedule(section);
            }
        }
    }

    // ============================= Helpers ===================================
    /**
     * Prompts the user
     * @param courseNameToCourseMap the HashMap of course data
     * @param courseTypeToCourseMap HashMap of each courseTypeToCourseMap
     */
    private void promptUser(HashMap<String, Course> courseNameToCourseMap,
                            LinkedHashMap<String, ArrayList<Course>>
                                    courseTypeToCourseMap) {
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
                    new isValidCourse(courseNameToCourseMap));
            String selected = sectionChoice.checkCorrectness();

            if (!selected.equals("Null")){
                // Get the course to schedule
                Course toSchedule = courseNameToCourseMap.get(selected);
                this.scheduledCourse.add(toSchedule);
            }
        }
    }

    /**
     * Split the HashMap of Courses by types
     *
     * @param nameToCourse the hashmap of the name of the course to the
     *                     Course object
     * @return a LinkedHashMap of the Course type to an ArrayList of Courses
     */
    private LinkedHashMap<String, ArrayList<Course>> splitByType(
            HashMap<String, Course> nameToCourse){
        LinkedHashMap<String, ArrayList<Course>> typeToItems =
                new LinkedHashMap<>();
        for (String sectionName : nameToCourse.keySet()){
            String typeOfObject = sectionName.substring(0, 3);
            if (!typeToItems.containsKey(typeOfObject)){
                typeToItems.put(typeOfObject, new ArrayList<>());
            }
            typeToItems.get(typeOfObject).add(nameToCourse.get(sectionName));
        }
        return typeToItems;
    }

    // ============================= Predicates ================================
    /**
     * A predicate to check if the course input is correct
     *
     * === Attributes ===
     * courseNameToCourseMap: A map of the course name to the course object
     */
    private static class isValidCourse extends Predicate{
        private final HashMap<String, Course> courseNameToCourseMap;

        /**
         * Constructor.
         *
         * @param theMap the map to connect to. See above for description
         */
        private isValidCourse(HashMap<String, Course> theMap){
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
            return courseNameToCourseMap.containsKey(prompt) || prompt.equals("Null");
        }
    }

    /**
     * Return if there has already been a course been scheduled
     * @return true iff there has been a course scheduled.
     */
    protected boolean hasScheduled(){
        return scheduledCourse.size() != 0;
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
}
