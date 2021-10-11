import java.util.HashMap;
import java.util.Map;

package TimeTableObjects;

public abstract class TimeTableObject {
    private Hashmap timeLocation;

    public TimeTableObject(Hashmap timeLocation) {
        this.timeLocation = timeLocation
    }

    abstract void getTimeLocation()

}
