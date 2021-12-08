package Commands.FunctionCommands;

import Commands.Command;

/**
 *
 * A command whose sole responsibility is to signal to the DatabaseController
 * to exit the program
 */
public class ExitProgramCommand implements Command {
    /**
     * Nothing is executed
     */
    @Override
    public void execute() {}

    /**
     * A string representation of the command
     *
     * @return a String representation of the command
     */
    @Override
    public String toString() {
        return "Exited Program";
    }
}
