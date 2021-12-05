package TimeTableContainers;

import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;
import Helpers.Constants;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * aTimeTable class stores all the activities from Monday to Sunday. If there
 * is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prompting user to take action or ignore it.
 *
 * === Private Attributes ===
 * calendar: a single timetable from Monday to Friday, and 24 intervals per day that can be filled with an event.
 * tasksCalender: contain Task objects(as values) in the corresponding weekday (as keys)
 */
public class TimeTable {
    private final LinkedHashMap<String, Events[]> calendar;
    private final LinkedHashMap<String, ArrayList<Task>> taskCalendar;

    /**
     * Constructs an empty TimeTable.
     */
    public TimeTable() {
        this.calendar = new LinkedHashMap<>() {
        };
        this.taskCalendar = new LinkedHashMap<>() {
        };
        for (String day : Constants.DAYS_OF_THE_WEEK) {
            this.calendar.put(day, new Events[24]);
            this.taskCalendar.put(day, new ArrayList<>());
        }
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
        if (event.getName().isEmpty()) {
            event.nameIt();
        }
        if (checkConflicts(event) && (event instanceof Activity || event instanceof CourseSection)) {
            int start = event.getStartTime().getHour();
            int end = event.getEndTime().getHour();

            // Add activity to interval between startTime and endTime
            for (int i = start; i < end; i++) {
                this.calendar.get(event.getDate())[i] = event;
            }
            return true;
        } else if (checkConflicts(event) && event instanceof Task) {
            this.taskCalendar.get(event.getDate()).add((Task) event);
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
     *
     * @param reminder is a Task object
     */
    public void addTasks(Task reminder) {
        this.taskCalendar.get(reminder.getDate()).add(reminder);
    }

    /**
     * Remove the given activity from this TimeTable
     * Precondition: the end time is later than the start time of this activity.
     *
     * @param startTime The start time of this activity
     * @param endTime   The end time of this activity
     * @param day       The day of the week this activity is on
     * @return true if the activity was removed, false otherwise.
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

    // ================================ toString ===============================

    /**
     * Generate the String representation of the calendar.
     *
     * @return the string of calendar
     */
    public String toString() {

        StringBuilder timeStrings = new StringBuilder();
        for (String day : this.calendar.keySet()) {
            timeStrings.append(getCalenderString(day));
            timeStrings.append(getTaskString(day)).append("\n");
        }
        return timeStrings.toString();
    }

    /**
     * Generate the String representation of all the tasks for the given day.
     *
     * @param day the day for the String to be generated from
     * @return the string of the tasks for the day
     */
    private String getTaskString(String day) {
        // Add reminder for all tasks
        StringBuilder tasks = new StringBuilder();
        tasks.append("\t").append("Reminders: ");
        ArrayList<Task> allReminders = this.taskCalendar.get(day);
        for (Task allReminder : allReminders) {
            tasks.append(allReminder).append(", ");
        }
        return tasks.substring(0, tasks.length() - 2);
    }

    /**
     * Generate a String representation of the Calendar for the given day.
     *
     * @param day the day for the String to be generated from
     * @return the string of the calendar for the day
     */
    private StringBuilder getCalenderString(String day) {
        StringBuilder times = new StringBuilder(day + ":\n");

        // Add all courses and activities
        Events[] events = this.calendar.get(day);
        for (int i = 0; i < events.length; i++) {
            times.append("\t").append(i).append(":00 ");
            if (events[i] != null) {
                times.append(events[i].toString()).append("\n");
            } else {
                times.append("\n");
            }
        }
        return times;
    }

    // ===================== Setters and Getters ===============================
    /**
     * Get the task hashmap
     *
     * @return the task hashmap
     */
    public LinkedHashMap<String, ArrayList<Task>> getTaskCalendar() {
        return taskCalendar;
    }

    /**
     * Get the calendar of the timetable
     *
     * @return the calendar contained in the timetable
     */
    public LinkedHashMap<String, Events[]> getCalendar() {
        return this.calendar;
    }
}

