package TimeTableContainers;

import java.util.ArrayList;

/**
 * Stores all Mementos with each TimeTableManager state.
 *
 * === Attributes ===
 * savedManagers: The list of stored Mementos
 */
public class Caretaker {
    ArrayList<Memento> savedManagers = new ArrayList<>();

    /**
     * Adds Memento to the list of Mementos saved at index given and remove any Mementos after
     * @param index is the index the Memento is saved to
     * @param memento is the Memento to be saved
     */
    public void addMemento(int index, Memento memento) {
        ArrayList<Memento> removeManagers = new ArrayList<>();
        for (Memento savedManager : savedManagers) {
            if (savedManagers.indexOf(savedManager) >= index) {
                removeManagers.add(savedManager);
            }
        }
        savedManagers.removeAll(removeManagers);
        savedManagers.add(index, memento);
    }

    /**
     * Gets the Memento from the list of saved TimeTableManager
     * @param index is the index of the Memento in the list
     * @return the Memento from the given index
     */
    public Memento getMemento(int index) { return savedManagers.get(index); }

    /**
     * Gets the size of the savedCalenders
     * @return the size of savedCalenders
     */
    public int size() { return savedManagers.size(); }
}
