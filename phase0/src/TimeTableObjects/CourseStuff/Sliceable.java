package CourseStuff;
import java.util.ArrayList;
//package TimeTableObjects

import TimeTableObjects.CourseStuff.Section;

/**
 * CourseStuff.Sliceable is an interface implemented by Course and all its subclasses.
 * It takes a course object and splits it into 1 or more sections.
 *
 * @param <T> A section
 */
public interface Sliceable<T> {
    ArrayList<Section> split();
}
