package DataGetting;

import TimeTableObjects.Course;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

/**
 *
 * A data gathering class / database class
 */
public abstract class CourseGetter extends DataGetter<Course>{
    /**
     * A Getter class for the Data HashMap
     * @param courseName the name of the Course
     * @return the Data HashMap
     */
    @Override
    public LinkedHashMap<String, Course> retrieveData(String courseName,
                                                      String term, String year) throws FileNotFoundException {
        CalibrateData(courseName, term, year);
        return super.getData();
    }
}
