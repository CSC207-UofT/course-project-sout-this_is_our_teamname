package TimeTableContainers;

import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;
import Helpers.Constants;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * A TimeTable class stores all the activities from Monday to Sunday. If there
 * is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prompting user to take action or ignore it.
 *
 * === Private Attributes ===
 * calendar: a single timetable from Monday to Friday, and 24 intervals per day that can be filled with an event.
 * tasksCalender: contain Task objects(as values) in the corresponding weekday (as keys)
 */
public class TimeTable {
    private LinkedHashMap<String, Events[]> calendar;
    private LinkedHashMap<String, ArrayList<Task>> taskCalendar;

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

        if (!hasConflicts(event)) {
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
     * Schedules the given task into the appropriate weekday.
     *
     * @param task the given task
     * @return true if scheduling is successful
     */
    public boolean schedule(Task task){
        this.taskCalendar.get(task.getDate()).add(task);
        return true;
    }

    /**
     * Check if there is a conflict in the timetable with given event.
     *
     * @param event the given event
     * @return true if there is a conflict, false otherwise
     */
    public boolean hasConflicts(Events event) {
        // Get all events on that day of the week
        Events[] eventsOnDay = calendar.get(event.getDate());

        int start = event.getStartTime().getHour();
        int end = event.getEndTime().getHour();

        for (int i = start; i < end; i++) {
            if (eventsOnDay[i] != null) {
                return true;
            }
        }
        return false;
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

    /**
     * Makes a copy of the TimeTable without alias
     * @return a copy of the TimeTable
     */
    public TimeTable make_copy(){
        TimeTable copy = new TimeTable();

        LinkedHashMap<String, Events[]> copy_calender =
                new LinkedHashMap<>();
        for (String calendar_key : this.calendar.keySet()){
            copy_calender.put(calendar_key,
                    this.calendar.get(calendar_key).clone());
        }

        copy.setCalendar(copy_calender);

        LinkedHashMap<String, ArrayList<Task>> copy_task_calender =
                new LinkedHashMap<>();
        for (String calendar_key : this.taskCalendar.keySet()){
            copy_task_calender.put(calendar_key,
                    new ArrayList<>(this.taskCalendar.get(calendar_key)));
        }

        copy.setTaskCalendar(copy_task_calender);

        return copy;
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

    /**
     * Sets the calendar of the timetable
     *
     * @param other the other calendar
     */
    public void setCalendar(LinkedHashMap<String, Events[]> other){
        this.calendar = other;
    }

    /**
     * Sets the task hashmap
     * @param taskCalendar a hashmap of tasks
     */
    public void setTaskCalendar(LinkedHashMap<String, ArrayList<Task>> taskCalendar) {
        this.taskCalendar = taskCalendar;
    }

    /**
     * Convert a timetable to a list of lists of strings that contains the data
     */
    public List<List<String>> toList() {
        List<List<String>> eventLists = new ArrayList<>();

        // Search all days in a week and get data from both the calendar and the task calendar
        for (String day : Constants.DAYS_OF_THE_WEEK) {
            // Search every hour period in a day and get data from the calendar
            for (int n = 0; n <= 23; n++) {
                // If there are non-empty data during this hour
                if (this.calendar.get(day)[n] != null) {
                    CalendarHelper(eventLists, day, this.calendar.get(day)[n]);
                }
            }

            // If there are non-empty data in the task calendar
            if (!(this.taskCalendar.get(day).isEmpty())) {
                TaskCalendarHelper(eventLists, day, this.taskCalendar.get(day));
            }
        }
        return eventLists;
    }


    /**
     * Append one more list of data to the list of lists of strings
     *
     * @param eventLists the list of lists of strings that needs to be enlarged
     * @param day the day information of the new list of data
     * @param item the event information of the new list of data
     */
    private void CalendarHelper(List<List<String>> eventLists, String day, Events item) {
        // Format one line
        List<String> eventList = new ArrayList<>();
        eventList.add(0, day);
        eventList.add(1, item.getStartTime().toString() +
                "-" + item.getEndTime().toString());
        eventList.addAll(item.reconstruct());
        // Append the line
        eventLists.add(eventList);
    }

    /**
     * Append one more list of data to the list of lists of strings
     *
     * @param eventLists the list of lists of strings that needs to be enlarged
     * @param day the day information of the new list of data
     * @param tasks the event information of the new list of data
     */
    private void TaskCalendarHelper(List<List<String>> eventLists, String day, ArrayList<Task> tasks) {
        // Format one line
        List<String> taskList = new ArrayList<>();
        taskList.add(day);
        taskList.add("Tasks");
        for (Task task : tasks) {
            taskList.addAll(task.reconstruct());
        }
        // Append the line
        eventLists.add(taskList);
    }
}

