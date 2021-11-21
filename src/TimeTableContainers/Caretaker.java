package TimeTableContainers;

import java.util.ArrayList;

/**
 * Stores all TimeTableManagers in memento.
 *
 * === Attributes ===
 * savedCalenders: The list of stored TimeTableManager
 */
public class Caretaker {
    ArrayList<TimeTableManager> savedCalenders = new ArrayList<>();

    /**
     * Adds memento to the list of TimeTableManager saved at index given and remove any TimeTableManager after
     * @param index is the index the memento is saved to
     * @param memento is the TimeTableManager to be saved
     */
    public void addMemento(int index, TimeTableManager memento) {
        ArrayList<TimeTableManager> removeTimeTables = new ArrayList<>();
        for (TimeTableManager savedTimeTable : savedCalenders) {
            if (savedCalenders.indexOf(savedTimeTable) >= index) {
                removeTimeTables.add(savedTimeTable);
            }
        }
        savedCalenders.removeAll(removeTimeTables);
        savedCalenders.add(index, memento);
    }

    /**
     * Gets the memento from the list of saved TimeTableManager
     * @param index is the index of the TimeTableManager in the list
     * @return the TimeTableManager from the given index
     */
    public TimeTableManager getMemento(int index) { return savedCalenders.get(index); }

    /**
     * Gets the size of the savedCalenders
     * @return the size of savedCalenders
     */
    public int size() { return savedCalenders.size(); }
}
