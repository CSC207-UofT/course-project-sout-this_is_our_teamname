package DataCollection;

import EntitiesAndObjects.Course;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

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
     * A Getter class for the Data HashMap
     * @param courseName the name of the Course
     * @return the Data HashMap
     */
    public LinkedHashMap<String, Course> getData(String courseName, String term, String year) throws FileNotFoundException {
        CalibrateData(courseName, term, year);
        return this.data;
    }
}
