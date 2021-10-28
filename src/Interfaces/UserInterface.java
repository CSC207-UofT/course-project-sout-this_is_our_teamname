package Interfaces;

import GlobalHelpers.Constants;
import DatabaseController.DatabaseController;

import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    private final HashMap<String, String[]> usableClasses;

    public UserInterface(){
        // Will be replaced with something by OperatorInterface in later Phases.
        usableClasses = new HashMap<>();
        usableClasses.put(Constants.COURSE, new String[]{Constants.COURSE});
        usableClasses.put(Constants.NON_COURSE_OBJECT,
                new String[]{Constants.ACTIVITY,
                        Constants.TASK});
    }

    private boolean BinarySearch(String query, String[] array){
        for (String item : array){
            if (query.equals(item)){
                return true;
            }
        }
        return false;
    }

    private String checkInputValue(String input){
        for (String key : usableClasses.keySet()){
            if (BinarySearch(input, usableClasses.get(key))){
                return key;
            }
        }
        return "";
    }

    private String getUsableClasses(){
        StringBuilder usables = new StringBuilder();
        for (String[] item : usableClasses.values()){
            for (String usableItem : item){
                usables.append(usableItem).append(", ");
            }
        }
        return usables.toString();
    }

    /**
     * Runs the UserInterface.
     *
     */
    public void run(){
        DatabaseController control = new DatabaseController();

        // As long as the program is running
        boolean running = true;

        while(running) {
            System.out.println("\nUsable Objects: " + this.getUsableClasses());
            Scanner objectScanner = new Scanner(System.in);
            System.out.println("Please enter what type of object: ");
            String schedulingObject = objectScanner.nextLine();

            // For Phase 0, assume valid inputs
            String dataCategory = checkInputValue(schedulingObject);

            if (dataCategory.equals(Constants.COURSE)) {
                control.makeCourse();
            } else if (dataCategory.equals(Constants.NON_COURSE_OBJECT)){
                control.makeTimeTableObject(schedulingObject);
            } else {
                throw new UnsupportedOperationException(
                        "This is not Implemented in Phase 0.");
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
        control.getAllTimeTables();
    }

    /**
     * A UserInterface. The main method of the program and the one that the
     * user interacts with.
     *
     * @param args The arguments
     */
    public static void main(String[] args) {
        UserInterface user = new UserInterface();

        user.run();

        System.out.println("Here are your TimeTable!");
    }
}
