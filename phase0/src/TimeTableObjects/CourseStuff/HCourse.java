package TimeTableObjects.CourseStuff;

import java.util.HashMap;

public class HCourse extends Course {
    public HCourse(String code,
                   String professor,
                   String faculty,
                   String deliveryMethod,
                   HashMap<String[], String> timeLocation,
                   String term) {
        super(term, code, professor, faculty, deliveryMethod, timeLocation);
    }
}
