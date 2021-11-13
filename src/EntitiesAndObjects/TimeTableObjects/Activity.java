 package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;

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
      * Construct a activity with time, location and a name.
      * @param theStartTime is the start time of the activity.
      * @param theEndTime is the end time of the activity.
      * @param theLocation is the location of the activity.
      * @param theDate is the weekday of the activity.
      * @param term is the term of the activity
      * @param name is the name of the activity.
      */
    public Activity(LocalTime theStartTime,
                    LocalTime theEndTime,
                    String theLocation,
                    String theDate,
                    String term,
                    String name) {
        super(theStartTime, theEndTime, theLocation, theDate, term);
        this.description = name + " " + this.getDescription();
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
