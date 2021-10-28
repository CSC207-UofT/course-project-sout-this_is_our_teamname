package DatabaseController;

import DataCollection.CSVScraper;
import DataCollection.DataGetter;
import FunctionsAndCommands.Commands.Command;
import FunctionsAndCommands.Commands.CreationCommands.GetAllTimeTablesCommand;
import FunctionsAndCommands.Commands.CreationCommands.MakeCourseCommand;
import FunctionsAndCommands.Commands.CreationCommands.MakeEventCommand;
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

    // Command Keys:
    public static final String SCHEDULE_COURSE = Constants.COURSE;
    public static final String SCHEDULE_EVENT = Constants.NON_COURSE_OBJECT;
    public static final String GET_ALL_TIMETABLE = "Get All TimeTables";

    /**
     * Constructor. Sets the TimeTable Manager and DataSource of the file
     * TODO @Caules Use OperatorInterface
     * TODO @Aiden Add DataLoaders here too!
     */
    public CommandFactory(){
        this.courseManager = new TimeTableManager();
        this.dataSource = new CSVScraper();
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
        if (inputCommand.equals(SCHEDULE_COURSE)) {
            return new MakeCourseCommand(courseManager, dataSource);
        }

        // To schedule an event
        else if (inputCommand.equals(SCHEDULE_EVENT)) {
            return new MakeEventCommand(courseManager);
        }

        // To get all the timetables in the TimeTableManager
        else if (inputCommand.equals(GET_ALL_TIMETABLE)){
            return new GetAllTimeTablesCommand(courseManager);
        }
        // ... ADD YOUR NEW OBJECTS HERE!

        // The command is invalid
        else {
            throw new InvalidInputException();
        }
    }
}
