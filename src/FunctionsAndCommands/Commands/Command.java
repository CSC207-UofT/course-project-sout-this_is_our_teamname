package FunctionsAndCommands.Commands;

public interface Command {
    /**
     * Execute the command
     */
    void execute();

    /**
     * Returns a String representation of the command
     * @return
     */
    String toString();
}
