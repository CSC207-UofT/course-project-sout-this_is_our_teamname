package FunctionsAndCommands.Commands.FunctionCommands;

import DatabaseController.CommandFactory;
import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.Caretaker;
import TimeTableStuff.TimeTableManager;

public class UndoCommand implements Command {
    private final TimeTableManager manager;
    private int currentManager;
    private final Caretaker caretaker;

    /**
     * A constructor to initialize what this command is connected to
     * @param manager is the TimeTableManager the user currently has
     * @param currentManager is the index of the TimeTableManager
     * @param caretaker is where all TimeTableManager states are stored
     */
    public UndoCommand(TimeTableManager manager, int currentManager, Caretaker caretaker) {
        this.manager = manager;
        this.currentManager = currentManager;
        this.caretaker = caretaker;
    }

    /**
     * Undo the last change in timetable.
     */
    @Override
    public void execute() {
        if (this.currentManager >= 2) {
            this.currentManager--;
            manager.setTimetables(caretaker.getMemento(currentManager-1));
            CommandFactory.removeCurrentManager();
        }
    }
}
