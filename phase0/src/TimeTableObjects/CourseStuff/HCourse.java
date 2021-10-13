package TimeTableObjects.CourseStuff;

import java.util.ArrayList;
import java.util.HashMap;

public class HCourse extends Course {
    private final String term;

    public HCourse(String code, String professor, String faculty,
                   String deliveryMethod, HashMap<String, String> timeLocation,
                   String term) {
        super(code, professor, faculty, deliveryMethod, timeLocation);
        this.term = term;
    }

    /**
     * Get the term for this Course
     *
     * @return the term for this course
     */
    public String getTerm() {
        return this.term;
}
