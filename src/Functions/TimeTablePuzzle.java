package Functions;

import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableContainers.TimeTableManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Puzzle class for TimeTable Manager, schedules the given courses in the appropriate timetables
 * without any conflicts
 *
 * === Private Attributes ===
 *  courses: The courses this TimeTablePuzzle will solve.
 *  manager: The manager that will schedule the courses.
 *  scheduled: The ArrayList that holds the courses already scheduled
 *  extendedCourse: The course that this puzzle has "extended" after the extensions method
 *
 */
public class TimeTablePuzzle {
    private final HashMap<String, HashMap<String, ArrayList<Course>>> courses;
    private final TimeTableManager manager;
    private final ArrayList<Course> scheduled;
    private final ArrayList<ArrayList<Course>> unscheduled;
    private Course extendedCourse;

    /**
     * Construct a TimeTablePuzzle
     *
     * @param courses The courses to be schdeuled in the TimeTable
     * @param manager The TimeTableManager that will schedule the courses
     */
    public TimeTablePuzzle(HashMap<String, HashMap<String,
            ArrayList<Course>>> courses, TimeTableManager manager,
                           ArrayList<ArrayList<Course>> unscheduled,
                           ArrayList<Course> scheduled) {
        this.courses = courses;
        this.manager = manager;
        this.scheduled = scheduled;
        this.unscheduled = unscheduled;
        this.extendedCourse = null;
    }

    /**
     * Get the courses this TimeTablePuzzle is supposed to schedule.
     *
     * @return the courses this TimeTablePuzzle is supposed to schedule.
     */
    public HashMap<String, HashMap<String, ArrayList<Course>>> getCourses() {
        return courses;
    }

    /**
     * Get the TimeTableManager of this TimeTablePuzzle.
     *
     * @return the TimeTablemanager of this TimeTablePuzzle
     */
    public TimeTableManager getManager() {
        return manager;
    }

    /**
     * Get the ArrayList of scheduled courses of this TimeTablePuzzle.
     *
     * @return the ArrayList of scheduled courses.
     */
    public ArrayList<Course> getScheduled() {
        return scheduled;
    }

    /**
     * Get the course that this puzzle is extending, after the extensions method has been called.
     *
     * @return the course this puzzle is extending if this puzzle is a product of the extensions
     * method, null otherwise.
     */
    public Course getExtendedCourse() {
        return extendedCourse;
    }

    /**
     * Add the extended course to this TimeTablePuzzle
     *
     * @param course the extended course
     */
    public void addExtendedCourse(Course course) {
        extendedCourse = course;
    }

    /**
     * Add the scheduled course to this TimeTablePuzzle.
     *
     * @param course the scheduled course to be added.
     */
    public void addScheduledCourse(Course course) {
        scheduled.add(course);
    }

    /**
     * Determine whether all the courses have been scheduled, if the "puzzle" has been solved
     *
     * @return true if the courses have been scheduled, false otherwise
     */
    public boolean isSolved() {
        int counter = 0;
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            counter = counter + course.size();
        }
        return counter == this.scheduled.size();
    }


    /**
     * Gets an ArrayList of all possible sections for a certain course component(LEC/PRA/TUT) that can be scheduled
     * with existing courses (for example return all CSC207 LEC sections)
     *
     * @return An ArrayList of all sections for course component that can be scheduled
     */
    public ArrayList<TimeTablePuzzle> extensions() {
        ArrayList<TimeTablePuzzle> extensions = new ArrayList<>();

        // If the self.unassigned == []
        if (this.unscheduled.size() == 0){
            return new ArrayList<>();
        }

        // Get the last item in the unassigned list
        ArrayList<Course> current_course =
                this.unscheduled.remove(this.unscheduled.size() - 1);

        // starting point
        TimeTableManager initial_schedule = this.manager.get_copy();

        // Get each date
        for (Course course : current_course) {
            // Get a copy of the schedule

            TimeTableManager copy_schedule = initial_schedule.get_copy();

            for (CourseSection section : course.split()){
                copy_schedule.schedule(section);
            }

            TimeTablePuzzle next = new TimeTablePuzzle(new HashMap<>(courses),
                    copy_schedule,
                    new ArrayList<>(this.unscheduled),
                    new ArrayList<>(this.scheduled));
            next.scheduled.add(course);
            extensions.add(next);
        }

        return extensions;
    }

    /**
     * Make this TimeTablePuzzle identical to solved, i.e. schedule all the courses in solved that aren't
     * in this TimeTable Puzzle.
     *
     * @param solved a solved TimeTablePuzzle.
     */
    public void schedulePuzzle(TimeTablePuzzle solved) {
        ArrayList<CourseSection> thisCourses = this.manager.getCourses();
        ArrayList<CourseSection> otherCourses =
                solved.getManager().getCourses();

        ArrayList<CourseSection> difference = new ArrayList<>(otherCourses);
        difference.removeAll(thisCourses);

        for (CourseSection courseSection : difference) {
            String term = courseSection.getTerm();
            this.getManager().getTimetable(term).schedule(courseSection);
        }
    }

    public boolean fail_fast(){
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Course course : scheduled){
            sb.append(course.getCourseName()).append(course.getSectionName()).append("||||");
        }
        return sb.toString();
    }
}

