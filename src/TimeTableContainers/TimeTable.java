package TimeTableContainers;

import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;
import Helpers.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * TimeTable class stores all the activities from Monday to Sunday. If there is a conflict when storing a new activity,
 * it will still be stored, and a conflict warning will be sent back prompting user to take action or ignore it.
 * === Private Attributes ===
 * calendar is a single timetable from Monday to Friday, and 24 intervals per day that can be filled with an event.
 * tasks contain Task objects(as values) in the corresponding weekday(as keys)
 */
public class TimeTable {
    private LinkedHashMap<String, Events[]> calendar;
    private LinkedHashMap<String, ArrayList<Task>> taskCalendar;

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
        this.taskCalendar = new LinkedHashMap<>() {{
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
     * Sets the TimeTable with given savedCalendar
     * @param savedCalendar is the saved calendar
     */
    public void setCalendar(LinkedHashMap<String, Events[]> savedCalendar) {
        this.calendar = savedCalendar;
    }

    /**
     * Sets the TimeTable with given savedTaskCalendar
     * @param savedTaskCalendar is the saved task calendar
     */
    public void setTaskCalendar(LinkedHashMap<String, ArrayList<Task>> savedTaskCalendar) {
        this.taskCalendar = savedTaskCalendar;
    }

    /**
     * Schedules the given activity into the appropriate weekday.
     * @param event the given activity
     * @return true if scheduling is successful, false if there is a conflict
     */
    public boolean schedule(Events event) {
        if (checkConflicts(event)) {
            int start = event.getStartTime().getHour();
            int end = event.getEndTime().getHour();

            //Add activity to interval between startTime and endTime
            for (int i = start; i < end; i++) {
                this.calendar.get(event.getDate())[i] = event;
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
            if (weekday[i] != null){
                return false;
            }
        }
        return true;
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

    /**
     * Adds a Task object to the taskCalendar
     * @param task is a Task object
     */
    public void addTasks(Task task) {
        this.taskCalendar.get(task.getDate()).add(task);
    }

    /**
     * Get the task hashmap
     * @return the task hashmap
     */
    public LinkedHashMap<String, ArrayList<Task>> getTaskCalendar() {return taskCalendar;}

    /**
     * Get the calender of the timetable
     * @return the calender contained in the timetable
     */
    public LinkedHashMap<String, Events[]> getCalendar() {
        return this.calendar;
    }

    /**
     * Gets a copy of the calendar (not alias)
     *
     * @return the copy of the calendar
     */
    public LinkedHashMap<String, Events[]> getCalendarCopy() {
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
     * Gets a copy of the taskCalendar (not alias)
     *
     * @return the copy of the taskCalendar
     */
    public LinkedHashMap<String, ArrayList<Task>> getTaskCopy() {
        LinkedHashMap<String, ArrayList<Task>> copy = new LinkedHashMap<>();
        for (String day : this.taskCalendar.keySet()) {
            ArrayList<Task> tasks = new ArrayList<>(this.taskCalendar.get(day));
            copy.put(day, tasks);
        }
        return copy;
    }

    /**
     * Gets a copy of the TimeTable (not alias)
     *
     * @return the copy of the TimeTable
     */
    public TimeTable getCopy() {
        TimeTable copy = new TimeTable();
        copy.setCalendar(getCalendarCopy());
        copy.setTaskCalendar(getTaskCopy());
        return copy;
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
}

//
//    /**
//     *
//     *
//     * Precondition: otherTimeTable must have
//     *
//     * @param otherTimeTable
//     */
//    public void scheduleAll(TimeTable otherTimeTable) {
//        ArrayList<CourseSection> thisCourses = this.returnCourses();
//        ArrayList<CourseSection> otherCourses = otherTimeTable.returnCourses();
//        if (thisCourses.size() > otherCourses.size()){
//            ArrayList<CourseSection> difference = new ArrayList<>(thisCourses);
//            difference.removeAll(otherCourses);
//        }
//        else if (thisCourses.size() < otherCourses.size()){
//            ArrayList<CourseSection> difference = new ArrayList<>(otherCourses);
//        }
//
//    }
