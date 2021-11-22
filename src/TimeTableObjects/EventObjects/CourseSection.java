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
public class CourseSection extends Events {
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
     *
     * @return a string
     */
    @Override
    public String toString() {
        return this.courseName + ": " + this.description;
    }
}
