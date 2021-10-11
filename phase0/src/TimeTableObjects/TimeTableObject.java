package TimeTableObjects;

import java.util.HashMap;
import java.util.Map;

public abstract class TimeTableObject {
    private final String startTime;
    private final String endTime;
    private final String location;
    private final String date;
    private final String description;

    public TimeTableObject(String startTime, String endTime, String theLocation, String theDate) {
        this.date = theDate;
        this.description = "";
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = theLocation;
    }

    public abstract String getStartTime();

    public abstract String getEndTime();

    public abstract String getDate();

    public abstract String getLocation();

    public abstract String getDescription();

    public abstract String toString();

}
