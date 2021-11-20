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
 * A command to undo an action.
 *
 * === Private Attributes ===
 * manager: The TimeTableManager that will be changed
 * currentManager: The index of the TimeTableManager stored in memento
 * caretaker: Stores all memento of TimeTableManager
 * originator: Sets and gets TimeTableManager from memento
 */
public class UndoCommand implements Command {
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
    public UndoCommand(TimeTableManager manager, int currentManager, Caretaker caretaker, Originator originator) {
        this.manager = manager;
        this.currentManager = currentManager;
        this.caretaker = caretaker;
        this.originator = originator;
    }

    /**
     * Undo the last change in timetable.
     */
    @Override
    public void execute() {
        if (this.currentManager >= 2) {
            this.currentManager--;
            HashMap<String, LinkedHashMap<String, Events[]>> currentTimetable = originator.restoreFromMemento(caretaker.getMemento(currentManager-1));
            manager.setTimetables(new TimeTableManager(currentTimetable));
            CommandFactory.removeCurrentManager();
            System.out.println("Undo Successful");
        }
        else {
            System.out.println("Undo Unsuccessful");
        }
    }
}
