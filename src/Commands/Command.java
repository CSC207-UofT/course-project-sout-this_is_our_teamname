package Commands;

/**
 *
 * An interface for a command in the command design pattern
 */
public interface Command {
    /**
     * Execute the command
     */
    void execute();

    /**
     * Returns a String representation of the command
     * @return A string
     */
    String toString();
}
