package Commands.FunctionCommands;

import Commands.Command;
import InterfaceAdaptors.DatabaseController;
import TimeTableContainers.Caretaker;
import TimeTableContainers.Originator;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;

import java.util.HashMap;

/**
 * A command to undo an action.
 *
 * === Private Attributes ===
 * manager: The TimeTableManager that will be changed
 * controller: The DatabaseController that commands uses
 */
public class UndoCommand implements Command {
    private final TimeTableManager manager;
    private final DatabaseController controller;

    /**
     * A constructor to initialize what this command is connected to
     * @param manager is the TimeTableManager the user currently has
     * @param controller is the DatabaseController that commands uses
     */
    public UndoCommand(TimeTableManager manager, DatabaseController controller) {
        this.manager = manager;
        this.controller = controller;
    }

    /**
     * Undo the last change in timetable.
     */
    @Override
    public void execute() {
        int currentManagerIndex = this.controller.getCurrentManagerIndex();
        Originator originator = this.controller.getOriginator();
        Caretaker caretaker = this.controller.getCaretaker();

        if (currentManagerIndex >= 2) {
            currentManagerIndex --;
            HashMap<String, TimeTable> undoManager = originator.restoreFromMemento(caretaker.getMemento(currentManagerIndex-1));
            manager.setTimetables(undoManager);
            this.controller.removeManagerIndex();
            System.out.println("Undo Successful");
        }
        else {
            System.out.println("Undo Unsuccessful");
        }
    }
}
