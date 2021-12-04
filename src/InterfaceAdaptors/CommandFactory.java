package InterfaceAdaptors;

import Commands.FunctionCommands.SolverCommand;
import Commands.FunctionCommands.ExitProgramCommand;
import DataGetting.CourseGetter;
import Commands.Command;
import Commands.CreationCommands.GetAllTimeTablesCommand;
import Commands.CreationCommands.MakeCourseCommand;
import Commands.CreationCommands.MakeEventCommand;
import Commands.CreationCommands.AddTimeTableCommand;
import Commands.RemovalCommands.RemoveEventCommand;
import Commands.RemovalCommands.RemoveTimeTable;
import Commands.CreationCommands.PrintHistoryCommand;
import Commands.FunctionCommands.DownloadDataCommand;
import Commands.FunctionCommands.LoadDataCommand;
import Helpers.InvalidInputException;
import TimeTableContainers.TimeTableManager;
import demoGUI.GUIcommands.GUIMakeCourseCommand;
import demoGUI.GUIcommands.GUIMakeEventCommand;
import Helpers.Constants;
import demoGUI.userview.AbstractScreen;
import demoGUI.userview.ScheduleEventScreen;

/**
 * A factory class to create the individual commands of the class.
 *
 * === Attributes ===
 * courseManager: The TimetableManager to connect to
 * dataSource: The Data Getter to get the data from
 * controller: The controller that this is connected to
 * allowedFunctions: The list of allowed functions for the program as set out
 *  by the OperatorInterface
 */
public class CommandFactory {
    private TimeTableManager courseManager;
    private CourseGetter dataSource;
    private final DatabaseController controller;
    private String[] allowedFunctions;
    private AbstractScreen screen;


    /**
     * Constructor. Sets the TimeTable Manager and DataSource of the file
     *
     * @param theController the database controller that this CommandFactory
     *                      is set to.
     */
    public CommandFactory(DatabaseController theController){
        this.courseManager = new TimeTableManager();
        this.dataSource = null;
        this.controller = theController;

        this.allowedFunctions = new String[]{
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
        };
    }

    /**
     * This is the Factory that will create all the command objects. PLEASE
     * ADD TO THIS CLASS ALL NEW FUNCTIONS THAT YOU ADD!!!
     *
     * What this should do is it will return the corresponding command object
     * to whatever has been inputted by the user. Please see
     * `DatabaseController` for how the command objects will be used
     *
     * @param inputCommand The STRING input command of the user. IT MUST
     *                     CORRESPOND TO THE STATIC CONSTANTS ABOVE
     * @return The correct command object
     * @throws InvalidInputException If the inputCommand is invalid, throw this!
     */
    public Command getCommand(String inputCommand) throws InvalidInputException {
        assert this.dataSource != null && this.courseManager != null;

        if (controller.getCurrentUI().equals("cmd")) {
            return getCmdCommand(inputCommand);
        }
        else {
            return getGUICommand(inputCommand);
        }
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
    private Command getCmdCommand(String inputCommand) throws InvalidInputException {
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
    private Command getGUICommand(String inputCommand) throws InvalidInputException {
        switch (inputCommand) {
            case Constants.SCHEDULE_COURSE:
                return new GUIMakeCourseCommand(courseManager, dataSource);
            case Constants.SCHEDULE_EVENT:
                return new GUIMakeEventCommand((ScheduleEventScreen) screen);
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
//            case Constants.LOAD_DATA:
//                return new LoadDataCommand();
//            case Constants.DOWNLOAD_TIMETABLE:
//                return new DownloadDataCommand(courseManager);
                //no return statement error blocker, default return will never be reached.
            default:
                throw new InvalidInputException();
        }
    }


    // ========================= Setters and Getters ===========================

    /** Set the screen to one of AbstractScreen's subclasses.
     *
     * @param screen is a subclass object of AbstracScreen, it is a user viewable window.
     */
    public void setScreen(AbstractScreen screen) {this.screen = screen;}

    public TimeTableManager getCourseManager() {
        return courseManager;
    }

    /**
     * Returns a string array of all the allowable functions of the program
     *
     * @return a string array of all the allowed functions of the program
     */
    public String[] getAllowedFunctions() {
        return allowedFunctions;
    }

    /**
     * Sets the DataGetter to connect to
     *
     * @param theDataSource the DataGetter to connect to
     */
    public void setDataSource(CourseGetter theDataSource){
        this.dataSource = theDataSource;
    }

    /**
     * Sets the AllowedFunctions to connect to
     *
     * @param newAllowedFunction the AllowedFunction to connect to
     */
    public void setAllowedFunctions(String[] newAllowedFunction) {
        this.allowedFunctions = newAllowedFunction.clone();
    }

    //TODO: add docstring
    public CourseGetter getDataSource(){
        return this.dataSource;
    }
}
