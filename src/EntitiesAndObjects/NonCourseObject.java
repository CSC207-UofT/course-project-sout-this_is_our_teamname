package EntitiesAndObjects;

import java.time.LocalTime;

public class NonCourseObject {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String description;
    private final String date;
    private final String term;
    private final String type;

    /**
     * Construct an activity with time, location and a description.
     * @param startTime is the start time of the activity.
     * @param endTime is the end time of the activity.
     * @param theDescription is the description of the activity.
     * @param theDate is the weekday of the activity.
     * @param term is the term of the activity
     * @param type the type of the object
     */
    public NonCourseObject(LocalTime startTime,
                           LocalTime endTime,
                           String theDescription,
                           String theDate,
                           String term,
                           String type) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = theDescription;
        this.date = theDate;
        this.term = term;
        this.type = type;
    }

    /**
     * Get the type of the activity
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the start time for the activity
     * @return  startTime
     */
    public LocalTime getStartTime(){
        return this.startTime;
    }

    /**
     * Get the end time for the activity
     * @return  endTime
     */
    public LocalTime getEndTime() {
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
    public String toString(){
        //TODO: this is just a place holder!!!
        return "A(n) " + type + "object";
    }
}
