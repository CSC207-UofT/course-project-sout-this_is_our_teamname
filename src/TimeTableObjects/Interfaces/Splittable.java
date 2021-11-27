package TimeTableObjects.Interfaces;

import TimeTableObjects.EventObjects.CourseSection;

import java.util.ArrayList;

/**
 * CourseStuff.Sliceable is an interface implemented by Course and all its subclasses.
 * It takes a course object and splits it into 1 or more sections.
 *
 * @param <T> A section
 */
public interface Splittable<T> {
    ArrayList<CourseSection> split();
}
