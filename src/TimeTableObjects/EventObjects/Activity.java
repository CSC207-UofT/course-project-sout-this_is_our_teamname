 package TimeTableObjects.EventObjects;

import TimeTableObjects.Events;

import java.time.LocalTime;

 /**
  * Activity is an extracurricular or social Events object.
  *
  * === Private Attributes ===
  * description contains all the other info about the activity
  */
public class Activity extends Events {
    private final String description;

    /**
     * Construct a activity with time and a description.
     * @param theStartTime is the start time of the activity.
     * @param theEndTime is the end time of the activity.
     * @param theDate is the weekday of the activity.
     * @param term is the term of the activity.
     * @param description is the description of the activity.
     */
    public Activity(LocalTime theStartTime,
                    LocalTime theEndTime,
                    String theDate,
                    String term,
                    String description) {
        super(theStartTime, theEndTime, theDate, term);
        this.description = description;
    }

    /**
     *  Generate the string representation of the activity.
     * @return the string representation of the activity.
     */
    @Override
    public String toString() {
        return this.description;
    }
}
