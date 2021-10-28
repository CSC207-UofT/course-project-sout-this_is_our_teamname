 package EntitiesAndObjects.TimeTableObjects;

import java.sql.Time;

public class Activity extends Events {

    public Activity(Time theStartTime,
                    Time theEndTime,
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
