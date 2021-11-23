package TimeTableObjects.Interfaces;

import TimeTableObjects.Events;

import java.util.ArrayList;

/**
 * Savable is an interface implemented by Events and its subclasses
 * It takes an Events object and returns an ArrayList based on the non-time/date attributes.
 */
public interface Reconstructable {

    /**
     * reconstruct takes an Events object and returns an Arraylist based on the non-time/date attributes.
     */
    ArrayList<String> reconstruct();
}
