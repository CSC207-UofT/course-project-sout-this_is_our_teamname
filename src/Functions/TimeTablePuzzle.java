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
    private Course extendedCourse;

    /**
     * Construct a TimeTablePuzzle
     *
     * @param courses The courses to be schdeuled in the TimeTable
     * @param manager The TimeTableManager that will schedule the courses
     */
    public TimeTablePuzzle(HashMap<String, HashMap<String, ArrayList<Course>>> courses, TimeTableManager manager) {
        this.courses = courses;
        this.manager = manager;
        this.scheduled = new ArrayList<>();
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
    public ArrayList<Course> getScheduled() { return scheduled; }

    /**
     * Get the course that this puzzle is extending, after the extensions method has been called.
     *
     * @return the course this puzzle is extending if this puzzle is a product of the extensions
     * method, null otherwise.
     */
    public Course getExtendedCourse() { return extendedCourse; }

    /**
     * Add the extended course to this TimeTablePuzzle
     *
     * @param course the extended course
     */
    public void addExtendedCourse(Course course) { extendedCourse = course; }

    /**
     * Add the scheduled course to this TimeTablePuzzle.
     *
     * @param course the scheduled course to be added.
     */
    public void addScheduledCourse(Course course) {scheduled.add(course);}

    /**
     * Determine whether all the courses have been scheduled, if the "puzzle" has been solved
     *
     * @return true if the courses have been scheduled, false otherwise
     */
    public boolean isSolved() {
        int counter = 0;
        for (HashMap<String, ArrayList<Course>> course : this.getCourses().values()) {
            counter = counter + course.size();
        }
        return counter == this.getScheduled().size();
    }


    /**
     * Gets an ArrayList of all possible sections for a certain course component(LEC/PRA/TUT) that can be scheduled
     * with existing courses (for example return all CSC207 LEC sections)
     *
     * @return An ArrayList of all sections for course component that can be scheduled
     */
    public ArrayList<TimeTablePuzzle> extensions() {
        ArrayList<TimeTablePuzzle> extensions = new ArrayList<>();
        ArrayList<CourseSection> presentCourseSections = this.manager.returnCourses();

        // Iterate through courses
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            // Iterate through course components(LEC/TUT/PRA)
            for (ArrayList<Course> courseComponents : course.values()) {
                // Iterate through individual component sections(LEC0101/LEC0201) and check if already scheduled
                boolean truth = true;
                for (int i = 0; i < courseComponents.size(); i++) {
                    if (this.getScheduled().contains(courseComponents.get(i))) {
                        i = courseComponents.size();
                        truth = false;
                    }
                }
                if (truth) {
                    for (Course courseComponent : courseComponents) {
                        String term = courseComponent.getTerm();
                        ArrayList<CourseSection> split = courseComponent.split();

                        boolean conflictTruth = true;
                        for (CourseSection splitSection : split) {
                            // Check conflicts, if none, break from loop
                            if (!manager.getTimetable(term).checkConflicts(splitSection)) {
                                conflictTruth = false;
                            }
                        }
                        if (conflictTruth) {
                            // If no conflicts for all split sections, create extension with course added
                            // First make copy of TimeTable Manager
                            TimeTableManager managerCopy = new TimeTableManager();
                            for (CourseSection courseSection : presentCourseSections) {
                                String term2 = courseSection.getTerm();
                                managerCopy.getTimetable(term2).schedule(courseSection);
                            }
                            // Schedule new course
                            for (CourseSection splitSection : split) {
                                managerCopy.getTimetable(term).schedule(splitSection);

                                TimeTablePuzzle puzzleExtension = new TimeTablePuzzle(this.courses, managerCopy);

                                // Copy scheduled courses
                                for (Course scheduledCourse : this.scheduled) {
                                    puzzleExtension.addScheduledCourse(scheduledCourse);
                                }
                                puzzleExtension.addExtendedCourse(courseComponent);

                                // Add extension to course
                                extensions.add(puzzleExtension);
                            }
                        }
                        return extensions;
                    }
                }

            }
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
        ArrayList<CourseSection> thisCourses = this.manager.returnCourses();
        ArrayList<CourseSection> otherCourses = solved.getManager().returnCourses();

        ArrayList<CourseSection> difference = new ArrayList<>(otherCourses);
        difference.removeAll(thisCourses);

        for (CourseSection courseSection : difference) {
            String term = courseSection.getTerm();
            this.getManager().getTimetable(term).schedule(courseSection);
        }
    }
}

