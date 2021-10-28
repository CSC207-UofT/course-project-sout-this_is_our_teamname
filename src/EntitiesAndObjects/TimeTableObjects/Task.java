package EntitiesAndObjects.TimeTableObjects;

import java.time.LocalTime;

/**
 * Task class is a class that can be stored in a TimeTable class. It acts as a reminder.
 */
public class Task extends Events {

    /**
     * Construct a TimeTable Task for the given start time, end time, description,
     * date and term.
     *
     * @param theStartTime The start time for this task
     * @param theEndTime The end time for this task
     * @param theDescription The description for this task
     * @param theDate The date for this task
     * @param term The term this task belongs to
     */
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
