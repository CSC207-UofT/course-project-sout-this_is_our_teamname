package TimeTableObjects.CourseStuff;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class Course {

    private final String code;
    private final String professor;
    private final String faculty;
    private final String deliveryMethod;
    private final HashMap<String, String> timeLocation;

    /**
     * Construct a course with the given section, professor, faculty, delivery method,
     * time and location.
     *
     * @param section The section code for this course
     * @param professor The professor teaching this course section
     * @param faculty The faculty this course belongs to
     * @param deliveryMethod The delivery method for this course section
     * @param timeLocation The time and corresponding location for this course
     *                     section
     */
    public Course(String section, String professor, String faculty,
                  String deliveryMethod,
                  HashMap<String, String> timeLocation) {
        this.code = section;
        this.professor = professor;
        this.faculty = faculty;
        this.deliveryMethod = deliveryMethod;
        this.timeLocation = timeLocation;
    }

    /**
     * Get the times and locations for this course section.
     * @return a Hashmap with the keys as the times and the values as the cprresponding
     * location for this course section
     */
    public HashMap<String, String> getTimeLocation() {
        return this.timeLocation;
    }

    public String toString() {
        StringBuilder details =
                new StringBuilder(this.code + " with " + this.professor +
                " in the Faculty of " + this.faculty + "\n");
        details.append("The delivery method is ").append(this.deliveryMethod).append("\n").append("This course meets at ");
        for (String time : this.timeLocation.keySet()) {
            details.append(time).append(" at ").append(this.timeLocation.get(time)).append(",");
        }

        details.deleteCharAt(details.length() - 1);
        return details.toString();
    }

}
