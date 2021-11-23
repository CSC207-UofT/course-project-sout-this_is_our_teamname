package TimeTableObjects.EventObjects;

import TimeTableObjects.Events;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Task is a reminder Events object.
 */
public class Task extends Events {
    /**
     * Construct a task with time, location and a description.
     * @param theStartTime is the start time of the task.
     * @param theEndTime is the end time of the task.
     * @param theDate is the weekday of the task.
     * @param term is the term of the task
     */
    public Task(LocalTime theStartTime,
                LocalTime theEndTime,
                String theDate,
                String term) {
        super(theStartTime, theEndTime, theDate, term);
    }

    /**
     *  Generate the string representation of the task.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        if (this.getName() == null) {
            return "N/A";
        }
        else{
            return this.getName();
        }
    }
}