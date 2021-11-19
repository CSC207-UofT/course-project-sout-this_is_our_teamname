package Functions;

import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableContainers.TimeTableManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Puzzle class for TimeTable Manager, schedules the given courses in the appropriate timetables
 * without any conflicts
 */
public class TimeTablePuzzle {
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
     * Determine whether all the courses have been scheduled, if the "puzzle" has been solved
     *
     * @return true if the courses have been scheduled, false otherwise
     */
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
    public ArrayList<Course> extensions() {
        ArrayList<Course> extensions = new ArrayList<>();
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
                    // If no conflicts for all split sections, add to ArrayList
                    extensions.add(courseComponent);
                }
            }

        }
        return extensions;
    }
    public static void main(String args[]) {

    }
}

