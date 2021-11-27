package DataGetting;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class DataGetter<T>{
    private final LinkedHashMap<String, T> data;

    /**
     * A constructor for this class.
     * - data is a HashMap, where the keys are the section name, and the
     * values are the Course objects
     */
    public DataGetter(){
        this.data = new LinkedHashMap<>();
    }

    /**
     * An abstract class to calibrate the data HashMap!
     * @param itemName the name of the course.
     * @param theTerm the term of the course
     * @param theYear the course starts.
     */
    abstract void CalibrateData(String itemName, String theTerm,
                                String theYear) throws FileNotFoundException;

    abstract LinkedHashMap<String, ArrayList<T>> retrieveData(String courseName,
                                                              String term, String year) throws FileNotFoundException;

    /**
     * Add the Course to the data HashMap
     *
     * @param name the name of the section
     * @param theItem the course object
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

    public LinkedHashMap<String, T> getData() {
        return data;
    }
}
