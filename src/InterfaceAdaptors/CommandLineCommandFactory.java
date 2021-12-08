package InterfaceAdaptors;

import Commands.FunctionCommands.*;
import Commands.Command;
import Commands.CreationCommands.GetAllTimeTablesCommand;
import Commands.CreationCommands.MakeCourseCommand;
import Commands.CreationCommands.MakeEventCommand;
import Commands.CreationCommands.AddTimeTableCommand;
import Commands.RemovalCommands.RemoveEventCommand;
import Commands.RemovalCommands.RemoveTimeTable;
import Commands.CreationCommands.PrintHistoryCommand;
import Helpers.InvalidInputException;
import Helpers.Constants;

/**
 *
 * A factory class to create the individual commands of the class.
 *
 */
public class CommandLineCommandFactory extends CommandFactory {
    /**
     * Constructor. Sets the TimeTable Manager and DataSource of the file
     *
     * @param theController the database controller that this CommandFactory is set to.
     */
    public CommandLineCommandFactory(DatabaseController theController){
        super(theController);
        this.setAllowedFunctions(new String[]{Constants.SCHEDULE_COURSE,
                Constants.SCHEDULE_EVENT,
                Constants.REMOVE_EVENT,
                Constants.LOAD_DATA,
                Constants.DOWNLOAD_TIMETABLE,
                Constants.GET_ALL_TIMETABLE,
                Constants.SOLVE_TIMETABLE,
                Constants.ADD_TIMETABLE,
                Constants.REMOVE_TIMETABLE,
                Constants.PRINT_HISTORY,
                Constants.EXIT});
    }

    // ========================= helper functions ===========================
    /**
     * Helper method to return the corresponding command object
     * to whatever has been inputted by the user in command line. Please see
     * `DatabaseController` for how the command objects will be used
     *
     * @param inputCommand The STRING input command of the user. IT MUST
     *                     CORRESPOND TO THE STATIC CONSTANTS ABOVE
     * @return The correct command object
     * @throws InvalidInputException If the inputCommand is invalid, throw this!
     */
    @Override
    public Command getCommand(String inputCommand) throws InvalidInputException {
        switch (inputCommand) {
            case Constants.SCHEDULE_COURSE:
                return new MakeCourseCommand(courseManager, dataSource);
            case Constants.SCHEDULE_EVENT:
                return new MakeEventCommand(courseManager);
            case Constants.REMOVE_EVENT:
                return new RemoveEventCommand(courseManager);
            case Constants.GET_ALL_TIMETABLE:
                return new GetAllTimeTablesCommand(courseManager);
            case Constants.SOLVE_TIMETABLE:
                return new SolverCommand(courseManager, dataSource);
            case Constants.ADD_TIMETABLE:
                return new AddTimeTableCommand(courseManager);
            case Constants.REMOVE_TIMETABLE:
                return new RemoveTimeTable(courseManager);
            case Constants.PRINT_HISTORY:
                return new PrintHistoryCommand(controller);
            case Constants.LOAD_DATA:
                return new LoadDataCommand();
            case Constants.DOWNLOAD_TIMETABLE:
                return new DownloadDataCommand(courseManager);
            case Constants.EXIT:
                return new ExitProgramCommand();
            // ... ADD YOUR NEW OBJECTS HERE!

            // The command is invalid
            default:
                throw new InvalidInputException();
        }
    }
}
