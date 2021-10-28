package EntitiesAndObjects.TimeTableObjects;
import java.sql.Time;

/**
 * Events class is a class that can be stored in a TimeTable class.
 */
public abstract class Events {
    private final Time startTime;
    private final Time endTime;
    private final String description;
    private final String date;
    private final String term;
//TODO fix and add description parameter here, remove location, need a getter for description.
    /**
     * Construct an activity with time, location and a description.
     * @param startTime is the start time of the activity.
     * @param endTime is the end time of the activity.
     * @param description is the description of the activity.
     * @param theDate is the weekday of the activity.
     * @param term is the term of the activity
     */
    public Events(Time startTime,
                  Time endTime,
                  String description,
                  String theDate,
                  String term) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
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
     * Get the description for the activity
     * @return  description
     */
    public String getDescription() {
        return description;
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
