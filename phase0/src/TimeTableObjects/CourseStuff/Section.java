package TimeTableObjects.CourseStuff;

import TimeTableObjects.TimeTableObject;

import java.sql.Time;

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
     * @param location The location of this section
     * @param thedate The date of the section
     * @param term The term for this course
     * @param code The code for this course
     * @param professor The professor teaching this course section
     * @param faculty The faculty this course belongs to
     * @param deliveryMethod The delivery method for this course section
     */
    public Section(Time startTime, Time endTime, String location,
                   String thedate, String term, String code, String professor,
                   String faculty, String deliveryMethod) {
        super(startTime, endTime, location, thedate, term);
        this.code = code;
        this.professor = professor;
        this.faculty = faculty;
        this.deliveryMethod = deliveryMethod;
    }

    @Override
    public String toString() {
        return this.getStartTime() + " - " + this.getEndTime() + ": " +
                this.code + " at " + this.getLocation();
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

    /**
     * Compare two sections to check if they overlap in time.
     *
     * @param anotherSection The second section to be checked
     * @return -1 if they overlap, 1 otherwise.
     */
    @Override
    public int compareTo(Section anotherSection) {
        if (this.getDate().equals(anotherSection.getDate())) {
            int compare1 = anotherSection.getStartTime().compareTo(this.getEndTime());
            int compare2 = this.getStartTime().compareTo(anotherSection.getEndTime());
            if (compare1 <= 0 || compare2 <= 0) {
                return -1;
            }
            else {
                return 1;
            }
        }
        else {
            return 1;
        }
    }

}
