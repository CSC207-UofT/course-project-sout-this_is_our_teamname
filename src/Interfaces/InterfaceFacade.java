package Interfaces;

import java.io.IOException;
import java.util.Scanner;

public class InterfaceFacade {
    private final UserInterface userInterface;
    private final OperatorInterface operatorInterface;

    public InterfaceFacade(UserInterface userInterface, OperatorInterface operatorInterface) {
        this.userInterface = userInterface;
        this.operatorInterface = operatorInterface;
    }

    /**
     * Runs the InterfaceFacade
     */
    public void run() throws IOException {
        // As long as the program is running
        boolean running = true;

        while (running) {
            System.out.println("Who are you (User/Operator): ");
            Scanner objectScanner = new Scanner(System.in);
            String type = objectScanner.nextLine();
            if (type.equals("User")) {
                this.userInterface.run();
            } else if (type.equals("Operator")) {
                this.operatorInterface.run();
            } else {
                System.out.print("Please type (User/Operator).");
            }

            // Check if the user want to exit the program.
            Scanner continueQuestion = new Scanner(System.in);
            System.out.println("Do you want to exit? " +
                    "(true/false):");
            String continueResponse = continueQuestion.nextLine();

            // Checks if the user wants to add any more courses.
            if (continueResponse.equals("true")){
                running = false;
            }
        }
    }

    /**
     * A InterfaceFacade. The main method of the program and the one that the
     * user interacts with.
     *
     * @param args The arguments
     */
    public static void main(String[] args) throws IOException {
        UserInterface user = new UserInterface();

        InterfaceFacade facade = new InterfaceFacade(user, user.getOperator());

        facade.run();

    }

}
