package InterfaceAdaptors;

import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import Helpers.InvalidInputException;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Presenter {
    private final DatabaseController control;

    public Presenter(){
        this.control = new DatabaseController();
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
                    control.getAllowedFunctions();


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
                exitProgramCommand =
                        control.runCommand(NumberedKeysToAllowedFunctions.get(requested));
                running = false;
            } catch (InvalidInputException e){
                System.out.println("Command not allowed. Please try again!");
            }
        }

        // Indicates to UI to exit the program.
        return exitProgramCommand;
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
