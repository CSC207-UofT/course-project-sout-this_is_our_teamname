package Commands.RemovalCommands;

import Commands.Command;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTableManager;

import java.util.Set;

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
        boolean validTimeTable = true;
        while (validTimeTable) {
            // Get the TimeTable term from User
            String[] question = {"Which TimeTable would you like to remove? Enter the term name." +
                    " (eg Fall/Winter)"};
            String response = InputChecker.getQuestionsAnswers(question)[0];

            Set<String> terms = manager.getTerms();
            if (terms.contains(response)) {
                manager.removeTimeTable(response);
                String[] anotherTimeTable = {"Success! Would you like to remove another TimeTable? (true/false)"};
                String anotherResponse = InputChecker.getQuestionsAnswers(anotherTimeTable)[0];
                if (!Boolean.parseBoolean(anotherResponse)) {
                    validTimeTable = false;
                }
            } else {
                String[] incorrectTerm = {"There is no TimeTable with that term. Would you like to try again? " +
                        "(true/false)"};
                String incorrectTermResponse = InputChecker.getQuestionsAnswers(incorrectTerm)[0];
                if (!Boolean.parseBoolean(incorrectTermResponse)) {
                    validTimeTable = false;
                }
            }
        }
    }

    /**
     * Return a String representation of the Command
     * @return the String representation
     */
    @Override
    public String toString() {
        return "Removed a TimeTable";
    }
}

