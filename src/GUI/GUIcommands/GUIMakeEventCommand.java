package GUI.GUIcommands;

import Commands.Command;
import Helpers.Constants;
import Helpers.InputCheckers.Predicate;
import Helpers.Search;
import Helpers.StringToTime;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;
import GUI.userview.ConflictDialog;
import GUI.userview.ScheduleEventScreen;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * A command to create a Non-Course Object.
 *
 * === Private Attributes ===
 * scheduledObject: An non-course Event waiting to be scheduled
 * scheduleEventScreen: The window viewed by the user when scheduling non-course Event
 */
public class GUIMakeEventCommand implements Command {
    // Some Constants:
    final String NAME = "Name";
    final String START_TIME = "Start Time";
    final String END_TIME = "End Time";
    final String LOCATION = "Location";
    final String DATE = "Date";
    final String TERM = "Term";
    final String YEAR = "Year";
    final String TYPE = "Type";
    final String DESCRIPTION = "description";

    private Events scheduledObject;
    private final ScheduleEventScreen scheduleEventScreen;

    /**
     * A constructor to set the command
     * @param scheduleEventScreen is the window viewed by the user when scheduling non-course Event
     */
    public GUIMakeEventCommand(ScheduleEventScreen scheduleEventScreen){
        this.scheduledObject = null;
        this.scheduleEventScreen = scheduleEventScreen;
    }

    public TimeTableManager getManager(){
        return scheduleEventScreen.getController().getFactory().getCourseManager();
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
                    responses.get(TYPE),
                    responses.get(DESCRIPTION));

            assert toSchedule instanceof Events || toSchedule instanceof Task;
            TimeTableManager manager = scheduleEventScreen.getController().getFactory().getCourseManager();
            if (toSchedule instanceof Events){
                Events obj = (Events) toSchedule;
                if (!manager.hasConflicts(obj)){
                    manager.schedule(obj);
                    scheduledObject = obj;
                    running = false;
                } else {
                    ConflictDialog cd = new ConflictDialog();
                    cd.run();
                    running = false;
                }
            } else {
                Task obj = (Task) toSchedule;
                manager.schedule(obj);
                running = false;
            }
        }
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
        prompts.put(YEAR, scheduleEventScreen.getYear());
        prompts.put(TYPE,scheduleEventScreen.getEventType());
        prompts.put(DESCRIPTION,scheduleEventScreen.getDescription());
        return prompts;
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
    public static Object getCorrectTimeTableObject(String name,
                                                   LocalTime startTime,
                                                   LocalTime endTime,
                                                   String theLocation,
                                                   String theDate,
                                                   String term,
                                                   String year,
                                                   String type,
                                                   String description) {
        // Creates the Activity
        switch (type){
            case Constants.ACTIVITY:
                String activityTerm = term + " " + year;
                Activity activity = new Activity(startTime, endTime, theDate, activityTerm);
                activity.setDescription(description);
                activity.setName(name);
                return activity;
            case "Reminder":
                String taskTerm = term + " " + year;
                Task task = new Task(name, theDate, taskTerm);
                task.setName(name);
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
