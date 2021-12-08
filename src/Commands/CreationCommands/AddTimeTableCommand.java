package Commands.CreationCommands;

import Commands.Command;
import Helpers.InputCheckers.InputChecker;
import TimeTableContainers.TimeTableManager;

import java.util.*;

/**
 *
 * A command to add a TimeTable Object.
 *
 * === Private Attributes ===
 * manager: The manager that the TimeTable will be added to
 */
public class AddTimeTableCommand implements Command {
    private final TimeTableManager manager;

    /**
     * A constructor to initialize this command
     *
     * @param manager the Manager to send to
     */
    public AddTimeTableCommand(TimeTableManager manager) {
        this.manager = manager;
    }

    /**
     * Creates a TimeTable with the given term from user input
     */
    @Override
    public void execute() {
        boolean validTimeTable = true;
        while (validTimeTable) {
            // Get the TimeTable term from User
            String[] question = {"What term would you like to add a TimeTable for? (eg Spring 2021)"};
            String response = InputChecker.getQuestionsAnswers(question)[0];

            Set<String> terms = manager.getTerms();
            if (terms.contains(response)) {
                System.out.println("There already exists a TimeTable with that term. Please try another term!");
            }
            else {
                manager.addTimeTable(response);
                String[] question2 = {"Would you like to add another TimeTable? (true/false)"};
                String response2 = InputChecker.getQuestionsAnswers(question2)[0];
                if (!Boolean.parseBoolean(response2)) {
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
        return "Added a TimeTable";
    }
}
