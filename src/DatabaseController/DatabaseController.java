package DatabaseController;
import FunctionsAndCommands.Commands.Command;
import GlobalHelpers.InvalidInputException;

import java.util.Stack;

/**
 * A database controller class that serves as the Invoker in the command
 * pattern. It sets the command and executes them.
 *
 * TODO @Caules If you think it's a good idea, maybe link Operator Interface
 * TODO here too so that it controls what inputs users add?
 *
 * === Private Attributes ===
 * CommandHistory: The history of all the commands that has ever been made
 *  since the program was run (For Phase 2, maybe serialize it so that it can
 *  have a history of all commands ever made)
 *
 * possibleCommands: The factory that generates the commands to be executed
 */
public class DatabaseController {
    private final Stack<Command> CommandHistory;
    private final CommandFactory Factory;

    /**
     * A constructor. Sets the commandHistory and Factory
     */
    public DatabaseController(){
        this.CommandHistory = new Stack<>();
        this.Factory = new CommandFactory();
    }

    /**
     * Sets the command into the Command History.
     *
     * The command history will act as a history of all the commands in order
     * they were added. The setCommands will also execute the command.
     */
    public void addToCommandHistory(String commandKey) throws InvalidInputException {
        Command theCommand = this.Factory.getCommand(commandKey);
        setCommands(theCommand);
    }

    /**
     * Restores previous command
     *
     * TODO @Hubert perhaps use Momento here? What do you think? Is it a good
     * TODO idea or not?
     */
    public void rollback(){
        Command previous = this.CommandHistory.pop();
        // TODO HELP!
    }

    /**
     * Set the command into the commands and executes the command.
     *
     * The execute method is an abstract method so all command objects will
     * have it. It serves as the "main" method of a command!
     *
     * @param theCommand the command
     */
    private void setCommands(Command theCommand){
        this.CommandHistory.push(theCommand);
        theCommand.execute();
    }
}
