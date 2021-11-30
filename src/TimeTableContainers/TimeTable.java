package TimeTableContainers;

import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;
import Helpers.Constants;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * TimeTable class stores all the activities from Monday to Sunday. If there is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prompting user to take action or ignore it.
 *
 * === Private Attributes ===
 * calendar: a single timetable from Monday to Friday, and 24 intervals per
 *  day that can be filled with an event.
 * tasksCalender: contain Task objects(as values) in the corresponding weekday
 *  (as keys)
 */
public class TimeTable {
    private final LinkedHashMap<String, Events[]> calendar;
    private final LinkedHashMap<String, ArrayList<Task>> taskCalendar;

    public TimeTable() {
        this.calendar = new LinkedHashMap<>(){};
        this.taskCalendar = new LinkedHashMap<>(){};
        for (String day : Constants.DAYS_OF_THE_WEEK){
            this.calendar.put(day, new Events[24]);
            this.taskCalendar.put(day, new ArrayList<>());
        };
    }

    // ========================= Basic Operations ==============================
    /**
     * Schedules the given activity into the appropriate weekday.
     *
     * @param event the given activity
     * @return true if scheduling is successful, false if there is a conflict
     */
    public boolean schedule(Events event) {
        // eliminating possible empty string for name attribute, so it would show up in display better.
        event.unnamed();
        if (checkConflicts(event)) {
            int start = event.getStartTime().getHour();
            int end = event.getEndTime().getHour();

            // Add activity to interval between startTime and endTime
            for (int i = start; i < end; i++) {
                this.calendar.get(event.getDate())[i] = event;
            }
            return true;
        }
        return false;
    }

    /**
     * Check if there is a conflict in the timetable with given activity.
     *
     * @param activity the given activity
     * @return true if there is no conflict, false otherwise
     */
    public boolean checkConflicts(Events activity) {
        // Get all events on that day of the week
        Events[] activitiesOnDay = calendar.get(activity.getDate());

        int start = activity.getStartTime().getHour();
        int end = activity.getEndTime().getHour();

        for (int i = start; i < end; i++) {
            if (activitiesOnDay[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a Task object to the taskCalendar
     * @param task is a Task object
     */
    public void addTasks(Task task) {
        this.taskCalendar.get(task.getDate()).add(task);
    }

    /**
     * Remove the given activity from this TimeTable
     * Precondition: the start time is later than the end time of this activity.
     *
     * @param startTime The start time of this activity
     * @param endTime   The end time of this activity
     * @param day       The day of the week this activity is on
     * @return          true if the activity was removed, false otherwise.
     */
    public boolean remove(LocalTime startTime, LocalTime endTime, String day) {
        int startTimeInt = startTime.getHour();
        int endTimeInt = endTime.getHour();
        if (this.calendar.get(day)[startTimeInt] == null) {
            return false;
        }
        while (startTimeInt != endTimeInt) {
            this.calendar.get(day)[startTimeInt] = null;
            startTimeInt += 1;
        }
        return true;
    }

    /**
     * Generate the String representation of the calender.
     * @return the string of calendar
     */
    public String toString() {

        StringBuilder timeStrings = new StringBuilder();

        // For each day
        for (String day : this.calendar.keySet()) {

            // Add the day header
            StringBuilder times = new StringBuilder(day + ":\n");

            Events[] events = this.calendar.get(day);
            for (int i = 0; i < events.length; i++) {

                // Append the time as a string
                times.append("\t").append(i).append(":00 ");

                // Append the event as a string at that time
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

    // ===================== Setters and Getters ===============================
    /**
     * Returns a copy of the timetable (not alias)
     *
     * @return the copy of the timetable
     */
    public LinkedHashMap<String, Events[]> getCopy() {
        LinkedHashMap<String, Events[]> copy = new LinkedHashMap<>();
        for (String day : this.calendar.keySet()) {
            Events[] events = new Events[24];
            for (int i = 0; i < 24; i++) {
                events[i] = this.calendar.get(day)[i];
            }
            copy.put(day, events);
        }
        return copy;
    }

    /**
     * Get the task hashmap
     * @return the task hashmap
     */
    public LinkedHashMap<String, ArrayList<Task>> getTaskCalendar() {
        return taskCalendar;
    }

    /**
     * Get the calender of the timetable
     * @return the calender contained in the timetable
     */
    public LinkedHashMap<String, Events[]> getCalendar() {
        return this.calendar;
    }

    /**
     * Check if the given course is present in this TimeTable
     * TODO This method is never used, and incorrect
     * @param course The course to be checked
     * @return true if the course is present, false otherwise
     */
    public boolean checkCourseSection(Course course) {
        String courseCode = course.getCourseName() + course.getSectionName();
        for (Events[] day : this.calendar.values()) {
            for (Events hour : day) {
                if (hour instanceof CourseSection) {
                    String sectionCode = ((CourseSection) hour).getCourseName() +
                            ((CourseSection) hour).getSectionCode();
                    if (sectionCode.equals(courseCode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

