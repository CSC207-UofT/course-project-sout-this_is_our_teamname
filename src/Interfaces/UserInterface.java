package Interfaces;

import InterfaceAdaptors.DatabaseController;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.Presenter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 * An UserInterface class
 * It sets the DatabaseController.
 *
 * === Private Attributes ===
 *  control: The DatabaseController object.
 *  operator: The OperatorInterface object.
 *  presenter: the Presenter object.
 */
public class UserInterface {
    private final DatabaseController control;
    private final OperatorInterface operator;
    private final Presenter presenter;

    /**
     * Constructor of the UserInterface.
     * Sets presenter, control and operator.
     */
    public UserInterface() {
        this.presenter = new Presenter();
        this.control = this.presenter.getController();
        this.operator = new OperatorInterface(this.control);
    }


    /**
     * Runs the UserInterface.
     *
     * The IOException will be raised if the given file, datasource.txt, is not found.
     */
    public void run() throws IOException {
        // As long as the program is running
        boolean running = true;

        // Set the DataGetter of the program to be the one in the datasource.json.
        this.readDatasource();
        CommandFactory theFactory = new CommandFactory(control);
        this.operator.SetDatasource(theFactory, this.readDatasource());

        //TODO: the banFunction will be fixed later

        //ArrayList<String> banFunctions = this.operator.getBannedFunctions();
        //theFactory.setAllowedFunctions();
        //String[] banFunctions = this.readFunctions();
        //theFactory.setAllowedFunctions(banFunctions);
        //this.control.setFactory(theFactory);
        //for (int i = 0; i < banFunctions.length(); i++) {
        //char function = banFunctions.charAt(i);
        // Convert the char to String
        //String stringFunction = String.valueOf(function);

        // this.operator.banFunction(stringFunction, theFactory);
        //}

        while (running) {
            System.out.println("\nCurrent datasource: " + this.operator.getDatasource());
            running = presenter.run();
        }
    }


    // ============================ Helper Methods =================================
    /**
     * Gets the OperatorInterface
     *
     * @return An OperatorInterface object.
     */
    public OperatorInterface getOperator() {
        return this.operator;
    }


    /**
     * Read the datasource.txt file.
     *
     * The IOException will be raised if the given file is not found.
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
     * The IOException will be raised if the given file is not found.
     *
     * @return A string in datasource.txt.
     */
    private String[] readFunctions() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        // Read the file.
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Interfaces/functions.txt"));
        String s;
        // Check whether the datasource.json is empty.
        if ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s.trim());
        }
        String rawString = stringBuilder.toString();
        StringBuilder newString = new StringBuilder();
        String deletedString = "[]";
        char deletedChar1 = deletedString.charAt(0);
        char deletedChar2 = deletedString.charAt(1);
        for (int i = 0; i < rawString.length(); i ++) {
            if (rawString.charAt(i) != deletedChar1 & rawString.charAt(i) != deletedChar2){
                newString.append(rawString.charAt(i));
            }
        }
        System.out.println(Arrays.toString(newString.toString().trim().split(",")));
        return newString.toString().split(",");
    }
}
