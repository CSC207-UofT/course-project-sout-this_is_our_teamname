package TimeTableObjects.CourseStuff;

import TimeTableObjects.TimeTableObject;

public class Section extends TimeTableObject implements Comparable<Section>{
    private final String code;
    private final String professor;
    private final String faculty;
    private final String deliveryMethod;

    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     *
     * @param startTime The start time of this section.
     * @param endTime The end time of the section
     * @param thedate The date of the section
     * @param location The location of this section
     * @param code The code for this course
     * @param professor The professor teaching this course section
     * @param faculty The faculty this course belongs to
     * @param deliveryMethod The delivery method for this course section
     */
    public Section(String startTime, String endTime, String thedate,
                   String location, String code, String professor,
                   String faculty, String deliveryMethod) {
        super(startTime, endTime, location, thedate);
        this.code = code;
        this.professor = professor;
        this.faculty = faculty;
        this.deliveryMethod = deliveryMethod;
    }

    @Override
    public String toString() {
        return "";
    }

    public String getCode() {
        return code;
    }

    public String getProfessor() {
        return professor;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public String getFaculty() {
        return faculty;
    }

    /**
     * Get the Course code for this Course
     *
     * @return the course code
     */
    public String getCode() {
        return code;
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

    @Override
    public int compareTo(Section anotherSection) {
        // TODO Implement this!
        return 0;
    }
}
