package EntitiesAndObjects.TimeTableObjects.EventFactory;

import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.CourseSection;
import EntitiesAndObjects.TimeTableObjects.Events;
import EntitiesAndObjects.TimeTableObjects.Task;

/**
 * This is the abstract factory for event creation.
 */
public abstract class EventFactory {
    public static Events getEvents(String type){
        switch(type){
            case "Task":
                return new Task();
            case "Activity":
                return new Activity();
            case "CourseSection":
                return new CourseSection();
            default :
                return null;
        }
    }
}
}
