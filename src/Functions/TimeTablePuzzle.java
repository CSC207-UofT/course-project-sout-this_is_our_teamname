package Functions;

import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableContainers.TimeTableManager;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTablePuzzle {
    private final HashMap<String, HashMap<String, ArrayList<Course>>> courses;
    private final TimeTableManager manager;

    public TimeTablePuzzle(HashMap<String, HashMap<String, ArrayList<Course>>> courses, TimeTableManager manager) {
        this.courses = courses;
        this.manager = manager;
    }


    public boolean isSolved() {
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            for (ArrayList<Course> courseComponents : course.values()) {
                boolean truth = false;
                for (Course courseComponent : courseComponents) {
                    String term = courseComponent.getTerm();
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

    public boolean failFast() {
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            for (ArrayList<Course> courseComponents : course.values()) {
                for (Course courseComponent : courseComponents) {
                    String term = courseComponent.getTerm();
                    if (manager.getTimetable(term).checkCourseSection(courseComponent)) {
                        break;
                    }
                    ArrayList<CourseSection> split = courseComponent.split();
                    boolean truth = false;
                    for (CourseSection splitSection : split) {
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

    public ArrayList<Course> extensions() {
        ArrayList<Course> extensions = new ArrayList<>();
        for (HashMap<String, ArrayList<Course>> course : this.courses.values()) {
            for (ArrayList<Course> courseComponents : course.values()) {
                for (Course courseComponent : courseComponents) {
                    String term = courseComponent.getTerm();
                    if (manager.getTimetable(term).checkCourseSection(courseComponent)) {
                        break;
                    }
                    ArrayList<CourseSection> split = courseComponent.split();
                    for (CourseSection splitSection : split) {
                        if (!manager.getTimetable(term).checkConflicts(splitSection)) {
                            break;
                        }
                    }
                    extensions.add(courseComponent);
                }
            }

        }
        return extensions;
    }
}

