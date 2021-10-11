
package TimeTableObjects;

import java.util.HashMap;
import java.util.Map;

public abstract class TimeTableObject {
    private String time;
    private String location;

    public TimeTableObject(Hashmap timeLocation) {
        this.time = time;
        this.location = location;
    }

    abstract void getTime();

    abstract void getLocation();

    public abstract String toString();

}
