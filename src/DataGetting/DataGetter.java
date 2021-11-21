package DataGetting;

import TimeTableObjects.Course;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * A data gathering class / database class
 */
public abstract class DataGetter {
    private final LinkedHashMap<String, Course> data;

    /**
     * A constructor for this class.
     * - data is a HashMap, where the keys are the section name, and the
     * values are the Course objects
     */
    public DataGetter(){
        this.data = new LinkedHashMap<>();
    }

    /**
     * Add the Course to the data HashMap
     *
     * @param name the name of the section
     * @param theCourse the course object
     */
    protected void placeToData(String name, Course theCourse) {
        this.data.put(name, theCourse);
    }

    /**
     * An abstract class to calibrate the data HashMap!
     *
     * TODO @Sonny NOTICE CHANGE HERE. I added the throw exceptions
     * @param courseName the name of the course.
     * @param theTerm the term of the course
     * @param theYear the course starts.
     */
    abstract void CalibrateData(String courseName, String theTerm,
                                String theYear) throws FileNotFoundException;

    public void clearData(){
        this.data.clear();
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

        // A hashMap of type of item (LEC, TUT, PRA) to the course object
        LinkedHashMap<String, ArrayList<Course>> typeToItems =
                new LinkedHashMap<>();

        for (String sectionName : nameToCourse.keySet()){
            // The type of the object is always the first three letters of a
            // section code
            String typeOfObject = sectionName.substring(0, 3);

            // If there is no objects of that type currently in typesToItems,
            // create empty list
            if (!typeToItems.containsKey(typeOfObject)){
                typeToItems.put(typeOfObject, new ArrayList<>());
            }

            // Add the item to the list in the hashMap
            typeToItems.get(typeOfObject).add(nameToCourse.get(sectionName));
        }
        return typeToItems;
    }

    /**
     * A Getter class for the Data HashMap
     * @param courseName the name of the Course
     * @return the Data HashMap
     */
    public LinkedHashMap<String, ArrayList<Course>> getData(String courseName,
                                                  String term, String year) throws FileNotFoundException {
        CalibrateData(courseName, term, year);
        return splitByType(this.data);
    }
}
