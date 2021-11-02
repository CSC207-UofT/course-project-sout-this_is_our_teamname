package EntitiesAndObjects.TimeTableObjects.EventFactory;

import EntitiesAndObjects.TimeTableObjects.Events;
import EntitiesAndObjects.TimeTableObjects.Task;

import java.time.LocalTime;

public class TaskFactory extends EventFactory {

    @Override
    public Events createEvent() {
        return null;
    }

    //Overloads createEvent
    //TODO check after parameter changes
    /** Creates a Task
     * @return a Task object
     */
    public Task createEvent(String theDate,
                            String term,
                            String description) {
        LocalTime theStartTime = LocalTime.of(8,0,0);
        LocalTime theEndTime = LocalTime.of(9,0,0);
        return new Task(theStartTime, theEndTime, theDate, term);
        //TODO add description
    }
}
