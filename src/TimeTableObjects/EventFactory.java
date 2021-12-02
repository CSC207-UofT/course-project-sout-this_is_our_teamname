package TimeTableObjects;

import Helpers.Constants;
import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.Task;

import java.time.LocalTime;
import java.util.Scanner;

public class EventFactory {
    /**
     * A helper method for scheduling events.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @param theLocation the location
     * @param theDate the date
     * @param term the term
     * @param type the type of object
     * @return event "cast" to the correct type.
     */
    public static Events getCorrectTimeTableObject(LocalTime startTime,
                                                   LocalTime endTime,
                                                   String theLocation,
                                                   String theDate,
                                                   String term,
                                                   String type) {
        // Creates the Activity
        switch (type){
            case Constants.ACTIVITY:
                return new Activity(startTime, endTime, theDate, term);
            case Constants.TASK:
                Task task = new Task(startTime, endTime, theDate, term);
                task.addToName(theLocation);
                return task;
            // ...
            // Add more types of events here!
            default:
                return null;
        }
    }
}
