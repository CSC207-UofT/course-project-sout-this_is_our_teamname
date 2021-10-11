package TimeTableStuff;

import UserInterface.UserInterface;

// Importing HashMap class
import java.util.HashMap;

public class TimeTableManager {
    private HashMap<String, TimeTable> timetables;

    /**
     * Creates a new TimeTableManager with two default TimeTables for Fall and Winter.
     */
    public TimeTableManager() {
        this.timetables = new HashMap<String, TimeTable>(){{
            put("Fall", new TimeTable());
            put("Winter", new TimeTable());
        }};
    }

    /**
     * Add a new empty TimeTable with the given term.
     * @param term given term the of timetable
     * @return true if we successfully add a new TimeTable, else false.
     */
    public boolean addTimeTable(String term) {
        timetables.put(term, new TimeTable());
        // TODO Failed to add a new timetable.
        return true;
    }

    /**
     * Remove an existing TimeTable with the given term.
     * @param term given term the of timetable
     * @return true if we successfully remove a TimeTable, else false.
     */
    public boolean removeTimeTable(String term) {
        timetables.remove(term, new TimeTable());
        // TODO Failed to remove a new timetable.
        return true;
    }

    /**
     * Get the timetable object for the given term.
     *
     * @return the corresponding TimeTable object.
     */
    public TimeTable getTimetable(String term) {
        return timetables.get(term);
        // TODO What if the timetable is not found.
    }

}
