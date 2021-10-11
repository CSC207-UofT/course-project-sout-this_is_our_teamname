package TimeTableObjects;

public abstract class TimeTableObject {
    private final String time;
    private final String location;
    private final String date;
    private final String description;

    public TimeTableObject(String theTime, String theLocation, String theDate) {
        this.date = theDate;
        this.description = "";
        this.time = theTime;
        this.location = theLocation;
    }

    public abstract String getTime();

    public abstract String getLocation();

    public abstract String toString();

}
