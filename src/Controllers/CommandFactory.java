package Controllers;

import DataGetting.DataGetter;
import Commands.Command;
import Commands.CreationCommands.GetAllTimeTablesCommand;
import Commands.CreationCommands.MakeCourseCommand;
import Commands.CreationCommands.MakeEventCommand;
import Commands.CreationCommands.PrintHistoryCommand;
import Commands.FunctionCommands.DownloadDataCommand;
import Commands.FunctionCommands.LoadDataCommand;
import Commands.FunctionCommands.SaveDataCommand;
import Helpers.InvalidInputException;
import TimeTableContainers.TimeTableManager;

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
    private DataGetter dataSource;
    private final DatabaseController controller;
    private final String[] allowedFunctions;

    static final String SCHEDULE_COURSE = "Schedule Course";
    static final String SCHEDULE_EVENT = "Schedule Event";
    static final String GET_ALL_TIMETABLE = "Show TimeTables";
    static final String PRINT_HISTORY = "Get History";
    static final String LOAD_DATA = "Load Data";
    static final String SAVE_DATA = "Save";
    static final String DOWNLOAD_TIMETABLE = "Download Timetable";
    static final String EXIT = "Log Out";

    /**
     * Constructor. Sets the TimeTable Manager and DataSource of the file
     */
    public CommandFactory(DatabaseController theController){
        this.courseManager = null;
        this.dataSource = null;
        this.controller = theController;
        this.allowedFunctions = new String[]{
                SCHEDULE_COURSE,
                SCHEDULE_EVENT,
                LOAD_DATA,
                SAVE_DATA,
                DOWNLOAD_TIMETABLE,
                GET_ALL_TIMETABLE,
                PRINT_HISTORY,
                EXIT
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

        switch (inputCommand) {
            case SCHEDULE_COURSE:
                return new MakeCourseCommand(courseManager, dataSource);
            case SCHEDULE_EVENT:
                return new MakeEventCommand(courseManager);
            case GET_ALL_TIMETABLE:
                return new GetAllTimeTablesCommand(courseManager);
            case PRINT_HISTORY:
                return new PrintHistoryCommand(controller);
            case LOAD_DATA:
                return new LoadDataCommand(courseManager);
            case SAVE_DATA:
                return new SaveDataCommand(courseManager);
            case DOWNLOAD_TIMETABLE:
                return new DownloadDataCommand(courseManager);
            case EXIT:
                // Signals DatabaseController to exit the Program
                return null;
            // ... ADD YOUR NEW OBJECTS HERE!

            // The command is invalid
            default:
                throw new InvalidInputException();
        }
    }

    // ========================= Setters and Getters ===========================
    /**
     * Returns an string array of all the allowable functions of the program
     *
     * @return a string array of all the allowed functions of the program
     */
    public String[] getAllowedFunctions() {
        return allowedFunctions;
    }

    /**
     * Sets the TimeTableManager to connect to
     * @param theManager the TimeTableManager to connect to
     */
    public void setManager(TimeTableManager theManager){
        this.courseManager = theManager;
    }

    /**
     * Sets the DataGetter to connect to
     * @param theDataSource the DataGetter to connect to
     */
    public void setDataSource(DataGetter theDataSource){
        this.dataSource = theDataSource;
    }
}
