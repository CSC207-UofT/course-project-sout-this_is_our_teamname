package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;


public class Task extends Events {
    public Task(LocalTime theStartTime,
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
