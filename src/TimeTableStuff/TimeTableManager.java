package TimeTableStuff;

import EntitiesAndObjects.Course;
import EntitiesAndObjects.TimeTableObjects.CourseSection;

import GlobalHelpers.Constants;
import EntitiesAndObjects.NonCourseObject;
import EntitiesAndObjects.TimeTableObjects.Events;
import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.Task;

// Importing HashMap class
import java.util.*;

public class TimeTableManager {
    private final HashMap<String, TimeTable> timetables;

    /**
     * Creates a new TimeTableManager with two default TimeTables for Fall and Winter.
     *
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
     * Get the course from interface and schedule it to the corresponding timetable(s).
     *
     * @param c a Course object passed from user interface
     */
    public void schedule(Course c) {
        ArrayList<CourseSection> list = c.split();
        if (c.getTerm().equals(Constants.FALL)) {
            for (CourseSection x : list) {
                timetables.get(Constants.FALL).schedule(x);
            }
        } else if (c.getTerm().equals(Constants.WINTER)) {
            for (CourseSection x : list) {
                timetables.get(Constants.FALL).schedule(x);
            }
        } else {
            for (CourseSection x : list) {
                timetables.get(Constants.FALL).schedule(x);
                timetables.get(Constants.WINTER).schedule(x);
            }
        }
    }

    /**
     * Get an event from the user interface and schedule it to the corresponding timetable(s).
     *
     * @param event a Events passed from user interface
     *
     */
    public void schedule(NonCourseObject event) {
        Events life = getCorrectTimeTableObject(event);

        // Add to corresponding timetable(s).
        if (event.getTerm().equals(Constants.FALL)){
            timetables.get(Constants.FALL).schedule(life);
        }
        else if (event.getTerm().equals(Constants.WINTER)){
            timetables.get(Constants.WINTER).schedule(life);
        }
        else{
            timetables.get(Constants.FALL).schedule(life);
            timetables.get(Constants.WINTER).schedule(life);
        }
    }

    /**
     * A helper method for schedule (Events). Returns event to the
     * correct type.
     *
     * @param event The TimetableObject that needs to be scheduled.
     * @return event "cast" to the correct type.
     */
    private Events getCorrectTimeTableObject(NonCourseObject event) {
        if (event.getType().equals(Constants.LIFE)){
            Scanner descriptionScanner = new Scanner(System.in);
            System.out.println("Please provide a description of your life " +
                    "activity: ");
            return new Activity(event.getStartTime(), event.getEndTime(),
                    event.getLocation(), event.getDate(), event.getTerm(),
                    descriptionScanner.nextLine());
        }
        else if (event.getType().equals(Constants.DESCRIPTION_LESS_LIFE)){
            return new Task(event.getStartTime(),
                    event.getEndTime(), event.getLocation(), event.getDate(),
                    event.getTerm());
        } else {
            // TODO More types of events.
            return null;
        }
    }

    /**
     * Returns an array of timetables with all the timetables.
     *
     * @return an array of timetables with all the timetables
     */
    public TimeTable[] getAllTimeTables(){
        //TODO No terms/names returned with the timetables
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
