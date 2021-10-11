package TimeTableStuff;

import TimeTableObjects.TimeTableObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTable {

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
     * @param time the given activity
     * @return true if scheduling is successful, false if there is a conflict
     */
    public boolean schedule(TimeTableObject time) {
        return false;
    }

    /**
     * Check if there is a conflict in the timetable with given activity.
     * @param time the given activity
     * @return true if there is no conflict, false otherwise
     */
    public boolean checkConflicts(TimeTableObject time) {
        return false;
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
