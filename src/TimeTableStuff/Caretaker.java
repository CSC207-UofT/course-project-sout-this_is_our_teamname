package TimeTableStuff;

import java.util.ArrayList;

public class Caretaker {
    ArrayList<TimeTableManager> savedCalenders = new ArrayList<TimeTableManager>();

    /**
     * Adds memento to the list of TimeTableManager saved
     * @param memento is the TimeTableManager to be saved
     */
    public void addMemento(TimeTableManager memento) { savedCalenders.add(memento); }

    /**
     * Gets the memento from the list of saved TimeTableManager
     * @param index is the index of the TimeTableManager in the list
     * @return the TimeTableManager from the given index
     */
    public TimeTableManager getMemento(int index) { return savedCalenders.get(index); }

}

