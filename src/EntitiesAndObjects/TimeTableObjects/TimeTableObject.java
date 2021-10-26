package EntitiesAndObjects.TimeTableObjects;
import java.sql.Time;

/**
 * TimeTableObject class is a class that can be stored in a TimeTable class.
 */
public abstract class TimeTableObject {
    private final Time startTime;
    private final Time endTime;
    private final String location;
    private final String date;
    private final String term;

    /**
     * Construct an activity with time, location and a description.
     * @param startTime is the start time of the activity.
     * @param endTime is the end time of the activity.
     * @param theLocation is the location of the activity.
     * @param theDate is the weekday of the activity.
     * @param term is the term of the activity
     */
    public TimeTableObject(Time startTime,
                           Time endTime,
                           String theLocation,
                           String theDate,
                           String term) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = theLocation;
        this.date = theDate;
        this.term = term;
    }

    /**
     * Get the start time for the activity
     * @return  startTime
     */
    public Time getStartTime(){
        return this.startTime;
    }

    /**
     * Get the end time for the activity
     * @return  endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Get the date for the activity
     * @return  date
     */
    public String getDate() {
        return date;
    }

    /**
     * Get the location for the activity
     * @return  location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Get the term for this activity
     *
     * @return the term for this activity
     */
    public String getTerm() {
        return term;
    }

    /**
     * Generate the String representation of the activity.
     * @return the string representation of the activity.
     */
    public abstract String toString();
}
