package TimeTableStuff;

import TimeTableObjects.CourseStuff.Course;
import TimeTableObjects.CourseStuff.Section;

import ConstantsAndExceptions.Constants;
import TimeTableObjects.TimeTableObject;
import TimeTableObjects.Life;
import TimeTableObjects.DescriptionlessLife;

// Importing HashMap class
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
        timetables.remove(term);
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
        if (c.getTerm().equals(Constants.FALL)) {
            for (Section x : list) {
                timetables.get(Constants.FALL).schedule(x);
            }
        } else if (c.getTerm().equals(Constants.WINTER)) {
            for (Section x : list) {
                timetables.get(Constants.FALL).schedule(x);
            }
        } else {
            for (Section x : list) {
                timetables.get(Constants.FALL).schedule(x);
                timetables.get(Constants.WINTER).schedule(x);
            }
        }
    }

    /**
     * Get the an event from the user interface and schedule it to the corresponding timetable(s).
     *
     * @param event a TimeTableObject passed from user interface
     * @param type a String passed from user interface representing the type of the TimeTableObject
     */
    public void schedule(TimeTableObject event, String type) {
        TimeTableObject life = getCorrectTimeTableObject(event, type);

        // Add to corresponding timetable(s).
        if (event.getTerm().equals(Constants.FALL)){
            timetables.get(Constants.FALL).schedule(life);
        }
        else if (event.getTerm().equals(Constants.WINTER)){
            timetables.get(Constants.WINTER).schedule(life);
        }
        else{
            timetables.get(Constants.FALL).schedule(event);
            timetables.get(Constants.WINTER).schedule(event);
        }
    }

    /**
     * A helper method for schedule (TimeTableObject). Returns event to the
     * correct type.
     *
     * @param event The TimetableObject that needs to be scheduled
     * @param type The type of the object
     * @return event "cast" to the correct type.
     */
    private TimeTableObject getCorrectTimeTableObject(TimeTableObject event, String type) {
        if (type.equals(Constants.LIFE)){
            Scanner descriptionScanner = new Scanner(System.in);
            System.out.println("Please provide a description of your life " +
                    "activity: ");
            return new Life(event.getStartTime(), event.getEndTime(),
                    event.getLocation(), event.getDate(), event.getTerm(),
                    descriptionScanner.nextLine());
        }
        else if (type.equals(Constants.DESCRIPTIONLESS_LIFE)){
            return new DescriptionlessLife(event.getStartTime(),
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
}
