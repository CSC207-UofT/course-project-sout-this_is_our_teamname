package Commands.RemovalCommands;

import Helpers.Constants;
import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import Helpers.Search;
import Helpers.StringToTime;
import TimeTableContainers.TimeTableManager;
import Commands.Command;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 *
 * A command to remove an Event.
 *
 * === Private Attributes ===
 * manager: The manager that the Event will be removed from
 */
public class RemoveEventCommand implements Command {
    private final TimeTableManager manager;

    /**
     * A constructor to initialize this command
     *
     * @param manager the Manager to send to
     */
    public RemoveEventCommand(TimeTableManager manager) {
        this.manager = manager;
    }

    /**
     * Removes the given Event object from user input.
     */
    @Override
    public void execute() {
        boolean success = false;
        while (!success) {
            LinkedHashMap<String, InputChecker> prompts = new LinkedHashMap<>();
            prompts.put("StartTime", new InputChecker("Enter the Start Time of this Event(in" +
                    " a 12h clock format - hh:mm[AM/PM] eg: 10:00AM or 09:00PM. " +
                    "No space between time and AM/PM)", new isTime()));
            prompts.put("EndTime", new InputChecker("Enter the End Time of this Event(in " +
                    " a 12h clock format - hh:mm[AM/PM] eg: 10:00AM or 09:00PM. " +
                    "No space between time and AM/PM)", new isTime()));
            prompts.put("Date", new InputChecker("Enter the day of the week this Event is on?" +
                    "(eg; Monday/Tuesday/Wednesday/etc.)", new isDate()));
            prompts.put("Term", new InputChecker("Enter the Term of this Event (Fall/Winter)",
                    new isTerm()));

            HashMap<String, String> checkedResponse = new HashMap<>();

            for (String prompt : prompts.keySet()) {
                checkedResponse.put(prompt, prompts.get(prompt).checkCorrectness());
            }
            LocalTime start = StringToTime.makeTime(checkedResponse.get("StartTime"));
            LocalTime end = StringToTime.makeTime(checkedResponse.get("EndTime"));
            assert start != null;
            assert end != null;
            success = manager.getTimetable(checkedResponse.get("Term")).remove(start, end,
                    (checkedResponse.get("Date")));
            if (success) {
                System.out.println("Event removed!");
            }
            if (!success) {
                InputChecker tryAgain = new InputChecker("There was no Event from that start time to " +
                        "that end time. Would you like to try again? (true/false)",
                        new RemoveEventCommand.isValidBoolean());
                String correctTryAgain = tryAgain.checkCorrectness();
                if (correctTryAgain.equals("false")) {
                    success = true;
                }
            }
        }
    }

    // ======================== Predicates Classes =============================
    /**
     * A predicate to check if User wants to try again
     */
    private static class isValidBoolean extends Predicate {

        /**
         * Constructor.
         */
        private isValidBoolean(){ }

        /**
         * Runs the predicate
         *
         * @param prompt the prompt to ask the user
         */
        @Override
        public boolean run(String prompt) {
            return prompt.equals("true") || prompt.equals("false");
        }
    }

    // ====================== Predicates =======================================
    /**
     * A predicate to check if an input is a time
     */
    private static class isTime extends Predicate {
        @Override
        public boolean run(String prompt) {
            return Pattern.matches("^[0-2][0-9]:[0-5][0-9][AP]M$",
                    prompt);
        }
    }

    /**
     * A predicate to check if an input is a date
     */
    private static class isDate extends Predicate {
        @Override
        public boolean run(String prompt) {
            String[] validDates = {Constants.MONDAY, Constants.TUESDAY,
                    Constants.WEDNESDAY, Constants.THURSDAY, Constants.FRIDAY
                    , Constants.SATURDAY, Constants.SUNDAY};
            return Search.BinarySearch(prompt, validDates);
        }
    }

    /**
     * A predicate to check if an input is a term
     */
    private static class isTerm extends Predicate {
        @Override
        public boolean run(String prompt) {
            return prompt.equals(Constants.FALL) ||
                    prompt.equals(Constants.WINTER);
        }
    }

    /**
     * Return a String representation of the Command
     * @return the String representation
     */
    @Override
    public String toString() {
        return "Removed an Event";
    }

}
