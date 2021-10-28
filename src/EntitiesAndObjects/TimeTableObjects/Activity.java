 package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;

 /**
  * Activity class is a class that can be stored in a TimeTable class.
  */
public class Activity extends Events {

    /**
     * Consyruct a TimeTable activity for the given start time, end time, description,
     * date and term.
     *
     * @param theStartTime The start time of this activity
     * @param theEndTime The end time of this activity
     * @param theDescription The description of this activity
     * @param theDate The date of this activity
     * @param term The term this activity belongs in
     */
    public Activity(LocalTime theStartTime,
                    LocalTime theEndTime,
                    String theDescription,
                    String theDate,
                    String term) {
        super(theStartTime, theEndTime, theDescription, theDate, term);
    }


    @Override
    public String toString() {
        return this.getStartTime() + " - " + this.getEndTime() + ": " +
                this.getDescription();
    }
}
