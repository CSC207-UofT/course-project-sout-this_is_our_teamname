package TimeTableObjects.CourseStuff;

import java.util.HashMap;

public class YCourse extends Course{
    private String term;

    public YCourse(String code, String professor, String faculty,
                   String deliveryMethod,
                   HashMap<String, String> timeLocation) {
        super(code, professor, faculty, deliveryMethod, timeLocation);
        this.term = "Y";
    }

    /**
     * Get the term for this Course
     *
     * @return the term for this course
     */
    public String getTerm() {
        return this.term;
}
