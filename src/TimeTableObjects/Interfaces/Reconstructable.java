package TimeTableObjects.Interfaces;

import TimeTableObjects.Events;

import java.util.ArrayList;

/**
 * TODO REMOVE THIS SENTENCE
 *
 * Savable is an interface implemented by CourseSection/Activity
 * It takes a CourseSection/Activity object and returns an ArrayList based on the non-time/date attributes.
 */
public interface Reconstructable {

    /**
     * reconstruct takes a CourseSection/Activity object and returns an Arraylist based on the non-time/date attributes.
     */
    ArrayList<String> reconstruct();
}
