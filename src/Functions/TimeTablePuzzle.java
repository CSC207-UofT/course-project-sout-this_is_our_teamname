package Functions;

import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableContainers.TimeTableManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Puzzle class for TimeTable Manager, schedules the given courses in the appropriate timetables
 * without any conflicts
 */
public class TimeTablePuzzle extends Puzzle {
    private final HashMap<String, HashMap<String, ArrayList<Course>>> courses;
    private final TimeTableManager manager;

    /**
     * Construct a TimeTablePuzzle
     *
     * @param courses The courses to be schdeuled in the TimeTable
     * @param manager The TimeTableManager that will schedule the courses
     */
    public TimeTablePuzzle(HashMap<String, HashMap<String, ArrayList<Course>>> courses, TimeTableManager manager) {
        this.courses = courses;
        this.manager = manager;
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
     * Determine whether all the courses have been scheduled, if the "puzzle" has been solved
     *
     * @return true if the courses have been scheduled, false otherwise
     */
    @Override
    public boolean isSolved() {
        // Iterate through the courses
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            // Iterate through course components(LEC/TUT/PRA)
            for (ArrayList<Course> courseComponents : course.values()) {
                boolean truth = false;
                // Iterate through individual component sections(LEC0101/LEC0201)
                for (Course courseComponent : courseComponents) {
                    String term = courseComponent.getTerm();
                    // Check if the component section is scheduled in the timetable
                    truth = this.manager.getTimetable(term).checkCourseSection(courseComponent);
                    if (truth) {
                        break;
                    }
                }
                if (!truth) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determine if the courses cannot be scheduled due to conflicts
     *
     * @return true if the courses cannot be scheduled, false otherwise
     */
    @Override
    public boolean failFast() {
        // Iterate through courses
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            // Iterate through course components(LEC/TUT/PRA)
            for (ArrayList<Course> courseComponents : course.values()) {
                // Iterate through individual component sections(LEC0101/LEC0201)
                for (Course courseComponent : courseComponents) {
                    String term = courseComponent.getTerm();
                    // Check if the component section is scheduled in the timetable
                    if (manager.getTimetable(term).checkCourseSection(courseComponent)) {
                        break;
                    }
                    ArrayList<CourseSection> split = courseComponent.split();
                    boolean truth = false;
                    for (CourseSection splitSection : split) {
                        // Check conflicts, if none, break from loop
                        if (!manager.getTimetable(term).checkConflicts(splitSection)) {
                            break;
                        }
                    }
                    return false;
                }

            }

        }
        return true;
    }

    /**
     * Gets all possible course sections(including LEC/TUT/PRA) that can be scheduled with
     * existing courses
     *
     * @return An ArrayList of all courses that can be scheduled
     */
    @Override
    public TimeTablePuzzle[] extensions() {
        ArrayList<TimeTablePuzzle> extensions = new ArrayList<>();
        ArrayList<CourseSection> presentCourseSections = this.manager.returnCourses();

        // Iterate through courses
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            // Iterate through course components(LEC/TUT/PRA)
            for (ArrayList<Course> courseComponents : course.values()) {
                // Iterate through individual component sections(LEC0101/LEC0201)
                for (Course courseComponent : courseComponents) {
                    String term = courseComponent.getTerm();
                    // Check if the component section is scheduled in the timetable
                    if (manager.getTimetable(term).checkCourseSection(courseComponent)) {
                        break;
                    }
                    ArrayList<CourseSection> split = courseComponent.split();
                    for (CourseSection splitSection : split) {
                        // Check conflicts, if none, break from loop
                        if (!manager.getTimetable(term).checkConflicts(splitSection)) {
                            break;
                        }
                    }
                    // If no conflicts for all split sections, create extension with course added
                    // First make copy of TimeTable Manager
                    TimeTableManager managerCopy = new TimeTableManager();
                    for (CourseSection courseSection : presentCourseSections) {
                        String term2 = courseSection.getTerm();
                        managerCopy.getTimetable(term2).schedule(courseSection);
                    }
                    TimeTablePuzzle puzzleExtension = new TimeTablePuzzle(this.courses, managerCopy);
                    for (CourseSection splitSection : split) {
                        puzzleExtension.getManager().getTimetable(term).schedule(splitSection);
                    }
                    // Add extension to course
                    extensions.add(puzzleExtension);
                }
            }

        }
        // Convert ArrayList to Array
        TimeTablePuzzle[] extensionResult = new TimeTablePuzzle[extensions.size()];
        if (extensions.size() >= 1) {
            for (int i = 0; i < extensions.size(); i++) {
                extensionResult[i] = extensions.get(i);
            }
        }
        return extensionResult;
    }

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

