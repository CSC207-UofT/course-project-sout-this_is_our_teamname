package Interfaces;

import DatabaseController.DatabaseController;
import DatabaseController.CommandFactory;
import GlobalHelpers.InvalidInputException;
import TimeTableStuff.TimeTableManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final DatabaseController control;
    private final OperatorInterface operator;

    /**
     * Constructor.
     *
     * === Private Attributes ===
     * usableClasses: This is a hashmap of all the usable classes in the
     * program.
     * control: This is a DatabaseController
     * operator: This is an OperatorInterface
     */
    public UserInterface(){
        this.control = new DatabaseController();
        CommandFactory theFactory = new CommandFactory(control);
        theFactory.setManager(new TimeTableManager());
        this.control.setFactory(theFactory);
        this.operator = new OperatorInterface(this.control);
    }


    /**
     * Gets the OperatorInterface
     *
     * @return A object of the OperatorInterface.
     */
    public OperatorInterface getOperator(){
        return this.operator;
    }

    /**
     * Read  the datasource.txt
     *
     * @return A string in datasource.txt.
     */
    private String read() throws IOException {

        StringBuilder buffer = new StringBuilder();
        BufferedReader bf= new BufferedReader(new FileReader("src/Interfaces/datasource.txt"));
        String s;
        while((s = bf.readLine()) != null){
            buffer.append(s.trim());
        }


        return buffer.toString();
    }

    /**
     * Runs the UserInterface.
     *
     */
    public void run() throws IOException {
        // As long as the program is running
        boolean running = true;

        this.read();
        CommandFactory theFactory = new CommandFactory(control);
        this.operator.SetDatasource(theFactory, this.read());

        while(running) {
            System.out.println("\nCurrent datasource: " + this.operator.getDatasource());
            control.run();

            // User types in the section they want to search
            Scanner continueQuestion = new Scanner(System.in);
            System.out.println("Continue? " +
                    "(true/false):");
            String continueResponse = continueQuestion.nextLine();

            // Checks if the user wants to add any more courses.
            if (continueResponse.equals("false")){
                running = false;
            }
        }

        // Gets all the timetables.
        try {
            control.runCommand("Get All TimeTables");
        } catch (InvalidInputException e){
            System.out.println("Oh No! I can't get the TimeTables");
        }
    }

}
