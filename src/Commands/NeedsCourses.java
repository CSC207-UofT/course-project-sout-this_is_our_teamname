package Commands;

import DataGetting.CourseGetter;
import Helpers.InputCheckers.InputChecker;
import TimeTableObjects.Course;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * An interface for commands that requires searching for courses through a
 * dataSource
 */
public interface NeedsCourses {
    /**
     * Prompts the user for inputs
     */
    static LinkedHashMap<String, ArrayList<Course>> userInputs(CourseGetter dataSource) {
        LinkedHashMap<String, ArrayList<Course>> course_data =
                new LinkedHashMap<>();

        boolean validCourseChecker = true;
        while (validCourseChecker){
            // The user enters the section they want to search
            String[] questions = {"Please Enter the course Name (eg CSC207H1. " +
                    "Don't forget the 'H1'!!!): ", "Enter the term of the " +
                    "course (Fall/Winter):", "Enter the year of the course " +
                    "(2020/2021): "};
            String[] responses = InputChecker.getQuestionsAnswers(questions);

            try {
                // Gets the data from the datasource
                course_data = dataSource.retrieveData(responses[0], responses[1],
                        responses[2]);
                validCourseChecker = false;
            } catch (FileNotFoundException e) {
                System.out.println("Course not found. Please try again!");
            }
        }

        return course_data;
    }
}
