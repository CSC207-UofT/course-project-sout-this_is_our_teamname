package TimeTableStuff;

import TimeTableObjects.CourseStuff.Section;
import TimeTableObjects.TimeTableObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TimeTable class stores all the activities from Monday to Sunday. If there is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prmopting user to take action or ignore it.
 */
public class TimeTable {
    //TODO maybe use linkedmap or treemap?
    private HashMap<String, ArrayList<TimeTableObject>> calender;

    public TimeTable() {
        this.calender = new HashMap<>() {{
            put("Monday", new ArrayList<>());
            put("Tuesday", new ArrayList<>());
            put("Wednesday", new ArrayList<>());
            put("Thursday", new ArrayList<>());
            put("Friday", new ArrayList<>());
            put("Saturday", new ArrayList<>());
            put("Sunday", new ArrayList<>());
        }};
    }

    /**
     * Schedules the given activity into the appropriate weekday.
     * @param activity the given activity
     * @return true if scheduling is successful, false if there is a conflict
     */
    public boolean schedule(TimeTableObject activity) {
        //must be a Section before checking for conflicts.
        if (activity instanceof Section && checkConflicts((Section) activity)) {
            (this.calender.get(activity.getDate())).add(activity);
            return true;
        } else {
            return false;
        }
    }
    //TODO Overload the schedule method so that it can take in (TimeTableObject, Type)

    /**
     * Check if there is a conflict in the timetable with given activity.
     * @param activity the given activity
     * @return true if there is no conflict, false otherwise
     */
    public boolean checkConflicts(Section activity) {
        //if the specific weekday is empty, there is no conflict.
        if ((this.calender.get(activity.getDate()).isEmpty())) {
            return true;
        }
        //otherwise, comparing all the sections in that specific day.
        for (TimeTableObject session: this.calender.get(activity.getDate())) {
            //only comparing sections, so check for type, then compare.
            if (session instanceof Section) {
                if (activity.compareTo((Section) session) == -1) {
                    //conflict has been found
                    return false;
                }
            }
        }
        //no conflict has been found
        return true;
    }

    /**
     * Grabs the calendar
     * @return calendar
     */
    public HashMap<String, ArrayList<TimeTableObject>> getCalender() {
        return calender;
    }

    /**
     * Generate the String representation of the calender.
     * @return the string of calender
     */
    public String toString() {
        HashMap<String, ArrayList<String>> times = new HashMap<>();
        for (String day : this.calender.keySet()) {
            ArrayList<String> sections = new ArrayList<>();
            for (TimeTableObject time : this.calender.get(day)) {
                sections.add(time.toString());
            }
            times.put(day, sections);
        }
        return times.toString();
    }
}
