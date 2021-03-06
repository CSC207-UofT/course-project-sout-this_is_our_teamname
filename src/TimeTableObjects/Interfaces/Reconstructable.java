package TimeTableObjects.Interfaces;

import java.util.ArrayList;

/**
 * Savable is an interface implemented by CourseSection/Activity
 * It takes a CourseSection/Activity object and returns an ArrayList based on the non-time/date attributes.
 */
public interface Reconstructable {

    /**
     * reconstruct takes a CourseSection/Activity object and returns an Arraylist based on the non-time/date attributes.
     */
    ArrayList<String> reconstruct();
}
