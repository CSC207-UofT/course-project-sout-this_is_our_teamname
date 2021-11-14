package Interfaces;



import GlobalHelpers.Constants;
import DatabaseController.DatabaseController;
import DatabaseController.CommandFactory;
import GlobalHelpers.InvalidInputException;
import GlobalHelpers.Search;
import TimeTableStuff.TimeTableManager;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    private final HashMap<String, String[]> usableClasses;
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

        // Will be replaced with something by OperatorInterface in later Phases.
        usableClasses = new HashMap<>();
        usableClasses.put(Constants.COURSE, new String[]{Constants.COURSE});
        usableClasses.put(Constants.NON_COURSE_OBJECT,
                new String[]{Constants.LIFE,
                        Constants.DESCRIPTION_LESS_LIFE});
    }

    /**
     * A helper method to help get the correct value key for the given
     * function. For instance, if the user wants to schedule an event, it
     * will correct the input so that it will return the 'Non Course Object'
     * Constant.
     *
     * @param input The input that the uper gave
     * @return The corresponding constant based on the input of the user.
     * Returns null if the input in invalid.
     */
    private String checkInputValue(String input){
        for (String key : usableClasses.keySet()){
            if (Search.BinarySearch(input, usableClasses.get(key))){
                return key;
            }
        }
        return null;
    }

    /**
     * Gets a printable string representation of the usable classes
     *
     * @return A string representation of all the usable classes.
     */
    private String getUsableClasses(){
        StringBuilder usableClassesString = new StringBuilder();
        for (String[] item : usableClasses.values()){
            for (String usableItem : item){
                usableClassesString.append(usableItem).append(", ");
            }
        }
        return usableClassesString.toString();
    }

    private String read() throws IOException {

        StringBuilder buffer = new StringBuilder();
        BufferedReader bf= new BufferedReader(new FileReader("src/Interfaces/datasource.txt"));
        String s;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }

        return buffer.toString();
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
    public void run() throws IOException {
        // As long as the program is running
        boolean running = true;


        while(running) {

            if (this.read() != null) {
                CommandFactory theFactory = new CommandFactory(control);
                this.operator.SetDatasource(theFactory, this.read());

            }


            System.out.println("\nCurrent datasource: " + this.operator.getDatasource());
            System.out.println("\nUsable Objects: " + this.getUsableClasses());
            Scanner objectScanner = new Scanner(System.in);
            System.out.println("Please enter what type of object: ");
            String schedulingObject = objectScanner.nextLine();

            String dataCategory = checkInputValue(schedulingObject);

            try {
                control.runCommand(dataCategory);
            } catch (InvalidInputException e){
                ; // TODO FIXME
            }
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
