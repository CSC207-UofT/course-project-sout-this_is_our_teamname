package TimeTableContainers;

import Helpers.Constants;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.Events;

import java.util.ArrayList;

import java.util.*;


/**
 * aA manager that manages and holds different timetables.
 * === Private Attributes ===
 * timetables contains different timetables as values and their corresponding names as keys.
 */
public class TimeTableManager {
    private HashMap<String, TimeTable> timetables;

    /**
     * Creates a new TimeTableManager with no timetables.
     */
    public TimeTableManager() {
        this.timetables = new HashMap<>();
    }

    // =========================== Basic Functions =============================
    /**
     * Get an event from the user interface and schedule it to the corresponding timetable(s).
     *
     * Precondition: The event to be scheduled will not result in conflicts.
     *
     * @param event an Events passed from user interface
     */
    public boolean schedule(Events event) {
        // Some Constants
        int TERM = 0;
        int YEAR = 1;

        String timeTableName = event.getTerm();
        String[] splitTImeTableName = timeTableName.split("\\s+");

        // Since the format is Term Year, the term is at index 0 and year index 1
        String term = splitTImeTableName[TERM];
        String year = splitTImeTableName[YEAR];

        // If we want to schedule a year event, we want every term in that year
        // get scheduled.
        if (term.equals(Constants.YEAR)) {
            this.getTimetable(Constants.FALL + " " + year).schedule(event);
            this.getTimetable(Constants.WINTER + " " + year).schedule(event);
            return true;
        }
        // For all other events, schedule it in the proper timetable. Return
        // true if successful.
        else {
            return this.getTimetable(timeTableName).schedule(event);
        }
    }

    /**
     * Get an event from the user interface and schedule it to the corresponding timetable(s).
     *
     * @param event an Events passed from user interface
     */
    public boolean hasConflicts(Events event){
        // Some Constants
        int TERM = 0;
        int YEAR = 1;

        String timeTableName = event.getTerm();
        String[] splitTImeTableName = timeTableName.split("\\s+");

        // Since the format is Term Year, the term is at index 0 and year index 1
        String term = splitTImeTableName[TERM];
        String year = splitTImeTableName[YEAR];

        // If we want to schedule a year event, we want every term in that year
        // get scheduled.
        if (term.equals(Constants.YEAR)) {
            boolean theFall =
                    this.getTimetable(Constants.FALL + " " + year).checkConflicts(event);
            boolean theWinter =
                    this.getTimetable(Constants.WINTER + " " + year).checkConflicts(event);
            return theFall && theWinter;
        }
        // For all other events, schedule it in the proper timetable. Return
        // true if successful.
        else {
            return this.getTimetable(timeTableName).checkConflicts(event);
        }
    }

    /**
     * Add a new empty TimeTable with the given name.
     *
     * @param name given name the of timetable
     * @return true if we successfully add a new TimeTable, else false.
     */
    public boolean addTimeTable(String name) {
        if (this.timetables.containsKey(name)) {
            return false;
        } else {
            timetables.put(name, new TimeTable());
            return true;
        }
    }

    /**
     * Remove an existing TimeTable with the given name. If the timetable doesn't exists,
     * create a new one with the given name.
     *
     * @param name the given name of timetable
     * @return true if we successfully remove a TimeTable, else false.
     */
    public boolean removeTimeTable(String name) {
        if (this.timetables.containsKey(name)) {
            timetables.remove(name);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Presents the TimeTableManager in string
     *
     * @return a string representation of the TimeTableManager
     */
    public String toString() {
        LinkedHashMap<String, String> times = new LinkedHashMap<>();
        for (String term : this.timetables.keySet()) {
            times.put(term, this.timetables.get(term).toString());
        }
        return times.toString();
    }

    //============================ Getters =====================================

    /**
     * Get the terms of the timetables
     *
     * @return the set of terms of the timetables
     */
    public Set<String> getTerms() {
        return this.timetables.keySet();
    }

    /**
     * Returns an array of timetables with all the timetables.
     *
     * @return an array of timetables with all the timetables
     */
    public TimeTable[] getAllTimeTables() {
        TimeTable[] theTimes =
                new TimeTable[this.timetables.keySet().size()];
        int i = 0;
        for (String term : this.timetables.keySet()) {
            theTimes[i] = this.timetables.get(term);
            i++;
        }
        return theTimes;
    }

    /**
     * Get the timetable object for the given name.
     *
     * @param name the name for the wanted timetable.
     * @return the corresponding TimeTable object.
     */
    public TimeTable getTimetable(String name) {
        if (!this.timetables.containsKey(name)) {
            timetables.put(name, new TimeTable());
        }
        return timetables.get(name);
    }

    public TimeTableManager get_copy(){
        TimeTableManager copy = new TimeTableManager();
        copy.setTimetables(this.timetables);
        return copy;
    }

    public void setTimetables(HashMap<String, TimeTable> other){
        // To prevent aliasing
        this.timetables = new HashMap<>(other);
    }

    /**
     * Get all CourseSections in this TimeTable
     * Precondition: All CourseSections in this TimeTable occur between the hours of 07:00 to 22:00 on weekdays.
     *
     * @return An ArrayList of all the CourseSections in this TimeTable
     */
    public ArrayList<CourseSection> getCourses() {
        ArrayList<CourseSection> courses = new ArrayList<>();
        for (TimeTable timeTable : this.getAllTimeTables())
            for (String day: timeTable.getCalendar().keySet()) {
                if (!Objects.equals(day, Constants.SATURDAY) && !Objects.equals(day, Constants.SUNDAY)) {
                    for (int hour=7; hour<23; hour++) {
                        Events hourEvent = timeTable.getCalendar().get(day)[hour];
                        if (hourEvent instanceof CourseSection) {
                            courses.add((CourseSection) hourEvent);
                        }
                    }
                }
            }
        return courses;
    }
}
