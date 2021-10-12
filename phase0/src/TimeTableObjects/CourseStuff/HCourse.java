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
}
