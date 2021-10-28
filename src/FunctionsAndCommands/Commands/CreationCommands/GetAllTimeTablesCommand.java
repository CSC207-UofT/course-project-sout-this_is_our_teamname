package FunctionsAndCommands.Commands.CreationCommands;

import FunctionsAndCommands.Commands.Command;
import TimeTableStuff.TimeTable;
import TimeTableStuff.TimeTableManager;

/**
 * A command object to get all the timetables
 */
public class GetAllTimeTablesCommand extends Command {
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
}
