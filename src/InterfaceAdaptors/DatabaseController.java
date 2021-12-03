package InterfaceAdaptors;
import Commands.Command;
import Commands.FunctionCommands.ExitProgramCommand;
import DataGetting.CSVScraper;
import DataGetting.WebScraper;
import Helpers.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * A database controller class that serves as the Invoker in the command
 * pattern. It sets the command and executes them.
 *
 * === Private Attributes ===
 * CommandHistory: The history of all the commands that has ever been made
 *  since the program was run (For Phase 2, maybe serialize it so that it can
 *  have a history of all commands ever made)
 *
 * possibleCommands: The factory that generates the commands to be executed
 */
public class DatabaseController {
    private final Stack<Command> CommandHistory;
    private CommandFactory Factory;

    /**
     * A constructor. Sets the commandHistory and Factory
     */
    public DatabaseController(){
        this.CommandHistory = new Stack<>();
        this.Factory = new CommandFactory(this);
    }

    // ======================== Control UserInterface ==========================

    /**
     * Configures the UserInterface
     * @throws IOException if the file cannot be found to get the information
     */
    public void configureUserInterface() throws IOException {
        // Set the DataGetter of the program to be the one in the datasource.json.
        String source = this.readDatasource();
        this.configureDataSource(source);

        // Set the AllowedFunction in the file to be the one saved in the file.
        String[] banFunctions = this.readFunctions();

        this.Factory.setAllowedFunctions(banFunctions);

    }

    /**
     * Set the datasource to the CommandFactory according to the operator's choice.
     *
     * @param input: A String of the name of the datasource inputted by the operator.
     */
    private void configureDataSource(String input) {
        // Set the datasource of theFactory to be CSVScraper if operator chooses it.
        if (input.equals("CSVScraper")) {
            this.Factory.setDataSource(new CSVScraper());
        } else if (input.equals("WebScraper")) {
            this.Factory.setDataSource(new WebScraper());
        }
    }

    /**
     * Read the datasource.txt file.
     *
     * @exception IOException will be raised if the given file is not found.
     *
     * @return A string in datasource.txt.
     */
    private String readDatasource() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        // Read the file.
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Interfaces/datasource.txt"));
        String s;
        // Check whether the datasource.json is empty.
        if ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s.trim());
        }
        return stringBuilder.toString();
    }

    /**
     * Read the functions.txt file.
     *
     * @exception IOException will be raised if the given file is not found.
     *
     * @return the Array of allowedFunctions.
     */
    private String[] readFunctions() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        // Read the file.
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Interfaces/functions.txt"));
        String s;
        String newString = "";
        // Check whether the datasource.json is empty.
        if ((s = bufferedReader.readLine()) != null) {
            // rawString is the String contains "[" and "]", need to delete them first and then convert the String into Array
            stringBuilder.append(s.trim());
            String rawString = stringBuilder.toString();
            newString = rawString.substring(1, rawString.length() - 1);
        }
        return newString.split(",\\s+");
    }

    // ======================= Control OperatorInterface =======================
    /**
     * Ban the function in the CommandFactory according to the operator's choice.
     *
     * @param ban the name of the banned function.
     * @exception IOException throws if the file functions.txt is not found.
     */
    public void setBanned(String ban) throws IOException {
        ArrayList<String> function = new ArrayList<>(Arrays.asList(this.Factory.getAllowedFunctions()));
        function.remove(ban);

        FileWriter file = new FileWriter("src/Interfaces/functions.txt");
        // Write the ALLOWED functions in functions.txt.
        file.write(function.toString());

        file.flush();
        // After writing, close the file.
        file.close();

        // Set the factory so that it persists even before the program exits
        this.Factory.setAllowedFunctions(function.toArray(new String[0]));
    }

    /**
     * Set the datasource to the CommandFactory according to the operator's choice.
     *
     * @param input: A String of the name of the datasource inputted by the operator.
     * @exception IOException throws if the file datasource.txt is not found.
     */
    public void setDataSource(String input) throws IOException {
        // Set the factory so that it persists even before the program exits
        configureDataSource(input);

        FileWriter file = new FileWriter("src/Interfaces/datasource.txt");
        // Write the source in the datasource.json.
        file.write(input);

        file.flush();
        // After writing, close the file.
        file.close();
    }

    // ===================== Command Pattern Infrastructure ====================
    /**
     * Sets the command into the Command History.
     *
     * The command history will act as a history of all the commands in order
     * they were added. The setCommands will also execute the command.
     *
     * @param requestedCommand the command that has been requested
     * @return true iff the command has been run properly. False to indicate
     * that the program should exit
     * @exception InvalidInputException Throws invalid Input exception if the
     * input command is invalid
     */
    public boolean runCommand(String requestedCommand) throws InvalidInputException {
        assert this.Factory != null;
        Command theCommand = this.Factory.getCommand(requestedCommand);

        // If the command is to exit the program, it will return false to let
        // UserInterface know to exit the program
        if (theCommand instanceof ExitProgramCommand) {
            return false;
        }

        // Execute the command. Throws InvalidInputException if the command is
        // invalid
        executeCommand(theCommand);
        return true;
    }

    /**
     * Sends the command into the commandHistory and executes the command.
     *
     * The execute method is an abstract method so all command objects will
     * have it.
     *
     * @param theCommand the command
     */
    private void executeCommand(Command theCommand){
        this.CommandHistory.push(theCommand);
        theCommand.execute();
    }

    // ============================ Setters and Getters ========================
    /**
     * Set the factory attached to this controller
     *
     * @param theFactory the factory to be set
     */
    public void setFactory(CommandFactory theFactory){
        this.Factory = theFactory;
    }

    /**
     * Get a Stack of the commands that has been executed in the history
     * @return The Stack of commands.
     */
    public Stack<Command> getCommandHistory() {
        return CommandHistory;
    }

    /**
     * Returns a hashmap of the entries in the string array commandList with
     * corresponding integer values from least to greatest.
     *
     * @return a hashmap of items from strings as values and ascending
     * integers as keys
     */
    public String[] getAllowedFunctions(){
        return this.Factory.getAllowedFunctions();
    }

    public String getDataSource(){
        return this.Factory.getDataSource().toString();
    }
}
