package Commands.CreationCommands;

import Commands.Command;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;

/**
 *
 * A command object to get all the timetables
 *
 * === Attributes ===
 * manager: The manager with all the timetables.
 */
public class GetAllTimeTablesCommand implements Command {
    private final TimeTableManager manager;

    /**
     * A constructor to indicate what manager to get all the commands from.
     *
     * @param theManager The manager to get all the commands from.
     */
    public GetAllTimeTablesCommand(TimeTableManager theManager){
        this.manager = theManager;
    }

    /**
     * Get all the timetables in the manager and prints them in an
     * aesthetically pleasing way
     */
    @Override
    public void execute() {
        TimeTable[] output = manager.getAllTimeTables();
        for (TimeTable table : output) {
            // Prints out the timetable
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
