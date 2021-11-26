package Interfaces;

import DataGetting.CSVScraper;
import DataGetting.WebScraper;


import Controllers.DatabaseController;
import Controllers.CommandFactory;


import TimeTableContainers.TimeTableManager;
import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;

import java.io.IOException;
import java.io.*;
import java.util.*;


/**
 * An OperatorInterface class, sets the DataGetter and bans functions by operator.
 *
 * === Private Attributes ===
 * control: This is a DatabaseController
 * datasource: A String of the selected datasource's name
 * bannedFunctions: An ArrayList of  names of functions that are banned by the operator.
 */
public class OperatorInterface {
    private final DatabaseController control;
    private String datasource;
    private final ArrayList<String> bannedFunctions;


    /**
     * Constructor.
     * Set control, datasource, and bannedFunctions.
     */
    public OperatorInterface(DatabaseController controller) {
        this.control = controller;
        this.datasource = null;
        this.bannedFunctions = new ArrayList<>();
    }


    /**
     * Set the datasource to the CommandFactory according to the operator's choice.
     *
     * @param theFactory: The CommandFactory object is used to set the datasource.
     * @param input: A String of the name of the datasource inputted by the operator.
     * @exception IOException throws if the file datasource.txt is not found.
     */
    public void SetDatasource(CommandFactory theFactory, String input) throws IOException {
        // Set the datasource of theFactory to be CSVScraper if operator chooses it.
        if (input.equals("CSVScraper")) {
            theFactory.setDataSource(new CSVScraper());
            theFactory.setManager(new TimeTableManager());
            this.control.setFactory(theFactory);
            this.datasource = input;
            this.download(input);

            // Set the datasource of theFactory to be WebScraper if operator chooses it.
        } else if (input.equals("WebScraper")) {
            theFactory.setDataSource(new WebScraper());
            theFactory.setManager(new TimeTableManager());
            this.control.setFactory(theFactory);
            this.datasource = input;
            this.download(input);
        }
    }


    /**
     * Ban the function in the CommandFactory according to the operator's choice.
     *
     * @param ban the name of the banned function.
     * @param commandFactory the CommandFactory which bans the function.
     */
    public void banFunction(String ban, CommandFactory commandFactory) {
        // Create an Array that stores banned functions.
        String[] oldFunctions =  commandFactory.getAllowedFunctions();
        String[] newFunctions = new String[oldFunctions.length - 1];
        int index = Integer.parseInt(ban);
        String bannedFunction = oldFunctions[index];
        for (int i = 0, k = 0; i < oldFunctions.length; i ++) {
            if (! oldFunctions[i].equals(bannedFunction)) {
                newFunctions[k] = oldFunctions[i];
                k++;
            }
        }

        // Update the allowedFunction in the CommandFactory
        commandFactory.setAllowedFunctions(newFunctions);
        this.control.setFactory(commandFactory);
    }


    /**
     * Runs the OperatorInterface
     */
    public void run() throws IOException {

            // Checks which datasource does the operator want to set.
            InputChecker requestDatasource = new InputChecker("Which datasource do you want to set (CSVScraper/WebScraper/n (if you do not want to set)): ",
                    new isValidDatasource());
            String type = requestDatasource.checkCorrectness();
            // Set the datasource if the operator has chosen one.
            CommandFactory theFactory = new CommandFactory(control);
            this.SetDatasource(theFactory, type);

            // Checks if the user wants to ban a function.
            InputChecker requestBan = new InputChecker("Do you want to ban a function? " +
                "(true/false):",
                new isValidBoolean());
            String ban = requestBan.checkCorrectness();

            // If the operator wants to ban a function.
            if (ban.equals("true")) {
                // As long as the operator wants to ban function.
                boolean running2 = true;
                while (running2){
                    // Print all functions that the operator can choose to ban.
                    LinkedHashMap<String, String> allowed =
                            hashMapit(theFactory.getAllowedFunctions());
                    for (String key : allowed.keySet()){
                        System.out.println(key + ": " + allowed.get(key));
                    }

                    // Ask operator which function to ban.
                    InputChecker requestCommand = new InputChecker("Which function do you want to ban?",
                            new isValidCommand(allowed));
                    String requested = requestCommand.checkCorrectness();

                    // Ban the function
                    this.bannedFunctions.add(requested);
                    this.banFunction(requested, theFactory);

                    // Checks if the operator wants to ban another function.
                    InputChecker continueBan = new InputChecker("Do you want to ban another function? " +
                            "(true/false):", new isValidBoolean());
                    String whetherBan = continueBan.checkCorrectness();

                    // If operator does not want to ban another function.
                    if (whetherBan.equals("false")) {
                        running2 = false;
                    }
                }
            }
    }


    // ============================ Helper Methods =================================
    /**
     * Get the chosen data source.
     *
     * @return the String of the name of the selected datasource.
     */
    public String getDatasource() {
        return this.datasource;
    }


    /**
     * Get the banned functions.
     *
     * @return the ArrayList of the name of banned functions.
     */
    public ArrayList<String> getBannedFunctions(){
        return this.bannedFunctions;
    }


    /**
     * Save the name of the selected datasource in the file. datasource.txt.
     *
     * @param source the name of the datasource selected by operator.
     * @exception IOException throws when the file, datasource.txt, is not found.
     */
    public void download(String source) throws IOException {
        FileWriter file = new FileWriter("src/Interfaces/datasource.txt");
        // Write the source in the datasource.json.
        file.write(source);
        file.flush();
        // After writing, close the file.
        file.close();
    }


    /**
     * Returns a hashmap of the entries in the string array strings with
     * corresponding integer values from least to greatest.
     *
     * @param strings the array of strings
     * @return a hashmap of items from strings as values and ascending
     * integers as keys
     */
    private LinkedHashMap<String, String> hashMapit(String[] strings) {
        LinkedHashMap<String, String> theMap = new LinkedHashMap<>();
        for (int i = 0; i < strings.length; i++) {
            theMap.put(String.valueOf(i), strings[i]);
        }
        return theMap;
    }


    // ============================ Predicates =================================
    /**
     * A predicate to determine if the command is valid.
     */
    private static class isValidCommand extends Predicate {
        private final HashMap<String, String> allowed;
        public isValidCommand(HashMap<String, String> values){
            this.allowed = values;
        }

        @Override
        public boolean run(String prompt) {
            return this.allowed.containsKey(prompt);
        }
    }


    /**
     * A predicate to determine if the datasource is valid input.
     */
    private static class isValidDatasource extends Predicate {
        private final String[] allowed;
        public isValidDatasource(){
            this.allowed = new String[]{"CSVScraper", "WebScraper", "n"};
        }
        @Override
        public boolean run(String prompt) {
            return Arrays.asList(allowed).contains(prompt) ;
        }
    }


    /**
     * A predicate to determine if the input is valid boolean.
     */
    private static class isValidBoolean extends Predicate {
        private final String[] allowed;
        public isValidBoolean(){
            this.allowed = new String[]{"true", "false"};
        }
        @Override
        public boolean run(String prompt) {
            return Arrays.asList(allowed).contains(prompt) ;
        }
    }
}

