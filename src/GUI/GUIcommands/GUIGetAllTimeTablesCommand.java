package GUI.GUIcommands;

import Commands.Command;
import GUI.userview.TimeTableScreen;
import TimeTableContainers.TimeTable;
import GUI.userview.AbstractScreen;
import TimeTableContainers.TimeTableManager;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * A command object to get all the timetables
 *
 * === Attributes ===
 * screen: The window viewed by the user.
 */
public class GUIGetAllTimeTablesCommand implements Command {
    private final AbstractScreen screen;
    private final TimeTableManager manager;

    /**
     * A constructor to indicate what manager to get all the commands from.
     *
     * @param screen is a window viewed by the user.
     */
    public GUIGetAllTimeTablesCommand(AbstractScreen screen,
                                      TimeTableManager manager){
        this.screen = screen;
        this.manager = manager;
    }

    /**
     * Get all the timetables in the manager and prints them in an
     * aesthetically pleasing way
     */
    @Override
    public void execute() {
//        HashMap<String, TimeTable> timetables = new HashMap<>();
//        Set<String> terms = manager.getTerms();
//
//        for (String term : terms) {
//            timetables.put(term, manager.getTimetable(term));
//        }




        TimeTable[] output = manager.getAllTimeTables();
        for (TimeTable table : output) {
            // create a tab for the timetable

            // display timetable
            System.out.println(table.toString());
        }
    }

    /**
     * Return a String representation of the Command
     * @return the String representation
     */
    @Override
    public String toString() {
        return "Extracted all Saved TimeTables";
    }
}
