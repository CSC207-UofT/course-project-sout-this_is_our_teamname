package TimeTableStuff;

import TimeTableObjects.Parents.InputData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TimeTable class stores all the activities from Monday to Sunday. If there is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prmopting user to take action or ignore it.
 */
public class TimeTable {

    private final HashMap<String, ArrayList<InputData>> calender;

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
    public boolean schedule(InputData activity) {
        if (checkConflicts(activity)) {
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
    public boolean checkConflicts(InputData activity) {
        if ((this.calender.get(activity.getDate()).isEmpty())) {
            return true;
        }
        for (InputData time : this.calender.get(activity.getDate())) {
            //TODO: uncomment, compare time and activity after Comparable interface is implemented
//            if (activity.compare(time)) {
//                return true;
//            }
            return true;
        }
        return false;
    }

    /**
     * Grabs the calendar
     * @return calendar
     */
    public HashMap<String, ArrayList<InputData>> getCalender() {
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
            for (InputData time : this.calender.get(day)) {
                sections.add(time.toString());
            }
            times.put(day, sections);
        }
        return times.toString();
    }
}
