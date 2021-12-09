package Commands.RemovalCommands;

import Commands.Command;
import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import TimeTableContainers.TimeTableManager;


/**
 *
 * A command to remove a TimeTable Object.
 *
 * === Private Attributes ===
 * manager: The manager that the TimeTable will be removed from
 */
public class RemoveTimeTable implements Command {
    private final TimeTableManager manager;

    /**
     * A constructor to initialize this command
     *
     * @param manager the Manager to send to
     */
    public RemoveTimeTable(TimeTableManager manager) {
        this.manager = manager;
    }

    /**
     * Removes the TimeTable with the given term from user input
     */
    @Override
    public void execute() {
        // Get the TimeTable term from User
        InputChecker response = new InputChecker("Which TimeTable would you like to remove? " +
                "Enter the term name. (eg Fall 2021/Winter 2021)",
                new isValidTerm(manager));
        String selected = response.checkCorrectness();

        // Remove the TimeTable with given term
        manager.removeTimeTable(selected);
        System.out.println("Success!");
    }

    /**
     * Return a String representation of the Command
     * @return the String representation
     */
    @Override
    public String toString() {
        return "Removed a TimeTable";
    }

    // ======================== Predicates Classes =============================
    /**
     * A predicate to check if the TimeTable term is correct
     *
     * === Attributes ===
     * term: The term of the TimeTable
     * manager: The TimeTableManager holding all the TimeTables
     */
    private static class isValidTerm extends Predicate {
        private final TimeTableManager manager;

        /**
         * Constructor.
         *
         * @param manager The manager to check terms in.
         */
        private isValidTerm(TimeTableManager manager){
            this.manager = manager;
        }

        /**
         * Runs the predicate
         *
         * @param prompt the prompt to ask the user
         * @return true iff this term exists in the TimeTableManager.
         */
        @Override
        public boolean run(String prompt) {
            return manager.getTerms().contains(prompt);
        }
    }
}
