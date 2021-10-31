package TimeTableStuff;

import EntitiesAndObjects.TimeTableObjects.Events;
import GlobalHelpers.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * TimeTable class stores all the activities from Monday to Sunday. If there is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prompting user to take action or ignore it.
 */
public class TimeTable {
    //TODO leave the warning for now, check after reformatted TimeTable.
    private final LinkedHashMap<String, Events[]> calender;

    public TimeTable() {
        this.calender = new LinkedHashMap<>() {{
            put(Constants.MONDAY, new Events[24]);
            put(Constants.TUESDAY, new Events[24]);
            put(Constants.WEDNESDAY, new Events[24]);
            put(Constants.THURSDAY, new Events[24]);
            put(Constants.FRIDAY, new Events[24]);
            put(Constants.SATURDAY, new Events[24]);
            put(Constants.SUNDAY, new Events[24]);
        }};
    }
    /**
     * Schedules the given activity into the appropriate weekday.
     * @param activity the given activity
     * @return true if scheduling is successful, false if there is a conflict
     */
    public boolean schedule(Events activity) {
        if (checkConflicts(activity)) {
            int start = activity.getStartTime().getHour();
            int end = activity.getEndTime().getHour();

            //Add activity to interval between startTime and endTime
            for (int i=start; i<end; i++) {
                this.calender.get(activity.getDate())[i] = activity;
            }
            return true;
        }
        return false;
    }

    /**
     * Check if there is a conflict in the timetable with given activity.
     * @param activity the given activity
     * @return true if there is no conflict, false otherwise
     */
    public boolean checkConflicts(Events activity) {
        int start = activity.getStartTime().getHour();
        int end = activity.getEndTime().getHour();

        //Check whether there is another activity between startTime and endTime
        for (int i=start; i<end; i++) {
            if (this.calender.get(activity.getDate())[i] != null) {
                return false;
            }
        }
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
            for (Events activity : this.calender.get(day)) {
                sections.add(activity.toString());
            }
            times.put(day, sections);
        }
        return times.toString();
    }
}
