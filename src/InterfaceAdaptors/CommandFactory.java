package InterfaceAdaptors;

import Commands.Command;
import DataGetting.CourseGetter;
import Helpers.InvalidInputException;
import TimeTableContainers.TimeTableManager;

public abstract class CommandFactory {
    private String[] allowedFunctions;
    protected final TimeTableManager courseManager;
    protected CourseGetter dataSource;
    protected final DatabaseController controller;

    public CommandFactory(DatabaseController controller){
        this.courseManager = new TimeTableManager();
        this.dataSource = null;
        this.controller = controller;
        this.allowedFunctions = null;
    }

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


    //TODO: add docstring
    public CourseGetter getDataSource(){
        return this.dataSource;
    }
}
