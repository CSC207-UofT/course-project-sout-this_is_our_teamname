import java.util.HashMap;
import java.util.Map;

package TimeTableObjects;

public abstract class TimeTableObject {
    private String time;
    private String date;
    private String location;
    private String description;

    public TimeTableObject(Hashmap timeLocation) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.description = '';
    }

    abstract void getTime();

    abstract void getDate();

    abstract void getLocation();

    abstract void getDescription();

    abstract void toString();

}
