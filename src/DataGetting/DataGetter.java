package DataGetting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A DataGetter Superclass that connects to external sources to retrieve data
 *
 * === Private Attributes ===
 * data: a HashMap, where the keys are the names of the object and the values
 *  are the objects
 *
 * @param <T> the type of object to be returned
 */
public abstract class DataGetter<T>{
    private final LinkedHashMap<String, T> data;

    /**
     * A constructor for this class.
     */
    public DataGetter(){
        this.data = new LinkedHashMap<>();
    }

    /**
     * An abstract class to calibrate the data HashMap!
     *
     * @param itemName the name of the course.
     * @param theTerm the term of the course
     * @param theYear the course starts.
     */
    abstract void CalibrateData(String itemName, String theTerm,
                                String theYear) throws FileNotFoundException;

    /**
     * Retrieve the data from the DataGetter
     *
     * @param courseName the name of the Course
     * @param term the term of the course
     * @param year the year of the course
     * @return a linkedHashmap of courses, where the keys are the type of
     * course (LEC, TUT, PRA) and the values are arraylist of course objects
     * @throws FileNotFoundException If the file is not found.
     */
    abstract LinkedHashMap<String, T> retrieveData(String courseName,
                                                   String term, String year) throws FileNotFoundException;

    /**
     * Add the object to the data HashMap
     *
     * @param name the name of the object
     * @param theItem the object to be placed
     */
    public void placeToData(String name, T theItem) {
        this.data.put(name, theItem);
    }

    /**
     * Clears the data stored
     */
    public void clearData(){
        this.data.clear();
    }

    /**
     * Get the raw data stored in the hashmap
     * @return A Hashmap of strings of object name to object stored in the
     *  hashmap.
     */
    public LinkedHashMap<String, T> getData() {
        return data;
    }
}
