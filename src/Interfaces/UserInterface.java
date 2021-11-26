package Interfaces;

import Controllers.DatabaseController;
import Controllers.CommandFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * An UserInterface class
 * It sets the DatabaseController.
 *
 * === Private Attributes ===
 *  control: The DatabaseController object.
 *  operator: The OperatorInterface object.
 */
public class UserInterface {
    private final DatabaseController control;
    private final OperatorInterface operator;


    /**
     * Constructor of the UserInterface.
     * Sets control and operator.
     */
    public UserInterface() {
        this.control = new DatabaseController();
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
        this.read();
        CommandFactory theFactory = new CommandFactory(control);
        this.operator.SetDatasource(theFactory, this.read());

        // Ban the functions banned by operator.
        ArrayList<String> banFunctions = this.operator.getBannedFunctions();
        for (String x : banFunctions) {
            this.operator.banFunction(x, theFactory);
        }

        while (running) {
            System.out.println("\nCurrent datasource: " + this.operator.getDatasource());
            running = control.run();
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
    private String read() throws IOException {
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
}
