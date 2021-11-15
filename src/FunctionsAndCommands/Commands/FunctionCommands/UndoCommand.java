package FunctionsAndCommands.Commands.FunctionCommands;

import DatabaseController.CommandFactory;
import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.Caretaker;
import TimeTableStuff.TimeTableManager;

public class UndoCommand implements Command {
    private final TimeTableManager manager;
    private int currentManager;
    private final Caretaker caretaker;

    public UndoCommand(TimeTableManager manager, int currentManager, Caretaker caretaker) {
        this.manager = manager;
        this.currentManager = currentManager;
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
        if (this.currentManager >= 2) {
            this.currentManager--;
            manager.setTimetables(caretaker.getMemento(currentManager-1));
            CommandFactory.removeCurrentManager();
        }
    }
}
