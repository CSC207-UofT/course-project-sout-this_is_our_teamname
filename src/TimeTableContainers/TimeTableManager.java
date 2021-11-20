package TimeTableContainers;

import Helpers.ConflictException;
import Helpers.Constants;
import Helpers.InvalidInputException;
import TimeTableObjects.Events;

// Importing HashMap class
import java.util.*;

/**
 * Stores all TimeTables for each term.
 *
 * === Private Attributes ===
 * timetables: The map of each term mapped to its corresponding TimeTable
 */
public class TimeTableManager {
    private HashMap<String, TimeTable> timetables;
    /**
     * Creates a new TimeTableManager with two default TimeTables for Fall and Winter.
     */
    public TimeTableManager() {
        this.timetables = new HashMap<>(){{
            put(Constants.FALL, new TimeTable());
            put(Constants.WINTER, new TimeTable());
        }};
    }

    /**
     * Construct a TimeTableManager with given timetablesSave
     * @param timetablesSave is the saved calendar
     */
    public TimeTableManager(HashMap<String, LinkedHashMap<String, Events[]>> timetablesSave) {
        HashMap<String, TimeTable> tempCalender = new HashMap<>();
        for (String term : timetablesSave.keySet()) {
            TimeTable calender = new TimeTable(timetablesSave.get(term));
            tempCalender.put(term, calender);
        }
        this.timetables = tempCalender;
    }

    /**
     * Add a new empty TimeTable with the given term.
     * @param term given term the of timetable
     * @return true if we successfully add a new TimeTable, else false.
     */
    public boolean addTimeTable(String term) {
        if (this.timetables.containsKey(term)) {
            return false;
        }
        else{
            timetables.put(term, new TimeTable());
            return true;
        }
    }

    /**
     * Remove an existing TimeTable with the given term.
     * @param term given term the of timetable
     * @return true if we successfully remove a TimeTable, else false.
     */
    public boolean removeTimeTable(String term) {
        if (this.timetables.containsKey(term)) {
            timetables.remove(term);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Get the terms of the timetables
     * @return the set of terms of the timetables
     */
    public Set<String> getTerms() {
        return this.timetables.keySet();
    }

    /**
     * Get the timetable object for the given term.
     *
     * @return the corresponding TimeTable object.
     */
    public TimeTable getTimetable(String term) {
        if (this.timetables.containsKey(term)) {
            return timetables.get(term);
        }
        else{
            //TODO exceptions later
            return null;
        }
    }

    /**
     * Returns a copy of TimeTableManager (not alias)
     * @return the copy of the TimeTableManager
     */
    public HashMap<String, LinkedHashMap<String, Events[]>> getCopy() {
        HashMap<String, LinkedHashMap<String, Events[]>> copy = new LinkedHashMap<>();
        for (String term : this.timetables.keySet()) {
            copy.put(term, this.timetables.get(term).getCopy());
        }
        return copy;
    }

    /**
     * Sets the TimeTableManager with given TimeTableManager
     * @param manager the given TimeTableManger
     */
    public void setTimetables(TimeTableManager manager) {
        this.timetables = manager.timetables;
    }

    /**
     * Get an event from the user interface and schedule it to the corresponding timetable(s).
     * @param event an Events passed from user interface
     */
    public void schedule(Events event) throws ConflictException {
        if (event.getTerm().equals(Constants.FALL)){
           boolean conflict = timetables.get(Constants.FALL).schedule(event);
           if (!conflict){throw new ConflictException();}

        }
        else if (event.getTerm().equals(Constants.WINTER)){
            boolean conflict = timetables.get(Constants.WINTER).schedule(event);
            if (!conflict){throw new ConflictException();}
        }
        else{
            boolean conflict1 = timetables.get(Constants.FALL).schedule(event);
            boolean conflict2 = timetables.get(Constants.WINTER).schedule(event);
            if (!conflict1 && !conflict2){throw new ConflictException();}
        }
    }

    /**
     * Returns an array of timetables with all the timetables.
     *
     * @return an array of timetables with all the timetables
     */
    public TimeTable[] getAllTimeTables(){
        TimeTable[] theTimes =
                new TimeTable[this.timetables.keySet().size()];
        int i = 0;
        for (String term : this.timetables.keySet()){
            theTimes[i] = this.timetables.get(term);
            i++;
        }
        return theTimes;
    }

    public String toString() {
        LinkedHashMap<String, String> times = new LinkedHashMap<>();
        for (String term : this.timetables.keySet()) {
            times.put(term, this.timetables.get(term).toString());
        }
        return times.toString();
    }
}
