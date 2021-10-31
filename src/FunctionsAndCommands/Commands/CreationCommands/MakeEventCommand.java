package FunctionsAndCommands.Commands.CreationCommands;

import EntitiesAndObjects.NonCourseObject;
import FunctionsAndCommands.Commands.Command;
import GlobalHelpers.StringToTime;
import TimeTableStuff.TimeTableManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

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
    private NonCourseObject scheduledObject;

    /**
     * A constructor to set the command
     * @param theManager The manager to connect to
     */
    public MakeEventCommand(TimeTableManager theManager){
        this.manager = theManager;
        this.scheduledObject = null;
    }

    /**
     * Return if there has already been an object been scheduled
     * @return true iff there has been a course scheduled.
     */
    protected boolean hasScheduled(){
        return scheduledObject != null;
    }

    /**
     * Prompts the user to create a timetableObject
     */
    @Override
    public void execute() {
        LinkedHashMap<String, String> prompts = new LinkedHashMap<>();
        prompts.put(NAME, "Enter a name for an object (eg; Dinner with Prof " +
                "Gries and Friends)");
        prompts.put(START_TIME, "Enter the Start Time (in a 12h clock format." +
                " eg: 10:00AM or 9:00PM. No space between time and AM/PM)");
        prompts.put(END_TIME, "Enter the End Time (in a 12h clock format. (" +
                "eg: 10:00AM or 9:00PM. No space between time and AM/PM)");
        prompts.put(LOCATION, "Enter the Location (eg; MY150, Home, Middle of" +
                " Nowhere)");
        prompts.put(DATE, "Enter the Day of the week (eg; Monday, Tuesday, " +
                "Wednesday, etc.)");
        prompts.put(TERM, "Enter the Term (Fall/Winter - NB will add more in " +
                "later Phases)");
        prompts.put(TYPE, "Enter the Type of the Object (Life, " +
                "DescriptionlessLife...");

        HashMap<String, String> responses = new HashMap<>();

        for (String prompt : prompts.keySet()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(prompts.get(prompt) + ": ");
            responses.put(prompt, scanner.nextLine());
        }

        NonCourseObject toSchedule = new NonCourseObject(
                StringToTime.makeTime(responses.get(START_TIME)),
                StringToTime.makeTime(responses.get(END_TIME)),
                responses.get(LOCATION),
                responses.get(DATE),
                responses.get(TERM),
                responses.get(TYPE));

        this.scheduledObject = toSchedule;

        manager.schedule(toSchedule);
    }

    @Override
    public String toString() {
        if (this.hasScheduled()){
            return "Scheduled the item" + this.scheduledObject.toString();
        } else {
            return "No Course Scheduled";
        }
    }
}
