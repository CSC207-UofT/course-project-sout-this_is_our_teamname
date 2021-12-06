package TimeTableObjects;
import TimeTableObjects.Interfaces.Reconstructable;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * TODO REMOVE THIS SENTENCE
 *
 * Events class is a class that can be stored in a TimeTable class.
 *
 * === Private Attributes ===
 * startTime is the starting time of the event
 * endTime is the ending time of the event
 * date is the weekday of the event
 * term is which school term and year the timetable belongs to e.g. Winter 2021, Year 2021
 * name is the name or a short description of the Event
 */
public abstract class Events implements Reconstructable {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String date;
    private final String term;
    private String name;


    /**
     * Construct an event with time and a description.
     *
     * @param startTime is the start time of the event.
     * @param endTime   is the end time of the event.
     * @param theDate   is the weekday of the event.
     * @param term      is the term of the event.
     */
    public Events(LocalTime startTime,
                  LocalTime endTime,
                  String theDate,
                  String term) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = theDate;
        this.term = term;
        this.name = "";
    }

    /**
     * Reconstruct takes an Events object and returns an Arraylist based on the non-time/date attributes.
     */
    public abstract ArrayList<String> reconstruct();

    /**
     * If the Events object has an empty string for name attribute,
     * then set name to: "Unnamed" + Activity/Task/CourseSection
     */
    public void nameIt(){
        this.setName("Unnamed" + this.getClass().getSimpleName());
    }

    /**
     * Set the name for the event.
     *
     * @param name is going to be the name of the Events object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add to the name of the event
     *
     * @param info is going to be added to the name of the Events object
     */
    public void addToName(String info) {
        this.name += info;
    }

    /**
     * Get the start time for the event
     *
     * @return startTime
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Get the end time for the event
     *
     * @return endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Get the date for the event
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Get the name for the event,note: task's name will be name + " at " + task's location
     *
     * @return name
     */
    public String getName() {
        return name;
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
     *
     * @return the string representation of the event.
     */
    public abstract String toString();
}
