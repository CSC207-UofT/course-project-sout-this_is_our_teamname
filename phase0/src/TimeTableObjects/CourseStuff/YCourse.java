package TimeTableObjects.CourseStuff;

import java.util.HashMap;
import ConstantsAndExceptions.Constants;

public class YCourse extends Course{
    public YCourse(String theCourseCode,
                   String theProfessor,
                   String faculty,
                   String deliveryMethod,
                   HashMap<String[], String> timeLocation) {
        super(Constants.YEAR, theCourseCode, theProfessor, faculty,
                deliveryMethod,
                timeLocation);
    }

    /**
     * Get the term for this Course
     *
     * @return the term for this course
     */
    public String getTerm() {
        return this.term;
}
