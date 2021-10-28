 package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;

public class Activity extends Events {

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
