 package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;

 //TODO docstrings,remove location param
public class Activity extends Events {
    private final String description;

    public Activity(LocalTime theStartTime,
                    LocalTime theEndTime,
                    String theLocation,
                    String theDate,
                    String term,
                    String description) {
        super(theStartTime, theEndTime, theLocation, theDate, term);
        this.description = description + this.getDescription();
    }


    @Override
    public String toString() {
        return this.getStartTime() + " - " + this.getEndTime() + ": " +
                this.description;
    }
}
