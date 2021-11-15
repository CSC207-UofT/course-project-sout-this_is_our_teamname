package FunctionsAndCommands.Commands.FunctionCommands;

import DatabaseController.CommandFactory;
import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.Caretaker;
import TimeTableStuff.TimeTableManager;

public class RedoCommand implements Command {
    private final TimeTableManager manager;
    private int currentManager;
    private final Caretaker caretaker;

    /**
     * A constructor to initialize what this command is connected to
     * @param manager is the TimeTableManager the user currently has
     * @param currentManager is the index of the TimeTableManager
     * @param caretaker is where all TimeTableManager states are stored
     */
    public RedoCommand(TimeTableManager manager, int currentManager, Caretaker caretaker) {
        this.manager = manager;
        this.currentManager = currentManager;
        this.caretaker = caretaker;
    }

    /**
     * Redo the last change in timetable.
     */
    @Override
    public void execute() {
        if (caretaker.size() > currentManager) {
            this.currentManager++;
            manager.setTimetables(caretaker.getMemento(currentManager-1));
            CommandFactory.addCurrentManager();
        }
    }
}
