package Interfaces;

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
    public void run() {
        // As long as the program is running
        boolean running = true;

        while (running) {
            System.out.println("Who are you (User/Operator): ");
            Scanner objectScanner = new Scanner(System.in);
            String type = objectScanner.nextLine();
            if (type.equals("User")) {
                this.userInterface.run();
                System.out.println("Here are your TimeTable!");
            } else if (type.equals("Operator")) {
                this.operatorInterface.run();
            } else {
                System.out.print("Please type (User/Operator).");
            }

        }
    }
}
