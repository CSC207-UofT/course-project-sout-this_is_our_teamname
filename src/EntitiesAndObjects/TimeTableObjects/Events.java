package EntitiesAndObjects.TimeTableObjects;
import java.time.LocalTime;
/**
 * Events class is a class that can be stored in a TimeTable class.
 */
public abstract class Events {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String date;
    private final String term;
    private String description;

    //TODO FIX constructor parameters(and subclasses) after everything works.
    /**
     * Construct an event with time and a description.
     * @param startTime is the start time of the event.
     * @param endTime is the end time of the event.
     * @param theDate is the weekday of the event.
     * @param term is the term of the event.
     */
    public Events (LocalTime startTime,
                   LocalTime endTime,
                   String theDate,
                   String term) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = theDate;
        this.term = term;
        this.description = "";
    }

    /**
     * Construct an event with time, location and a description.
     * @param startTime is the start time of the event.
     * @param endTime is the end time of the event.
     * @param theLocation is the location of the event.
     * @param theDate is the weekday of the event.
     * @param term is the term of the event.
     */
    public Events(LocalTime startTime,
                  LocalTime endTime,
                  String theLocation,
                  String theDate,
                  String term) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = theDate;
        this.term = term;
        this.description = "at " + theLocation;
    }
    /**
     * Get the start time for the event
     * @return  startTime
     */
    public LocalTime getStartTime(){
        return this.startTime;
    }
    /**
     * Get the end time for the event
     * @return  endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }
    /**
     * Get the date for the event
     * @return  date
     */
    public String getDate() {
        return date;
    }
    /**
     * Get the description for the event
     * @return  description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Get the term for this event
     *
     * @return term
     */
    public String getTerm() {
        return term;
    }
    /**
     * Generate the String representation of the event.
     * @return the string representation of the event.
     */
    public abstract String toString();


    //TODO need to rethink about how to add to description. since we should be able to change it anytime.
    /**
     * Change description for the event. (Currently an one time deal)
     */
    public void addDescription(String info) {
        this.description = info;
    }

}