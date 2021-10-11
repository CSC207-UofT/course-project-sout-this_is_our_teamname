package TimeTableObjects;

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

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public abstract String toString();

}
