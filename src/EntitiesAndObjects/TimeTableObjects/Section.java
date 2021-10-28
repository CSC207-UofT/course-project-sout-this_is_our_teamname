package EntitiesAndObjects.TimeTableObjects;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Section extends TimeTableObject implements java.lang.Comparable<Section> {
    private final String code;
    //TODO delete attributes after parameter change
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
     * Compare two sections to check if they overlap in time.
     *
     * @param other The second section to be checked
     * @return -1 if they overlap, 1 otherwise.
     */
    @Override
    public int compareTo(Section other) {
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
