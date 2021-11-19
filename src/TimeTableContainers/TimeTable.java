package TimeTableContainers;

import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.Events;
import Helpers.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * TimeTable class stores all the activities from Monday to Sunday. If there is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prompting user to take action or ignore it.
 * === Private Attributes ===
 * calendar is a single timetable from Monday to Friday, and 24 intervals per day that can be filled with an event.
 */
public class TimeTable {
    private final LinkedHashMap<String, Events[]> calendar;

    public TimeTable() {
        this.calendar = new LinkedHashMap<>() {{
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
     * Get the calender of the timetable
     * @return the calender contained in the timetable
     */
    public LinkedHashMap<String, Events[]> getCalendar() {
        return this.calendar;
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
            for (int i = start; i < end; i++) {
                this.calendar.get(activity.getDate())[i] = activity;
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
        Events[] weekday = calendar.get(activity.getDate());
        int start = activity.getStartTime().getHour();
        int end = activity.getEndTime().getHour();
        for (int i = start; i < end; i++ ){
            if (weekday[i] != null){return false;}
        }
        return true;
    }

    /**
     * Generate the String representation of the calender.
     * @return the string of calendar
     */
    public String toString() {
        StringBuilder timeStrings = new StringBuilder();
        for (String day : this.calendar.keySet()) {
            StringBuilder times = new StringBuilder(day + ":\n");

            Events[] events = this.calendar.get(day);
            for (int i = 0; i < events.length; i++) {
                times.append("\t").append(i).append(":00 ");
                if (events[i] != null) {
                    times.append(events[i]).append("\n");
                } else {
                    times.append("\n");
                }
            }
            timeStrings.append(times);
        }
        return timeStrings.toString();
    }


    /**
     * Check if any CourseSections that reside under the given course code are present
     * in this TimeTable
     *
     * @param courseCode The course code to be checked
     * @return An ArrayList of all the CourseSections correlating to courseCode that are
     * present in this TimeTable
     * e.g. If courseCode is "CSC207", checkCourse will return [CourseSection(CSC207LEC0101),
     * CourseSection(CSC207TUT0101), ... ,]
     */
    public ArrayList<Events> checkCourse(String courseCode) {
        ArrayList<Events> matchingCourses = new ArrayList<>();
        for (Events[] day : this.calendar.values()) {
            for (Events hour : day) {
                if (hour instanceof CourseSection){
                    String sectionCode = ((CourseSection) hour).getSectionCode();
                    if (sectionCode.contains(courseCode)) {
                        matchingCourses.add(hour);
                    }

                }
            }
        }
        return matchingCourses;
    }

    /**
     * Check if the given course is present in this TimeTable
     *
     * @param course The course to be checked
     * @return true if the course is present, false otherwise
     */
    public boolean checkCourseSection(Course course) {
        String courseCode = course.getSectionName();
        for (Events[] day : this.calendar.values()) {
            for (Events hour : day) {
                if (hour instanceof CourseSection) {
                    String sectionCode = ((CourseSection) hour).getSectionCode();
                    if (sectionCode.equals(courseCode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
