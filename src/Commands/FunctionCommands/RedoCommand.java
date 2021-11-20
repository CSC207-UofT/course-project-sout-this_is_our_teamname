package Commands.FunctionCommands;

import Commands.Command;
import Controllers.CommandFactory;
import TimeTableContainers.Caretaker;
import TimeTableContainers.Originator;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Events;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * A command to redo an undone action.
 *
 * === Private Attributes ===
 * manager: The TimeTableManager that will be changed
 * currentManager: The index of the TimeTableManager stored in memento
 * caretaker: Stores all memento of TimeTableManager
 * originator: Sets and gets TimeTableManager from memento
 */
public class RedoCommand implements Command {
    private final TimeTableManager manager;
    private int currentManager;
    private final Caretaker caretaker;
    private final Originator originator;

    /**
     * A constructor to initialize what this command is connected to
     * @param manager is the TimeTableManager the user currently has
     * @param currentManager is the index of the TimeTableManager
     * @param caretaker is where all TimeTableManager states are stored
     */
    public RedoCommand(TimeTableManager manager, int currentManager, Caretaker caretaker, Originator originator) {
        this.manager = manager;
        this.currentManager = currentManager;
        this.caretaker = caretaker;
        this.originator = originator;
    }

    /**
     * Redo the last change in timetable.
     */
    @Override
    public void execute() {
        if (caretaker.size() > currentManager) {
            this.currentManager++;
            HashMap<String, LinkedHashMap<String, Events[]>> currentTimetable = originator.restoreFromMemento(caretaker.getMemento(currentManager-1));
            manager.setTimetables(new TimeTableManager(currentTimetable));
            CommandFactory.addCurrentManager();
            System.out.println("Redo Successful");
        }
        else {
            System.out.println("Redo Unsuccessful");
        }
    }
}
