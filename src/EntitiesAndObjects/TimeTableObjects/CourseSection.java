package EntitiesAndObjects.TimeTableObjects;

import java.sql.Time;

public class CourseSection extends Events implements java.lang.Comparable<CourseSection> {
    private final String code;


    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     *
     * @param startTime The start time of this section.
     * @param endTime The end time of the section
     * @param description The description of this section
     * @param theDate The date of the section
     * @param term The term for this course
     * @param code The code for this course
     */
    public CourseSection(Time startTime, Time endTime, String description,
                         String theDate, String term, String code) {
        super(startTime, endTime, description, theDate, term);
        this.code = code;
    }

    @Override
    public String toString() {
        return this.getStartTime() + " - " + this.getEndTime() + ": " +
                this.code + " : " + this.getDescription();
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
