package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;


//TODO change param here with description, add boolean param wait list.
public class CourseSection extends Events implements java.lang.Comparable<CourseSection> {
    private final String description;

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
     */
    public CourseSection(LocalTime startTime, LocalTime endTime, String location,
                         String theDate, String term, String code, String professor,
                         String faculty, String deliveryMethod) {
        super(startTime, endTime, location, theDate, term);
        this.description = code + " of " + faculty + " with " + professor + " by " + deliveryMethod +
                this.getDescription();
    }

    @Override
    public String toString() {
        return this.getStartTime() + " - " + this.getEndTime() + ": " +
                this.description;
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

//    public static void main(String[] args) {
//        LocalTime time = LocalTime.of(6,30);
//        LocalTime time2 = LocalTime.of(6,30);
//        LocalTime time3 = LocalTime.of(7,30);
//        LocalTime time4 = LocalTime.of(5,30);
//        System.out.println(time);
//        System.out.println(time.compareTo(time4
//        ));
//    }
}
