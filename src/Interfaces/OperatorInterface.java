package Interfaces;

import Helpers.Constants;
import Helpers.Reformatters;
import InterfaceAdaptors.DatabaseController;
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
 */
public class OperatorInterface {
    private String datasource;
    private final DatabaseController control;

    /**
     * Constructor.
     * Set control and datasource.
     */
    public OperatorInterface() {
        this.datasource = null;
        this.control = new DatabaseController();
    }

    /**
     * Set the datasource to the CommandFactory according to the operator's choice.
     *
     * @param input: A String of the name of the datasource inputted by the operator.
     * @exception IOException throws if the file datasource.txt is not found.
     */
    public void SetDatasource(String input) throws IOException {
        this.datasource = input;
        this.downloadDatasource(input);
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
        this.SetDatasource(type);

        // Checks if the user wants to ban a function.
        InputChecker requestBan = new InputChecker("Do you want to ban a function? " +
                "(true/false):",
                new isValidBoolean());
        String ban = requestBan.checkCorrectness();

        // If the operator wants to ban a function.
        if (ban.equals(Constants.TRUE)) {
            promptBan();
        }
    }

    private void promptBan() throws IOException {
        // As long as the operator wants to ban function.
        boolean running = true;
        while (running){
            // Print all functions that the operator can choose to ban.
            LinkedHashMap<String, String> allowed =
                    Reformatters.hashMapIt(control.getAllowedFunctions());

            for (String key : allowed.keySet()){
                System.out.println(key + ": " + allowed.get(key));
            }

            // Ask operator which function to ban.
            InputChecker requestCommand = new InputChecker("Which function do you want to ban?",
                    new isValidCommand(allowed));
            String requested = requestCommand.checkCorrectness();

            // Ban the function
            this.control.setBanned(requested);

            // Checks if the operator wants to ban another function.
            InputChecker continueBan = new InputChecker("Do you want to ban another function? " +
                    "(true/false):", new isValidBoolean());
            String whetherBan = continueBan.checkCorrectness();

            // If operator does not want to ban another function.
            if (whetherBan.equals(Constants.FALSE)) {
                running = false;
            }
        }
    }


    // ============================ Helper Methods =================================
    /**
     * Save the name of the selected datasource in the file, datasource.txt.
     *
     * @param source the name of the datasource selected by operator.
     * @exception IOException throws when the file, datasource.txt, is not found.
     */
    public void downloadDatasource(String source) throws IOException {
        FileWriter file = new FileWriter("src/Interfaces/datasource.txt");
        // Write the source in the datasource.json.
        file.write(source);
        file.flush();
        // After writing, close the file.
        file.close();
    }

    public DatabaseController getControl() {
        return control;
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

