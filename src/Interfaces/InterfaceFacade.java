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
                running = false;
            }
            if (type.equals("Operator")) {
                this.operatorInterface.run;
                running = false;
            }
            else {
                System.out.print("Please type (User/Operator)");
            }

        }
    }
    public static void main(String[] args) {
        InterfaceFacade user = new InterfaceFacade(new UserInterface(), new OperatorInterface());

        user.run();
    }
}
