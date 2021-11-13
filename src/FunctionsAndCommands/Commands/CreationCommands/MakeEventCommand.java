package FunctionsAndCommands.Commands.CreationCommands;

import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.Events;
import EntitiesAndObjects.TimeTableObjects.Task;
import FunctionsAndCommands.Commands.Command;
import GlobalHelpers.Constants;
import GlobalHelpers.InputCheckers.Predicate;
import GlobalHelpers.InputCheckers.InputChecker;
import GlobalHelpers.Search;
import GlobalHelpers.StringToTime;
import TimeTableStuff.TimeTableManager;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A command to create a Non-Course Object.
 *
 * === Private Attributes ===
 * manager: The manager that will eventually schedule the object
 */
public class MakeEventCommand implements Command {
    // Some Constants:
    final String NAME = "Name";
    final String START_TIME = "Start Time";
    final String END_TIME = "End Time";
    final String LOCATION = "Location";
    final String DATE = "Date";
    final String TERM = "Term";
    final String TYPE = "Type";

    private final TimeTableManager manager;
    private Events scheduledObject;

    /**
     * A constructor to set the command
     * @param theManager The manager to connect to
     */
    public MakeEventCommand(TimeTableManager theManager){
        this.manager = theManager;
        this.scheduledObject = null;
    }

    /**
     * Prompts the user to create a timetableObject
     */
    @Override
    public void execute() {
        LinkedHashMap<String, InputChecker> prompts = new LinkedHashMap<>();
        prompts.put(NAME, new InputChecker("Enter a name for an object " +
                "(eg; Dinner with Prof Gries and Friends)", new isTrivial()));
        prompts.put(START_TIME, new InputChecker("Enter the Start Time (in" +
                " a 12h clock format - hh:mm[AM/PM] eg: 10:00AM or 09:00PM. " +
                "No space between time and AM/PM)", new isTime()));
        prompts.put(END_TIME, new InputChecker("Enter the End Time (in " +
                " a 12h clock format - hh:mm[AM/PM] eg: 10:00AM or 09:00PM. " +
                "No space between time and AM/PM)", new isTime()));
        prompts.put(LOCATION, new InputChecker("Enter the Location (eg; " +
                "MY150, Home, Middle of Nowhere)", new isTrivial()));
        prompts.put(DATE, new InputChecker("Enter the Day of the week (eg;" +
                " Monday, Tuesday, Wednesday, etc.)", new isDate()));
        prompts.put(TERM, new InputChecker("Enter the Term (Fall/Winter)",
                new isTrivial())); // TODO Fix me!
        prompts.put(TYPE, new InputChecker("Enter the Type of the Object " +
                "(Event, Activity, etc ...", new isTrivial())); // TODO Fix me!

        HashMap<String, String> responses = new HashMap<>();

        for (String prompt : prompts.keySet()) {
            responses.put(prompt, prompts.get(prompt).checkCorrectness());
        }

        Events toSchedule = getCorrectTimeTableObject(
                StringToTime.makeTime(responses.get(START_TIME)),
                StringToTime.makeTime(responses.get(END_TIME)),
                responses.get(LOCATION),
                responses.get(DATE),
                responses.get(TERM),
                responses.get(TYPE));

        this.scheduledObject = toSchedule;

        assert toSchedule != null;
        manager.schedule(toSchedule);
    }

    // ============================= Helper Methods ============================
    @Override
    public String toString() {
        if (this.hasScheduled()){
            return "Scheduled the item" + this.scheduledObject.toString();
        } else {
            return "No Course Scheduled";
        }
    }

    /**
     * Return if there has already been an object been scheduled
     * @return true iff there has been a course scheduled.
     */
    protected boolean hasScheduled(){
        return scheduledObject != null;
    }

    /**
     * A helper method for schedule (Events). A Factory to return the event in
     * the correct type.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @param theLocation the location
     * @param theDate the date
     * @param term the term
     * @param type the type of object
     * @return event "cast" to the correct type.
     */
    private Events getCorrectTimeTableObject(LocalTime startTime,
                                             LocalTime endTime,
                                             String theLocation,
                                             String theDate,
                                             String term,
                                             String type) {
        // Creates the Activity
        if (type.equals(Constants.ACTIVITY)){
            // Asks the user for the description of the object
            Scanner descriptionScanner = new Scanner(System.in);
            System.out.println("Please provide a description of your life " +
                    "activity: ");

            return new Activity(startTime, endTime, theLocation, theDate,
                    term, descriptionScanner.nextLine());
        // Creates the task
        } else if (type.equals(Constants.TASK)){
            return new Task(startTime, endTime, theLocation, theDate, term);
        }

        // ...
        // Add more types of events here!

        else {
            return null;
        }
    }

    // ====================== Predicates =======================================
    private static class isTime extends Predicate {
        @Override
        public boolean run(String prompt) {
            return Pattern.matches("^[0-2][0-9]:[0-5][0-9][AP]M$",
                    prompt);
        }
    }

    private static class isTrivial extends Predicate {
        @Override
        public boolean run(String prompt) {
            return true;
        }
    }

    private static class isDate extends Predicate {
        @Override
        public boolean run(String prompt) {
            String[] validDates = {Constants.MONDAY, Constants.TUESDAY,
                    Constants.WEDNESDAY, Constants.THURSDAY, Constants.FRIDAY
                    , Constants.SATURDAY, Constants.SUNDAY};
            return Search.BinarySearch(prompt, validDates);
        }
    }
}
