package TimeTableContainers;

import Helpers.Constants;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;

import java.util.ArrayList;

// Importing HashMap class
import java.util.*;


/**
 * A manager that manages and holds different timetables.
 * === Private Attributes ===
 * timetables contains different timetables as values and their corresponding names as keys.
 */
public class TimeTableManager {
    private final HashMap<String, TimeTable> timetables;

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
    public boolean checkConflicts(Events event){
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
     * Reformats a given timetable from a hashmap of Events objects to a
     * hashmap of strings.
     *
     * - The keys are the weekdays.
     * - The value is an arraylist of arraylists of strings.
     * - The outer-layer of the arraylist contains 25 elements, index 0 to 23
     *      corresponds to hours of the day,
     * - each hour contains an arraylist of strings representing the
     *      corresponding Event object scheduled in the timetable
     * - index 24 contains an arraylist of task objects which are all day
     * events.
     *
     *  TODO This class is never used
     *
     * @param timetable is a timetable object
     * @return hashmap of strings representation of the  given timetable.
     */
    public LinkedHashMap<String, ArrayList<ArrayList<String>>> reformat(TimeTable timetable) {
        // creates the value of hashmap: hoursOfDay is an empty list with 24
        // null elements
        ArrayList<ArrayList<String>> hoursOfDay = new ArrayList<>(24);
        for (int i = 0; i < 25; i++) {
            hoursOfDay.add(i, null);
        }
        //Creates the hashmap with hoursOfDay as values (not aliased).
        LinkedHashMap<String, ArrayList<ArrayList<String>>> stringCalendar = new LinkedHashMap<>() {{
            put(Constants.MONDAY, new ArrayList<>(hoursOfDay));
            put(Constants.TUESDAY, new ArrayList<>(hoursOfDay));
            put(Constants.WEDNESDAY, new ArrayList<>(hoursOfDay));
            put(Constants.THURSDAY, new ArrayList<>(hoursOfDay));
            put(Constants.FRIDAY, new ArrayList<>(hoursOfDay));
            put(Constants.SATURDAY, new ArrayList<>(hoursOfDay));
            put(Constants.SUNDAY, new ArrayList<>(hoursOfDay));
        }};
        //add string representations of non-Task Events objects to stringCalendar
        reformatNonTaskEvents(timetable, stringCalendar);
        //add string representations of Task objects to stringCalendar
        reformatTaskEvents(timetable, stringCalendar);
        return stringCalendar;
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

    //============================ Helpers =====================================

    /**
     * A helper for the reformat method, uses a TimeTable (linkedhashmap of Events objectrs) to add
     * string representations of Task object to a LinkedHashmap
     *
     * @param timetable is a TimeTable object filled with Task object
     * @param calendar  is a LinkedHashmap waiting to be filled with string representations of Task object
     */
    private void reformatTaskEvents(TimeTable timetable, LinkedHashMap<String, ArrayList<ArrayList<String>>> calendar) {
        LinkedHashMap<String, ArrayList<Task>> taskSchedule =
                timetable.getTaskCalendar();
        Set<String> taskKeys = taskSchedule.keySet();
        //iterate through taskCalendar for Task objects.
        //iterate through weekdays.
        for (String key : taskKeys) {
            ArrayList<Task> reminderList = taskSchedule.get(key);
            // finds task objs in taskList if it's not empty
            if (!reminderList.isEmpty()) {
                // initialize the arraylist in the appropriate calendar date to store task strings.
                ArrayList<String> emptyList = new ArrayList<>();
                calendar.get(key).set(24, emptyList);
                for (Task reminder : reminderList) {
                    //reconstruct task obj into string
                    String taskString = reminder.reconstruct().get(0);
                    //add tasks to calendar
                    calendar.get(key).get(24).add(taskString);
                }
            }
        }
    }

    /**
     * A helper for the reformat method, uses a TimeTable (linkedhashmap of Events objectrs) to add
     * string representations of non-Task Events object to a LinkedHashmap
     *
     * @param timetable is a TimeTable object filled with non-Task Events object
     * @param calendar  is a LinkedHashmap waiting to be filled with string representations of non-Task Events object
     */

    private void reformatNonTaskEvents(TimeTable timetable, LinkedHashMap<String,
            ArrayList<ArrayList<String>>> calendar) {
        //iterate through timetable for event objects.
        LinkedHashMap<String, Events[]> schedule = timetable.getCalendar();
        Set<String> keys = schedule.keySet();
        //iterate through weekdays.
        for (String key : keys) {
            Events[] list = schedule.get(key);
            //iterate through all time intervals in a day.
            for (int i = 0; i < 24; i++) {
                // finds an Events obj.
                if (list[i] != null) {
                    //reconstruct Event obj
                    ArrayList<String> newlist = list[i].reconstruct();
                    //add the reconstructed Event obj to an appropriate location on calendar
                    calendar.get(list[i].getDate()).set(i, newlist);
                }
            }
        }
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

    /**
     * Get all CourseSections in this TimeTable
     * Precondition: All CourseSections in this TimeTable occur between the hours of 07:00 to 22:00 on weekdays.
     *
     * @return An ArrayList of all the CourseSections in this TimeTable
     */
    public ArrayList<CourseSection> returnCourses() {
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
