package TimeTableContainers;

import TimeTableObjects.Events;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Originator {
    private HashMap<String, LinkedHashMap<String, Events[]>> timetables;

    /**
     * Set the value of the TimeTableManager
     * @param newCalender is the TimeTableManager to be saved
     */
    public void setCalender(TimeTableManager newCalender) {
        this.timetables = newCalender.getCopy();
    }

    /**
     * Creates a new memento with the given TimeTableManager
     * @return the new TimeTableManager
     */
    public TimeTableManager storeInMemento() {
        return new TimeTableManager(timetables);
    }

    /**
     * Gets the TimeTableManager currently stored in memento
     * @param memento the TimeTableManager stored
     * @return the TimeTableManager currently stored in memento
     */
    public HashMap<String, LinkedHashMap<String, Events[]>> restoreFromMemento(TimeTableManager memento) {
        timetables = memento.getCopy();
        return timetables;
    }

}
