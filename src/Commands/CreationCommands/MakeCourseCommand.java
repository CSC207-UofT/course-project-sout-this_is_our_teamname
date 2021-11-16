package Commands.CreationCommands;

import Commands.Command;
import DataGetting.DataGetter;
import Helpers.ConflictException;
import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import Helpers.InputCheckers.Predicate;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTableManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        boolean running = true;

        while (running){
            // Clears the dataSource so it doesn't build up.
            dataSource.clearData();

            LinkedHashMap<String, ArrayList<Course>> course_data = userInputs();

            // Pass this to the TimeTableManager
            for (Course item : this.scheduledCourse){
                ArrayList<CourseSection> sections = item.split();
                for (CourseSection section: sections){
                    try {
                        manager.schedule(section);
                    }
                    catch (ConflictException e) {
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
            System.out.println("Course Scheduled");
        }
    }

    /**
     * Prompts the user for inputs
     */
    private LinkedHashMap<String, ArrayList<Course>> userInputs() {
        LinkedHashMap<String, ArrayList<Course>> course_data =
                new LinkedHashMap<>();

        boolean validCourseChecker = true;
        while (validCourseChecker){
            // The user enters the section they want to search
            String[] questions = {"Please Enter the course Name (eg CSC207H1. " +
                    "Don't forget the 'H1'!!!): ", "Enter the term of the " +
                    "course (Fall/Winter):", "Enter the year of the course " +
                    "(2020/2021): "};
            String[] responses = new String[3];
            for (int i = 0; i < questions.length; i++) {
                Scanner userChoice = new Scanner(System.in);
                System.out.println(questions[i]);
                responses[i] = userChoice.nextLine();
            }

            try {
                // Gets the data from the datasource
                course_data = dataSource.getData(responses[0], responses[1],
                        responses[2]);
                validCourseChecker = false;
            } catch (FileNotFoundException e) {
                System.out.println("Course not found. Please try again!");
            }
        }

        promptUser(course_data);
        return course_data;
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

    // ============================= Predicates ================================
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
