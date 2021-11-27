package DataGetting;

import TimeTableObjects.Course;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * A data gathering class / database class
 */
public abstract class CourseGetter extends DataGetter<Course>{
    /**
     * Split the HashMap of Courses by types
     *
     * @param nameToObject the hashmap of the name of the course to the
     *                     Course object
     * @return a LinkedHashMap of the Course type to an ArrayList of Courses
     */
    private LinkedHashMap<String, ArrayList<Course>> splitByType(
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
     * A Getter class for the Data HashMap
     * @param courseName the name of the Course
     * @return the Data HashMap
     */
    public LinkedHashMap<String, ArrayList<Course>> retrieveData(String courseName,
                                                                 String term, String year) throws FileNotFoundException {
        CalibrateData(courseName, term, year);
        return splitByType(super.getData());
    }
}
