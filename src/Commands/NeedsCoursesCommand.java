package Commands;

import DataGetting.CourseGetter;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * An interface for commands that requires searching for courses through a
 * dataSource
 */
public abstract class NeedsCoursesCommand implements Command {
    /**
     * Split the HashMap of Courses by types
     *
     * @param nameToObject the hashmap of the name of the course to the
     *                     Course object
     * @return a LinkedHashMap of the Course type to an ArrayList of Courses
     */
    private static LinkedHashMap<String, ArrayList<Course>> splitByType(
            HashMap<String, Course> nameToObject){

        // A hashMap of type of item (LEC, TUT, PRA) to the course object
        LinkedHashMap<String, ArrayList<Course>> typeToItems =
                new LinkedHashMap<>();

        for (String sectionName : nameToObject.keySet()){
            // The type of the object is always the first three letters of a
            // section code
            String typeOfObject = sectionName.substring(0, 3);

            // If there is no objects of that type currently in typesToItems,
            // create empty list
            if (!typeToItems.containsKey(typeOfObject)){
                typeToItems.put(typeOfObject, new ArrayList<>());
            }

            // Add the item to the list in the hashMap
            typeToItems.get(typeOfObject).add(nameToObject.get(sectionName));
        }
        return typeToItems;
    }

    /**
     * Prompts the user for inputs
     */
    public LinkedHashMap<String, ArrayList<Course>> userInputs(CourseGetter dataSource) {
        LinkedHashMap<String, Course> course_data =
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


        return splitByType(course_data);
    }
}
