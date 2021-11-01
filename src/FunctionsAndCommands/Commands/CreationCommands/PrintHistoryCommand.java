package FunctionsAndCommands.Commands.CreationCommands;

import DatabaseController.DatabaseController;
import FunctionsAndCommands.Commands.Command;

import java.util.Stack;

public class PrintHistoryCommand implements Command {
    private final DatabaseController TheController;

    public PrintHistoryCommand(DatabaseController theController){
        TheController = theController;
    }

    @Override
    public void execute() {
        Stack<Command> history = this.TheController.getCommandHistory();
        for (Command item : history){
            System.out.println(item);
        }
    }

    @Override
    public String toString() {
        return "Printed the history of the commands";
    }
}
