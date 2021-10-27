package TimeTableStuff;

import EntitiesAndObjects.TimeTableObjects.Events;
import GlobalHelpers.Constants;
import EntitiesAndObjects.TimeTableObjects.CourseSection;
import EntitiesAndObjects.TimeTableObjects.Task;
import EntitiesAndObjects.TimeTableObjects.Activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * TimeTable class stores all the activities from Monday to Sunday. If there is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prompting user to take action or ignore it.
 */
public class TimeTable {
    //TODO leave the warning for now, check after reformatted TimeTable.
    private final LinkedHashMap<String, ArrayList<Events>> calender;

    public TimeTable() {
        this.calender = new LinkedHashMap<>() {{
            put(Constants.MONDAY, new ArrayList<>());
            put(Constants.TUESDAY, new ArrayList<>());
            put(Constants.WEDNESDAY, new ArrayList<>());
            put(Constants.THURSDAY, new ArrayList<>());
            put(Constants.FRIDAY, new ArrayList<>());
            put(Constants.SATURDAY, new ArrayList<>());
            put(Constants.SUNDAY, new ArrayList<>());
        }};
    }
    /**
     * Schedules the given activity into the appropriate weekday.
     * @param activity the given activity
     * @return true if scheduling is successful, false if there is a conflict
     */
    public boolean schedule(Events activity) {
        if (activity instanceof Activity || activity instanceof Task){
            (this.calender.get(activity.getDate())).add(activity);
            return true;
        } else if (activity instanceof CourseSection && checkConflicts((CourseSection) activity)) {
            (this.calender.get(activity.getDate())).add(activity);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if there is a conflict in the timetable with given activity.
     * @param activity the given activity
     * @return true if there is no conflict, false otherwise
     */
    public boolean checkConflicts(CourseSection activity) {
        //if the specific weekday is empty, there is no conflict.
        if ((this.calender.get(activity.getDate()).isEmpty())) {
            return true;
        }
        //otherwise, comparing all the sections in that specific day.
        for (Events session: this.calender.get(activity.getDate())) {
            //only comparing sections, so check for type, then compare.
            if (session instanceof CourseSection) {
                if (activity.compareTo((CourseSection) session) < 0) {
                    //conflict has been found
                    return false;
                }
            }
        }
        //no conflict has been found
        return true;
    }


    /**
     * Generate the String representation of the calender.
     * @return the string of calender
     */
    public String toString() {
        LinkedHashMap<String, ArrayList<String>> times = new LinkedHashMap<>();
        for (String day : this.calender.keySet()) {
            ArrayList<String> sections = new ArrayList<>();
            for (Events time : this.calender.get(day)) {
                sections.add(time.toString());
            }
            times.put(day, sections);
        }
        return times.toString();
    }
}
