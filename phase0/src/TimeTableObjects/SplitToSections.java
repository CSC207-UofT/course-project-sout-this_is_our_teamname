package TimeTableObjects;

import TimeTableObjects.CourseStuff.Section;

import java.util.ArrayList;

/**
 * Splits TimeTableObjects or Course into one or more Section, the number depends on how many seperated sessions there
 * are per week.
 * @param <T> TimeTableObject or Course
 */
public interface SplitToSections<T> {
    ArrayList<Section> splitToSections(T o);
}
