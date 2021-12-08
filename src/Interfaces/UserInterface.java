package Interfaces;

import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import Helpers.InvalidInputException;
import Helpers.Reformatters;
import InterfaceAdaptors.DatabaseController;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 *
 * An UserInterface class
 * It sets the DatabaseController.
 *
 * === Private Attributes ===
 *  control: The DatabaseController object.
 *
 */
public class UserInterface {
    private final DatabaseController control;

    /**
     * Constructor of the UserInterface.
     * Sets presenter, control and operator.
     */
    public UserInterface() {
        this.control = new DatabaseController("cmd");
    }

    /**
     * Runs the UserInterface.
     *
     * @exception IOException will be raised if the given file, datasource.txt, is not
     * found.
     */
    public void run() throws IOException {
        this.control.configureUserInterface();

        // As long as the program is running
        boolean running = true;
        while (running) {
            System.out.println("\nCurrent datasource: " + this.control.getDataSource());
            // Get a hashmap of all functions, with numbered keys to
            // enumerate the choices
            LinkedHashMap<String, String> NumberedKeysToAllowedFunctions =
                    Reformatters.hashMapIt(control.getAllowedFunctions());


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
                running = control.runCommand(NumberedKeysToAllowedFunctions.get(requested));
            } catch (InvalidInputException e){
                System.out.println("Command not allowed. Please try again!");
            }
        }
    }

    // ========================== Helper Methods ===============================
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
