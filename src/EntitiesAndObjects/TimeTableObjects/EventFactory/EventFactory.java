package EntitiesAndObjects.TimeTableObjects.EventFactory;

import EntitiesAndObjects.TimeTableObjects.Events;

/**
 * This is the abstract factory for event creation.
 */
public abstract class EventFactory {
    /** Creates an event
     */
    public abstract Events createEvent();
}
