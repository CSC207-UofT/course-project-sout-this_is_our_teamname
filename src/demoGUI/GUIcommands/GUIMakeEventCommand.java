package demoGUI.GUIcommands;

import Commands.Command;
import Commands.CreationCommands.MakeEventCommand;
import Helpers.Constants;
import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;
import Helpers.Search;
import Helpers.StringToTime;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;
import demoGUI.userview.ScheduleEventScreen;

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
 * scheduledObject: An non course Event waiting to be scheduled
 * managerChanged: Whether the TimeTableManager is changed
 */
public class GUIMakeEventCommand implements Command {
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
    private ScheduleEventScreen scheduleEventScreen;

    /**
     * A constructor to set the command
     * @param scheduleEventScreen //TODO finish here
     */
    public GUIMakeEventCommand(TimeTableManager theManager){
        this.manager = theManager;
        this.scheduledObject = null;
    }

    /**
     * Prompts the user to create a timetableObject
     */
    @Override
    public void execute() {
        boolean running = true;
        while (running) {
            HashMap<String, String> responses = promptUser();

            Events toSchedule = getCorrectTimeTableObject(
                    responses.get(NAME),
                    StringToTime.makeTime(responses.get(START_TIME)),
                    StringToTime.makeTime(responses.get(END_TIME)),
                    responses.get(LOCATION),
                    responses.get(DATE),
                    responses.get(TERM),
                    responses.get(TYPE));

            assert toSchedule != null;
            if (!manager.hasConflicts(toSchedule)){
                System.out.println("if not");
                scheduledObject = toSchedule;
                System.out.println("to be scheduled");
                boolean bool = manager.schedule(toSchedule);
                System.out.println(bool);
                System.out.println(manager.toString());
                running = false;
            }
            else {
                System.out.println("Conflict Found. Try again!");
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
        LinkedHashMap<String, String> prompts = new LinkedHashMap<>();
        prompts.put(NAME, scheduleEventScreen.getName());
        prompts.put(START_TIME,scheduleEventScreen.getStartTime());
        prompts.put(END_TIME,scheduleEventScreen.getEndTime());
        prompts.put(LOCATION,scheduleEventScreen.getLocations());
        prompts.put(DATE,scheduleEventScreen.getDate());
        prompts.put(TERM,scheduleEventScreen.getTerm());
        prompts.put(TYPE,scheduleEventScreen.getEventType());
        prompts.put(DESCRIPTION,scheduleEventScreen.getDescription());
;
        return prompts;
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
     * A helper method for scheduling events.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @param theLocation the location
     * @param theDate the date
     * @param term the term
     * @param type the type of object
     * @return event "cast" to the correct type.
     */
    public static Events getCorrectTimeTableObject(String name,
                                                   LocalTime startTime,
                                                   LocalTime endTime,
                                                   String theLocation,
                                                   String theDate,
                                                   String term,
                                                   String type) {
        // Creates the Activity
        switch (type){
            case Constants.ACTIVITY:
                Scanner descriptionScanner = new Scanner(System.in);
                System.out.println("Enter Description: ");
                return new Activity(startTime, endTime, theDate, term,
                        descriptionScanner.nextLine());
            case Constants.TASK:
                Task task = new Task(startTime, endTime, theDate, term);
                task.addToName(theLocation);
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

    /**
     * A predicate to check if an input is a boolean
     */
    private static class isBoolean extends Predicate{

        @Override
        public boolean run(String prompt) {
            return prompt.equals("true") || prompt.equals("false");
        }
    }
}
