package GUI.GUIcommands;

import Commands.Command;
import TimeTableContainers.TimeTable;
import GUI.userview.AbstractScreen;

/**
 *
 * A command object to get all the timetables
 *
 * === Attributes ===
 * screen: The window viewed by the user.
 */
public class GUIGetAllTimeTablesCommand implements Command {
    private final AbstractScreen screen;

    /**
     * A constructor to indicate what manager to get all the commands from.
     *
     * @param screen is a window viewed by the user.
     */
    public GUIGetAllTimeTablesCommand(AbstractScreen screen){
        this.screen = screen;
    }

    /**
     * Get all the timetables in the manager and prints them in an
     * aesthetically pleasing way
     */
    @Override
    public void execute() {
        TimeTable[] output = screen.getController().getFactory().getCourseManager().getAllTimeTables();
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