package TimeTableObjects.EventObjects;

import TimeTableObjects.Interfaces.Reconstructable;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * Task is a reminder Events object.
 *
 * === Attributes ===
 * date: The weekday of the task
 * term: The term of the task
 * name: The name of the task
 */
public class Task implements Reconstructable {
    private final String date;
    private final String term;
    private String name;

    /**
     * Construct a task with time, location and a description.
     * @param theDate is the weekday of the task.
     * @param term is the term of the task
     */
    public Task(String name, String theDate, String term) {
        this.date = theDate;
        this.term = term;
        this.name = name;
    }

    /**
     * Add to the name of the event
     *
     * @param info is going to be added to the name of the Events object
     */
    public void addToName(String info) {
        this.name += info;
    }

    /**
     * reconstruct takes an Events object and returns an Arraylist based on the non-time/date attributes.
     */
    @Override
    public ArrayList<String> reconstruct() {
        ArrayList<String> list = new ArrayList<>(1);
        // index 0: task name
        list.add(0, this.getName());
        return list;
    }

    /**
     *  Generate the string representation of the task.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        if (this.getName().equals("")) {
            return "N/A";
        }
        else {
            return this.getName();
        }
    }

    // ===================== Setters and Getters ===============================

    /**
     * Set the name for the event.
     *
     * @param name is going to be the name of the Events object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the task
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the date of the task
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Get the term of the task
     *
     * @return the term
     */
    public String getTerm() {
        return term;
    }

}
