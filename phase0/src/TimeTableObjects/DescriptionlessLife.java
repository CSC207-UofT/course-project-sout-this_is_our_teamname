package TimeTableObjects;

import TimeTableObjects.TimeTableObject;

import java.sql.Time;

public class DescriptionlessLife extends TimeTableObject{
    //TODO Missing docstrings
    public DescriptionlessLife(Time theStartTime,
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
