package Commands.CreationCommands;

import Commands.Command;
import InterfaceAdaptors.DatabaseController;

import java.util.Stack;

/**
 *
 * Prints the history of the user's actions
 *
 * === Attributes ===
 * theController: The DatabaseController with the history of all commands executed.
 */
public class PrintHistoryCommand implements Command {
    private final DatabaseController theController;

    /**
     * Constructor to set the command.
     * @param theController is the DatabaseController with the history of all commands executed.
     */
    public PrintHistoryCommand(DatabaseController theController){
        this.theController = theController;
    }

    /**
     * Executes the command to display history of all commands executed.
     */
    @Override
    public void execute() {
        Stack<Command> history = this.theController.getCommandHistory();
        for (Command item : history){
            System.out.println(item);
        }
    }

    /**
     * Return a String representation of the Command
     * @return the String representation
     */
    @Override
    public String toString() {
        return "Printed the history of the commands";
    }
}
