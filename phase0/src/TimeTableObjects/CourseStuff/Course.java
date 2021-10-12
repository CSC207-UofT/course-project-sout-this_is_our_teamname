import java.util.HashMap;
import java.util.Map;
import java.util.*;

package TimeTableObjects.CourseStuff;


import TimeTableObjects.TimeTableObject;

public abstract class Course {

    private String code;
    private String professor;
    private String faculty;
    private String deliveryMethod;
    private Hashmap timeLocation;

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
    public Course(String code, String professor, String faculty,
                  String deliveryMethod, Hashmap timeLocation) {
        this.code = code;
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

    /**
     * Get the Course code for this Course
     *
     * @return the course code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Get the Professor teaching this Course
     *
     * @return the name of the Professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * Get the Faculty this course belongs to
     *
     * @return the Faculty this course belongs to
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Get the delivery method this course is delivered in
     *
     * @return the delivery method for this course
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Get the times and corresponding locations for this Course
     *
     * @return the times and corresponding locations
     */
    public Hashmap getTimeLocation() {
        return this.timeLocation;
    }

    /**
     * Return the details of this Course
     *
     * @return the details of this course
     */
    public String toString() {
        Stringbuilder details = new Stringbuilder(this.code + " with " + this.professor. +
                " in the Faculty of " + this.faculty + "\n");
        details.append("The delivery method is " + this.deliveryMethod + "\n"
        + "This course meets at ");
        for (ArrayList time : this.timeLocation.keyset()) {
            details.append(time + " at " + this.timeLocation.get(time) + ",");
        }

        details.deleteCharAt(details.length() - 1);
        return details
    }

}
