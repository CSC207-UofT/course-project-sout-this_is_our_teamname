package TimeTableObjects.EventObjects;

import TimeTableObjects.Events;
import TimeTableObjects.Interfaces.Reconstructable;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This is a specific time interval of a specific section for a course.
 *
 * === Private Attributes ===
 * waitlist is whether this course is waitlisted or not.
 * courseName is the course code of the course section
 * sectionCode is the section code of the course section
 * description contains all the other info of the course section
 */
public class CourseSection extends Events  implements Reconstructable {
    private final boolean waitlist;
    private final String courseName;
    private final String sectionCode;
    private String description;

    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     *
     * @param CourseName The CourseName
     * @param startTime The start time of this section.
     * @param endTime The end time of the section
     * @param theDate The date of the section
     * @param term The term and year for this course e.g. Fall 2021
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
     * reconstruct takes an Events object and returns an Arraylist based on the non-time/date attributes.
     */
    @Override
    public ArrayList<String> reconstruct() {
        ArrayList<String> list = new ArrayList<>(4);
        // index 0: class name, index 1: CourseSection name, index 2: section code, index 3: description
        list.add(0, this.getClass().getSimpleName());
        list.add(1, this.courseName);
        list.add(2, this.sectionCode);
        list.add(3, this.description);
        return list;
    }

    /**
     * Set the description of the CourseSection object
     *
     * @param info is the description to be added to the CourseSection object
     */
    public void setDescription(String info) {
        this.description = info;
    }

    /**
     * Get the section for this Course
     *
     * @return the section
     */
    public String getSectionCode() {
        return sectionCode;
    }

    /**
     * Get the Course Code for this Course
     *
     * @return the course code
     */
    public String getCourseName() {return courseName; }

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
