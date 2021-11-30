package TimeTableContainers;

/**
 * Stores a TimeTableManager's state.
 *
 * === Attributes ===
 * managerSave: The state of a TimeTableManager
 */
public class Memento {
    private TimeTableManager managerSave;

    /**
     * Constructs a Memento with given managerSave
     * @param managerSave the state of a TimeTableManager
     */
    public Memento(TimeTableManager managerSave) { this.managerSave = managerSave; }

    /**
     * Gets the saved TimeTableManager
     * @return the saved TimeTableManager
     */
    public TimeTableManager getSavedManager() { return managerSave; }
}
