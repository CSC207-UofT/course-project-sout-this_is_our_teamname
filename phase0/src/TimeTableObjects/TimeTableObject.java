package TimeTableObjects;

/**
 * TimeTableObject class is an abstract class that can be stored in a TimeTable class.
 */
public abstract class TimeTableObject {
    private final String startTime;
    private final String endTime;
    private final String location;
    private final String date;
    private final String description;

    /**
     * Construct an activity with time, location and a description.
     * @param theDate is the weekday of the activity.
     * @param startTime is the start time of the activity.
     * @param endTime is the end time of the activity.
     * @param theLocation is the location of the activity.
     */
    //TODO: How do we add description? Here in the constructor or a seperate method? or are we overriding in subclass?
    public TimeTableObject(String startTime, String endTime, String theLocation, String theDate) {
        this.date = theDate;
        this.description = "";
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = theLocation;
    }

    /**
     * Get the start time for the activity
     * @return  startTime
     */
    public String getStartTime(){
        return this.startTime;
    }

    /**
     * Get the end time for the activity
     * @return  endTime
     */
    public String getEndTime() {
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
     * Get the location for the activity
     * @return  location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Generate the String representation of the activity.
     * @return the string representation of the activity.
     */
    public String toString(){
    //TODO: this is just a place holder!!!
        return "placeHolder";
    }
}
