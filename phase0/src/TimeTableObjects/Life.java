// package TimeTableObjects;

import TimeTableObjects.TimeTableObject;

import java.sql.Time;

public class Life extends TimeTableObject{
    public Life(Time theStartTime,
                Time theEndTime,
                String theLocation,
                String theDate,
                String term) {
        super(theStartTime, theEndTime, theLocation, theDate, term);
    }

    @Override
    public String toString() {
        //TODO Fix this!
        return "";
    }
    // Add code here!
}
