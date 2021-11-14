package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;

/**
 * This is a specific time interval of a specific section for a course.
 *
 * === Private Attributes ===
 * Description contains all the minor info of the course
 * code is the code of the course and the section
 * waitlist is whether this course is waitlisted or not.
 */
public class CourseSection extends Events implements java.lang.Comparable<CourseSection> {
    private final boolean waitlist;
    private final String code;
    private final String description;

    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     *
     * @param startTime The start time of this section.
     * @param endTime The end time of the section
     * @param theDate The date of the section
     * @param term The term for this course
     * @param code The code for this course
     * @param waitlist Whether the course is waitlisted
     */
    public CourseSection(LocalTime startTime, LocalTime endTime,
                         String theDate, String term, String code, boolean waitlist) {
        super(startTime, endTime, theDate, term);
        this.code = code;
        this.waitlist = waitlist;
        this.description = "";
    }

    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     *
     * @param startTime The start time of this section.
     * @param endTime The end time of the section
     * @param location The location of this section
     * @param theDate The date of the section
     * @param term The term for this course
     * @param code The code for this course
     * @param professor The professor teaching this course section
     * @param faculty The faculty this course belongs to
     * @param deliveryMethod The delivery method for this course section
     * @param waitlist Whether the course is waitlisted
     */
    public CourseSection(LocalTime startTime, LocalTime endTime, String location,
                         String theDate, String term, String code, String professor,
                         String faculty, String deliveryMethod, boolean waitlist) {
        super(startTime, endTime, location, theDate, term);
        this.waitlist = waitlist;
        this.code = code;
        this.description = "";
    }

    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Get the Course code for this Course
     *
     * @return the course code
     */
    public String getCode() {
        String[] splitStr = this.description.trim().split(" ");
        return splitStr[0];
    }

    /**
     * Get the waitlist for this Course
     *
     * @return true if section is waitlisted, false otherwise
     */
    public boolean getWaitlist() {
        return this.waitlist;
    }


    /**
     * Compare two sections to check if they overlap in time.
     *
     * @param other The second section to be checked
     * @return -1 if they overlap, 1 otherwise.
     */
    @Override
    public int compareTo(CourseSection other) {
        if (this.getDate().equals(other.getDate())) {
            int compare1 = this.getStartTime().compareTo(other.getStartTime());
            int compare2 = this.getEndTime().compareTo(other.getStartTime());
            int compare3 = this.getStartTime().compareTo(other.getEndTime());
            int compare4 = this.getEndTime().compareTo(other.getEndTime());
            if (( compare1 >= 0 && compare3 < 0) || (compare2 > 0 && compare4 <= 0)){
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
