package InterfaceAdaptors;

import Commands.Command;
import Commands.CreationCommands.AddTimeTableCommand;
import Commands.FunctionCommands.SolverCommand;
import Commands.RemovalCommands.RemoveEventCommand;
import Commands.RemovalCommands.RemoveTimeTable;
import Helpers.Constants;
import Helpers.InvalidInputException;
import GUI.GUIcommands.*;
import GUI.userview.*;

/**
 *
 * A factory class to create the individual commands of the class.
 *
 * === Attributes ===
 * screen: The window viewed by the user
 */
public class GUICommandFactory extends CommandFactory {
    private AbstractScreen screen;

    public GUICommandFactory(DatabaseController controller){
        super(controller);
        setAllowedFunctions(new String[]{
                Constants.SCHEDULE_COURSE,
                Constants.SCHEDULE_EVENT,
                Constants.REMOVE_EVENT,
                Constants.LOAD_DATA,
                Constants.DOWNLOAD_TIMETABLE,
                Constants.GET_ALL_TIMETABLE,
                Constants.SOLVE_TIMETABLE,
                Constants.ADD_TIMETABLE,
                Constants.REMOVE_TIMETABLE,
                Constants.PRINT_HISTORY,
                Constants.EXIT
        });
        this.screen = null;
    }

    /**
     * Helper method to return the corresponding command object
     * to whatever has been inputted by the user in GUI. Please see
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
                return new GUIMakeCourseCommand((ScheduleCourseScreen) screen);
            case Constants.SCHEDULE_EVENT:
                return new GUIMakeEventCommand((ScheduleEventScreen) screen);
            case Constants.REMOVE_EVENT:
                return new RemoveEventCommand(courseManager);
            case Constants.GET_ALL_TIMETABLE:
                return new GUIGetAllTimeTablesCommand(screen);
            case Constants.SOLVE_TIMETABLE:
                return new SolverCommand(courseManager, dataSource);
            case Constants.ADD_TIMETABLE:
                return new AddTimeTableCommand(courseManager);
            case Constants.REMOVE_TIMETABLE:
                return new RemoveTimeTable(courseManager);
            case Constants.LOAD_DATA:
                return new GUILoadDataCommand((LoadScreen) screen);
            case Constants.DOWNLOAD_TIMETABLE:
                return new GUIDownloadDataCommand((SaveScreen) screen);
            default:
                throw new InvalidInputException();
        }
    }

    /** Set the screen to one of AbstractScreen's subclasses.
     *
     * @param screen is a subclass object of AbstracScreen, it is a user viewable window.
     */
    public void setScreen(AbstractScreen screen) {
        this.screen = screen;
    }
}
