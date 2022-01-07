package Commands.CreationCommands;

import Commands.Command;
import TimeTableObjects.EventBuilder;
import TimeTableObjects.Events;
import TimeTableObjects.EventObjects.Task;
import Helpers.Constants;
import Helpers.InputCheckers.Predicate;
import Helpers.InputCheckers.InputChecker;
import Helpers.Search;
import Helpers.StringToTime;
import TimeTableContainers.TimeTableManager;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * A command to create a Non-Course Object.
 *
 * === Private Attributes ===
 * manager: The manager that will eventually schedule the object
 * scheduledObject: An non course Event waiting to be scheduled
 */
public class MakeEventCommand implements Command {
    // Some Constants:
    static final String NAME = "Name";
    static final String START_TIME = "Start Time";
    static final String END_TIME = "End Time";
    static final String LOCATION = "Location";
    static final String DATE = "Date";
    static final String TERM = "Term";
    static final String TYPE = "Type";
    static final String YEAR = "Year";

    private final TimeTableManager manager;
    private Object scheduledObject;
    private final EventBuilder factory;

    /**
     * A constructor to set the command
     * @param theManager The manager to connect to
     */
    public MakeEventCommand(TimeTableManager theManager){
        this.manager = theManager;
        this.scheduledObject = null;
        this.factory = new EventBuilder();
    }

    /**
     * Prompts the user to create a timetableObject
     */
    @Override
    public void execute() {
        boolean running = true;

        while (running) {
            HashMap<String, String> responses = promptUser();

            Object toSchedule = getCorrectTimeTableObject(
                    responses.get(NAME),
                    StringToTime.makeTime(responses.get(START_TIME)),
                    StringToTime.makeTime(responses.get(END_TIME)),
                    responses.get(LOCATION),
                    responses.get(DATE),
                    responses.get(TERM),
                    responses.get(YEAR),
                    responses.get(TYPE));

            assert toSchedule instanceof Events || toSchedule instanceof Task;
            if (toSchedule instanceof Events){
                Events obj = (Events) toSchedule;
                if (!manager.hasConflicts(obj)){
                    manager.schedule(obj);
                    scheduledObject = obj;
                    running = false;
                } else {
                    System.out.println("Conflict Found. Try again!");
                }
            } else {
                Task obj = (Task) toSchedule;
                manager.schedule(obj);
                running = false;
            }
        }

        System.out.println("Event Scheduled");
    }

    /**
     * Prompt the user
     *
     * @return a hashmap of the questions and the response of the user
     */
    private HashMap<String, String> promptUser() {
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
                new isTerm()));
        prompts.put(YEAR, new InputChecker("Enter the Year (2021/2022):",
                new isTrivial()));
        prompts.put(TYPE, new InputChecker("Enter the Type of the Object " +
                "(Activity/Task)", new isTrivial()));

        HashMap<String, String> responses = new HashMap<>();

        for (String prompt : prompts.keySet()) {
            responses.put(prompt, prompts.get(prompt).checkCorrectness());
        }

        return responses;
    }

    // ============================= Helper Methods ============================
    /**
     * Return a String representation of the Command
     * @return the String representation
     */
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
     * A helper method for scheduling events.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @param theLocation the location
     * @param theDate the date
     * @param term the term
     * @param year the year
     * @param type the type of object
     * @return event "cast" to the correct type.
     */
    public Object getCorrectTimeTableObject(String name,
                                            LocalTime startTime,
                                            LocalTime endTime,
                                            String theLocation,
                                            String theDate,
                                            String term,
                                            String year,
                                            String type) {
        // Creates the Activity
        switch (type){
            case Constants.ACTIVITY:
                Scanner descriptionScanner = new Scanner(System.in);
                System.out.println("Enter Description: ");
                String description = descriptionScanner.nextLine();

                this.factory.calibrate(name, description, startTime, endTime,
                        theDate, term + " " + year);
                return this.factory.getActivity();
            case Constants.TASK:
                Task task = new Task(name, theDate, term + " " + year);
                task.setName(name);
                task.addToName(" at " + theLocation);
                return task;
            // ...
            // Add more types of events here!
            default:
                return null;
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
     * A predicate to check if an input is anything
     */
    private static class isTrivial extends Predicate {
        @Override
        public boolean run(String prompt) {
            return true;
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
}
