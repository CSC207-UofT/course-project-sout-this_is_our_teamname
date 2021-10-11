
package TimeTableObjects;

import java.util.HashMap;
import java.util.Map;

public abstract class TimeTableObject {
    private String startTime;
    private String endTime;
    private String date;
    private String location;
    private String description;

    public TimeTableObject(Hashmap timeLocation) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.location = location;
        this.description = '';
    }

    abstract void getStartTime();

    abstract void getEndTime();

    abstract void getDate();

    abstract void getLocation();

    abstract void getDescription();

    abstract void toString();

}
