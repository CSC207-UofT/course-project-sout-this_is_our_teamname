package DatabaseController;

import DataCollection.CSVScraper;
import DataCollection.DataGetter;
import FunctionsAndCommands.Commands.Command;
import FunctionsAndCommands.Commands.CreationCommands.GetAllTimeTablesCommand;
import FunctionsAndCommands.Commands.CreationCommands.MakeCourseCommand;
import FunctionsAndCommands.Commands.CreationCommands.MakeEventCommand;
import FunctionsAndCommands.Commands.CreationCommands.PrintHistoryCommand;
import GlobalHelpers.Constants;
import GlobalHelpers.InvalidInputException;
import TimeTableStuff.TimeTableManager;

/**
 * A factory class to create the individual commands of the class.
 */
public class CommandFactory {
    // TODO @Caules. Please configure Operator Interface here!
    public final TimeTableManager courseManager;
    public final DataGetter dataSource;
    public final DatabaseController controller;

    // Command Keys: TODO Please fix
    public static final String SCHEDULE_COURSE = Constants.COURSE;
    public static final String SCHEDULE_EVENT = Constants.NON_COURSE_OBJECT;
    public static final String GET_ALL_TIMETABLE = "Get All TimeTables";
    public static final String PRINT_HISTORY = "Get History";

    /**
     * Constructor. Sets the TimeTable Manager and DataSource of the file
     * TODO @Caules Use OperatorInterface
     * TODO @Aiden Add DataLoaders here too!
     */
    public CommandFactory(DatabaseController theController){
        this.courseManager = new TimeTableManager();
        this.dataSource = new CSVScraper();
        this.controller = theController;
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
        // To schedule a course
        switch (inputCommand) {
            case SCHEDULE_COURSE:
                return new MakeCourseCommand(courseManager, dataSource);
            // To schedule an event
            case SCHEDULE_EVENT:
                return new MakeEventCommand(courseManager);
            // To get all the timetables in the TimeTableManager
            case GET_ALL_TIMETABLE:
                return new GetAllTimeTablesCommand(courseManager);
            case PRINT_HISTORY:
                return new PrintHistoryCommand(controller);


            // ... ADD YOUR NEW OBJECTS HERE!

            // The command is invalid
            default:
                throw new InvalidInputException();
        }
    }
}
