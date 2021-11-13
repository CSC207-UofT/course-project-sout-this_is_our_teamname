package DatabaseController;
import FunctionsAndCommands.Commands.Command;
import GlobalHelpers.InputCheckers.InputChecker;
import GlobalHelpers.InputCheckers.Predicate;
import GlobalHelpers.InvalidInputException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * A database controller class that serves as the Invoker in the command
 * pattern. It sets the command and executes them.
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
    private CommandFactory Factory;

    /**
     * A constructor. Sets the commandHistory and Factory
     */
    public DatabaseController(){
        this.CommandHistory = new Stack<>();
        this.Factory = null;
    }

    /**
     * Sets the command into the Command History.
     *
     * The command history will act as a history of all the commands in order
     * they were added. The setCommands will also execute the command.
     */
    public void runCommand(String requestedCommand) throws InvalidInputException {
        assert this.Factory != null;
        Command theCommand = this.Factory.getCommand(requestedCommand);
        executeCommand(theCommand);
    }

    /**
     * Runs and Prompts the user with what they want to do
     */
    public void run(){
        boolean running = true;
        while (running){
            LinkedHashMap<String, String> allowed =
                    hashMapit(this.Factory.getAllowedFunctions());

            for (String key : allowed.keySet()){
                System.out.println(key + ": " + allowed.get(key));
            }

            InputChecker requestCommand = new InputChecker("Please select a " +
                    "command to execute", new isValidCommand(allowed));
            String requested = requestCommand.checkCorrectness();

            try {
                runCommand(allowed.get(requested));
                running = false;
            } catch (InvalidInputException e){
                System.out.println("Command not allowed. Please try again!");
            }
        }
    }

    // ============================== Helpers ==================================
    /**
     * Sends the command into the commandHistory and executes the command.
     *
     * The execute method is an abstract method so all command objects will
     * have it.
     *
     * @param theCommand the command
     */
    private void executeCommand(Command theCommand){
        this.CommandHistory.push(theCommand);
        theCommand.execute();
    }

    /**
     * Returns a hashmap of the entries in the string array strings with
     * corresponding integer values from least to greatest.
     *
     * @param strings the array of strings
     * @return a hashmap of items from strings as values and ascending
     * integers as keys
     */
    private LinkedHashMap<String, String> hashMapit(String[] strings){
        LinkedHashMap<String, String> theMap = new LinkedHashMap<>();
        for (int i = 0; i < strings.length; i++){
            theMap.put(String.valueOf(i), strings[i]);
        }
        return theMap;
    }

    // ============================ Setters and Getters ========================
    /**
     * Set the factory attached to this controller
     *
     * @param theFactory the factory to be set
     */
    public void setFactory(CommandFactory theFactory){
        this.Factory = theFactory;
    }

    /**
     * Get a Stack of the commands that has been executed in the history
     * @return The Stack of commands.
     */
    public Stack<Command> getCommandHistory() {
        return CommandHistory;
    }

    // ============================ Predicates =================================
    private static class isValidCommand extends Predicate{
        private final HashMap<String, String> allowed;
        public isValidCommand(HashMap<String, String> values){
            this.allowed = values;
        }

        @Override
        public boolean run(String prompt) {
            return this.allowed.containsKey(prompt);
        }
    }
}
