package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;

//TODO 1. docstrings
//TODO 2. remove location and add description in param, add getter for the latter.
public class Task extends Events {
    public Task(LocalTime theStartTime,
                LocalTime theEndTime,
                String theLocation,
                String theDate,
                String term) {
        super(theStartTime, theEndTime, theLocation, theDate, term);
    }

    @Override
    public String toString() {
        return this.getStartTime() + " - " + this.getEndTime() + ": " +
                this.getDescription();
    }
}
