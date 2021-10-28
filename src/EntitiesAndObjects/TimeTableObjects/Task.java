package EntitiesAndObjects.TimeTableObjects;

import java.sql.Time;

public class Task extends Events {
    public Task(Time theStartTime,
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
