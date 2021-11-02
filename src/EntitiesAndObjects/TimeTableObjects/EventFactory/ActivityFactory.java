package EntitiesAndObjects.TimeTableObjects.EventFactory;

import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.Events;

import java.time.LocalTime;

public class ActivityFactory extends EventFactory{

    @Override
    public Events createEvent() {
        return null;
    }

    //Overloads createEvent
    //TODO check after parameter changes
    /** Creates an activity
     * @return an Activity object
     */
    public Activity createEvent(LocalTime theStartTime,
                                LocalTime theEndTime,
                                String theDate,
                                String term,
                                String description) {
        return new Activity(theStartTime, theEndTime, theDate, term, description);
        //TODO add description
    }
}
