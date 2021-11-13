package Interfaces;

import GlobalHelpers.Constants;
import DatabaseController.DatabaseController;
import DatabaseController.CommandFactory;
import GlobalHelpers.InvalidInputException;
import GlobalHelpers.Search;
import TimeTableStuff.TimeTableManager;

import java.util.HashMap;
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
     * Runs the UserInterface.
     *
     */
    public void run(){
        // As long as the program is running
        boolean running = true;


        while(running) {
//            System.out.println("\nUsable Objects: " + this.getUsableClasses());
//            Scanner objectScanner = new Scanner(System.in);
//            System.out.println("Please enter what type of object: ");
//            String schedulingObject = objectScanner.nextLine();
//
//            String dataCategory = checkInputValue(schedulingObject);

            control.run();

//            try {
//                control.runCommand(dataCategory);
//            } catch (InvalidInputException e){
//                ; // TODO FIXME
//            }
            // User types in the section they want to search
            Scanner continueQuestion = new Scanner(System.in);
            System.out.println("Do you want to add another object? " +
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
