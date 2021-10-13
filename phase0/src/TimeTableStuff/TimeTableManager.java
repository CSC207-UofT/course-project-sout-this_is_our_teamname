package TimeTableStuff;

import TimeTableObjects.CourseStuff.Course;
import TimeTableObjects.CourseStuff.Section;

import TimeTableObjects.TimeTableObject;
import ConstantsAndExceptions.Constants;
import TimeTableObjects.Life;

// Importing HashMap class
import java.util.ArrayList;
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

    /**
     * Get the course from interface and schedule it to the corresponding timetable(s).
     *
     * @param c a Course object passed from user interface
     */
    public void schedule(Course c) {
        ArrayList<Section> list = c.split();
        if (c.getTerm().isequal(Contants.FALL)) {
            for (Section x : list) {
                timetables.get("Fall").schedule(x);
            }
        } if (c.getTerm().isequal(Contants.WINTER)) {
            for (Section x : list) {
                timetables.get("Winter").schedule(x);
            }
        } else {
            for (Section x : list) {
                timetables.get("Fall").schedule(x);
                timetables.get("Winter").schedule(x);
            }
        }
    }

    /**
     * Get the life from interface and schedule it to the corresponding timetable(s).
     *
     * @param l a Life object passed from user interface
     */
    public void schedule(Life l) {
        timetables.get("Fall").schedule(l);
        timetables.get("Winter").schedule(l);
    }

}
