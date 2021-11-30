package TimeTableContainers;

import java.util.HashMap;

/**
 * Sets and gets TimeTableManager in Memento.
 *
 * === Attributes ===
 * manager: The stored TimeTableManager
 */
public class Originator {
    private TimeTableManager manager;

    /**
     * Set the value of the TimeTableManager
     * @param newManager is the TimeTableManager to be saved
     */
    public void setManager(TimeTableManager newManager) {
        this.manager = newManager.getCopy();
    }

    /**
     * Creates a new Memento with the given TimeTableManager
     * @return the new Memento
     */
    public Memento storeInMemento() {
        return new Memento(manager);
    }

    /**
     * Gets the TimeTableManager currently stored in Memento
     * @param memento the Memento with TimeTableManager stored
     * @return the TimeTableManager currently stored in memento
     */
    public HashMap<String, TimeTable> restoreFromMemento(Memento memento) {
        manager = memento.getSavedManager();
        return manager.getTimetables();
    }

}
