package Interfaces;

import java.io.IOException;

import java.util.Arrays;

import Helpers.InputCheckers.InputChecker;
import Helpers.InputCheckers.Predicate;


/**
 * A InterfaceFacade class that serves as the Invoker in the facade
 * pattern. It sets the UserInterface and OperatorInterface and executes them according to input.
 *
 * === Private Attributes ===
 *  userInterface: The UserInterface object.
 *  operatorInterface: The OperatorInterface object.
 */
public class InterfaceFacade {
    private final UserInterface userInterface;
    private final OperatorInterface operatorInterface;

    public InterfaceFacade(UserInterface userInterface, OperatorInterface operatorInterface) {
        this.userInterface = userInterface;
        this.operatorInterface = operatorInterface;
    }

    /**
     * Runs the InterfaceFacade.
     * The IOException will be raised if the given file is not found.
     */
    public void run() throws IOException {
        // As long as the program is running
        boolean running = true;
        while (running) {
            // Check who is running the program: User or Operator.
            InputChecker requestRole = new InputChecker("Who are you (User/Operator): ",
                new isValidRole());
            String role = requestRole.checkCorrectness();
            if (role.equals("User")) {
                this.userInterface.run();
            } else if (role.equals("Operator")) {
                this.operatorInterface.run();
            }


            // Check if the user want to exit the program.
            InputChecker requestContinue = new InputChecker("Do you want to exit? " +
                    "(true/false):",
                    new isValidContinue());

            String whetherContinue = requestContinue.checkCorrectness();

            // Checks if the user wants to add any more courses.
            if (whetherContinue.equals("true")){
                running = false;}

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



    // ============================ Predicates =================================
    private static class isValidRole extends Predicate {
        private final String[] allowed;
        public isValidRole(){
            this.allowed = new String[]{"User", "Operator"};
        }

        @Override
        public boolean run(String prompt) {
            return Arrays.asList(allowed).contains(prompt) ;
        }
    }


    private static class isValidContinue extends Predicate {
        private final String[] allowed;
        public isValidContinue(){
            this.allowed = new String[]{"true", "false"};
        }

        @Override
        public boolean run(String prompt) {
            return Arrays.asList(allowed).contains(prompt) ;
        }
    }
}
