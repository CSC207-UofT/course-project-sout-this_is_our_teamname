package InterfaceAdaptors;

import Commands.Command;
import DataGetting.CourseGetter;
import Helpers.InvalidInputException;
import TimeTableContainers.TimeTableManager;

/**
 *
 * An abstract class for factory class to create the individual commands of the class.
 *
 * === Attributes ===
 * allowedFunctions: The list of allowed functions for the program as set out by the OperatorInterface
 * courseManager: The TimetableManager to connect to
 * dataSource: The Data Getter to get the data from
 * controller: The controller that this is connected to
 *
 */
public abstract class CommandFactory {
    private String[] allowedFunctions;
    protected final TimeTableManager courseManager;
    protected CourseGetter dataSource;
    protected final DatabaseController controller;

    /**
     * Constructor. Sets the TimeTable Manager and DataSource of the file
     *
     * @param controller the database controller that this CommandFactory is set to.
     */
    public CommandFactory(DatabaseController controller){
        this.courseManager = new TimeTableManager();
        this.dataSource = null;
        this.controller = controller;
        this.allowedFunctions = null;
    }

    /**
     * Return the corresponding command object to whatever has been inputted by the user.
     * Please see `DatabaseController` for how the command objects will be used
     *
     * @param inputCommand The STRING input command of the user.
     * @return The correct command object
     * @throws InvalidInputException If the inputCommand is invalid, throw this!
     */
    public abstract Command getCommand(String inputCommand) throws InvalidInputException;


    /**
     * Returns a string array of all the allowable functions of the program
     *
     * @return a string array of all the allowed functions of the program
     */
    public String[] getAllowedFunctions() {
        return allowedFunctions;
    }

    public TimeTableManager getCourseManager() {
        return courseManager;
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


    /**
     * Gets the DataGetter to get the data from
     * @return the DataGetter
     */
    public CourseGetter getDataSource(){
        return this.dataSource;
    }
}
