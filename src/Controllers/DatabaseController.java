package Controllers;
import Commands.Command;
import Commands.FunctionCommands.ExitProgramCommand;
import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import Helpers.InvalidInputException;

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
     * Runs and Prompts the user with what they want to do
     *
     * @return true iff the program has been able to execute the command
     * correctly
     */
    public boolean run(){
        // A brute forced implementation of conflict checker with bugs. Needs
        // to be fixed
        boolean exitProgramCommand = true;

        boolean running = true;
        while (running){
            // Get a hashmap of all functions, with numbered keys to
            // enumerate the choices
            LinkedHashMap<String, String> NumberedKeysToAllowedFunctions =
                    hashMapIt(this.Factory.getAllowedFunctions());

            // Print out enumerated lists
            for (String num : NumberedKeysToAllowedFunctions.keySet()){
                System.out.println(num + ": " + NumberedKeysToAllowedFunctions.get(num));
            }

            InputChecker requestCommand = new InputChecker("Please select a " +
                    "command to execute", new isValidCommand(NumberedKeysToAllowedFunctions));
            String requested = requestCommand.checkCorrectness();

            try {
                // True iff the command has been able to run the allowed
                // function
                exitProgramCommand = runCommand(NumberedKeysToAllowedFunctions.get(requested));
                running = false;
            } catch (InvalidInputException e){
                System.out.println("Command not allowed. Please try again!");
            }
        }

        // Indicates to UI to exit the program.
        return exitProgramCommand;
    }

    /**
     * Sets the command into the Command History.
     *
     * The command history will act as a history of all the commands in order
     * they were added. The setCommands will also execute the command.
     *
     * @param requestedCommand the command that has been requested
     * @return true iff the command has been run properly. False to indicate
     * that the program should exit
     * @exception InvalidInputException Throws invalid Input exception if the
     * input command is invalid
     */
    public boolean runCommand(String requestedCommand) throws InvalidInputException {
        assert this.Factory != null;
        Command theCommand = this.Factory.getCommand(requestedCommand);

        // If the command is to exit the program, it will return false to let
        // UserInterface know to exit the program
        if (theCommand instanceof ExitProgramCommand) {
            return false;
        }

        // Execute the command. Throws InvalidInputException if the command is
        // invalid
        executeCommand(theCommand);
        return true;
    }

    // ============================== Helpers ==================================
    /**
     * Returns a hashmap of the entries in the string array commandList with
     * corresponding integer values from least to greatest.
     *
     * @param commandList the array of strings
     * @return a hashmap of items from strings as values and ascending
     * integers as keys
     */
    private LinkedHashMap<String, String> hashMapIt(String[] commandList){
        LinkedHashMap<String, String> enumerateToCommandMap = new LinkedHashMap<>();
        for (int i = 0; i < commandList.length; i++){
            enumerateToCommandMap.put(String.valueOf(i), commandList[i]);
        }
        return enumerateToCommandMap;
    }

    // ===================== Command Pattern Infrastructure ====================
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
    /**
     * A predicate to determine if the command is a valid input
     */
    private static class isValidCommand extends Predicate {
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
