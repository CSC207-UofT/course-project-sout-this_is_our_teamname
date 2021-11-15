package FunctionsAndCommands.Commands.FunctionCommands;

import DatabaseController.CommandFactory;
import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.Caretaker;
import TimeTableStuff.TimeTableManager;

public class RedoCommand implements Command {
    private final TimeTableManager manager;
    private int currentManager;
    private final Caretaker caretaker;

    public RedoCommand(TimeTableManager manager, int currentManager, Caretaker caretaker) {
        this.manager = manager;
        this.currentManager = currentManager;
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
        if (caretaker.size() > currentManager) {
            this.currentManager++;
            manager.setTimetables(caretaker.getMemento(currentManager-1));
            CommandFactory.addCurrentManager();
        }
    }
}
