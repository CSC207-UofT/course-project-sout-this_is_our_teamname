package TimeTableContainers;

import Helpers.ConflictException;
import Helpers.Constants;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.Events;

import java.util.*;

public class TimeTableManager {
    private final HashMap<String, TimeTable> timetables;
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

    /**
     * Get all CourseSections in this TimeTable
     *
     * @return An ArrayList of all the CourseSections in this TimeTable.
     */
    // use getter for TimeTable to remove for loop
    public ArrayList<CourseSection> returnCourses() {
        ArrayList<CourseSection> courses = new ArrayList<>();
        for (TimeTable timeTable : this.getAllTimeTables())
            for (Events[] day : timeTable.getCalender().values()) {
                for (Events hour : day) {
                    if (hour instanceof CourseSection){
                        courses.add((CourseSection) hour);
                    }
                }
            }
        return courses;
    }
}
