 package TimeTableObjects.EventObjects;

import TimeTableObjects.Events;
import TimeTableObjects.Interfaces.Reconstructable;

import java.time.LocalTime;
import java.util.ArrayList;

 /**
  *
  * Activity is an extracurricular or social Events object.
  *
  * === Private Attributes ===
  * description: contains all the other info about the activity
  */
public class Activity extends Events implements Reconstructable {
    /**
     * Construct an activity with time and a description.
     * @param theStartTime is the start time of the activity.
     * @param theEndTime is the end time of the activity.
     * @param theDate is the weekday of the activity.
     * @param term is the term of the activity.
     */
    public Activity(LocalTime theStartTime,
                    LocalTime theEndTime,
                    String theDate,
                    String term) {
        super("", theStartTime, theEndTime, theDate, term, "");
    }

     /**
      * reconstruct takes an Events object and returns an Arraylist based on the non-time/date attributes.
      */
     @Override
     public ArrayList<String> reconstruct() {
         ArrayList<String> list = new ArrayList<>(4);
         // index 0: class name, index 1: Activity name, index 2: description
         list.add(0, this.getClass().getSimpleName());
         list.add(1, this.getName());
         list.add(2, " ");
         list.add(3, this.description);
         return list;
     }

     /**
     * Generate the string representation of the activity.
     * @return the string representation of the activity.
     */
    @Override
    public String toString() {
        return this.getName() + ": " + this.description;
    }
}
