package TimeTableObjects.EventObjects;

import TimeTableObjects.Events;

import java.time.LocalTime;

/**
 * This is a specific time interval of a specific section for a course.
 *
 * === Private Attributes ===
 * waitlist is whether this course is waitlisted or not.
 * courseName is the course code of the course section
 * sectionCode is the section code of the course section
 * description contains all the other info of the course section
 */
public class CourseSection extends Events implements java.lang.Comparable<CourseSection> {
    private final boolean waitlist;
    private final String courseName;
    private final String sectionCode;
    private final String description;

    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     *
     * @param CourseName The CourseName
     * @param startTime The start time of this section.
     * @param endTime The end time of the section
     * @param theDate The date of the section
     * @param term The term for this course
     * @param sectionCode The code for this course
     * @param waitlist Whether the course is waitlisted
     */
    public CourseSection(String CourseName, LocalTime startTime,
                         LocalTime endTime, String theDate, String term,
                         String sectionCode, boolean waitlist) {
        super(startTime, endTime, theDate, term);
        this.courseName = CourseName;
        this.sectionCode = sectionCode;
        this.waitlist = waitlist;
        this.description = "";
    }
//
//    /**
//     * Construct a TimeTable section for the given time, location, section, professor,
//     * faculty and delivery method
//     *
//     * @param CourseName The CourseName
//     * @param startTime The start time of this section.
//     * @param endTime The end time of the section
//     * @param location The location of this section
//     * @param theDate The date of the section
//     * @param term The term for this course
//     * @param sectionCode The code for this course
//     * @param professor The professor teaching this course section
//     * @param faculty The faculty this course belongs to
//     * @param deliveryMethod The delivery method for this course section
//     * @param waitlist Whether the course is waitlisted
//     */
//    public CourseSection(String CourseName, LocalTime startTime,
//                         LocalTime endTime, String location, String theDate,
//                         String term, String sectionCode, String professor,
//                         String faculty, String deliveryMethod, boolean waitlist) {
//        super(startTime, endTime, location, theDate, term);
//        this.courseName = CourseName;
//        this.waitlist = waitlist;
//        this.sectionCode = sectionCode;
//        this.description = sectionCode + " of " + faculty + " with " +
//                professor + " by " + deliveryMethod + " " + this.getDescription();
//    }

    @Override
    public String toString() {
        return this.courseName + ": " + this.description;
    }

    /**
     * Get the Course code for this Course
     *
     * @return the course code
     */
    public String getSectionCode() {
        return sectionCode;
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
