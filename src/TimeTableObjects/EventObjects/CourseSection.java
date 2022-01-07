package TimeTableObjects.EventObjects;

import TimeTableObjects.Events;
import TimeTableObjects.Interfaces.Reconstructable;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 *
 * This is a specific time interval of a specific section for a course.
 *
 * === Private Attributes ===
 * courseName: The course code of the course section
 * sectionCode:The section code of the course section
 * description: Contains all the other info of the course section
 */
public class CourseSection extends Events  implements Reconstructable {
    private String sectionCode;

    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     * @param startTime The start time of this section.
     * @param endTime The end time of the section
     * @param theDate The date of the section
     * @param term The term and year for this course e.g. Fall 2021
     */
    public CourseSection(LocalTime startTime,
                         LocalTime endTime,
                         String theDate,
                         String term) {
        super("", startTime, endTime, theDate, term, "");
        this.sectionCode = null;
    }

    /**
     * reconstruct takes an Events object and returns an Arraylist based on the non-time/date attributes.
     */
    @Override
    public ArrayList<String> reconstruct() {
        ArrayList<String> list = new ArrayList<>(4);
        // index 0: class name, index 1: CourseSection name, index 2: section code, index 3: description
        list.add(0, this.getClass().getSimpleName());
        list.add(1, this.name);
        list.add(2, this.sectionCode);
        list.add(3, this.description);
        return list;
    }

    /**
     *
     * @return a string
     */
    @Override
    public String toString() {
        return this.name + ": " + this.description;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
