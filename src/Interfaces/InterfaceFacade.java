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

    // Constants
    public static final String USER = "User";
    public static final String OPERATOR = "Operator";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * Constructor of the InterfaceFacade.
     * Sets userInterface and operatorInterface.
     */
    public InterfaceFacade(UserInterface userInterface, OperatorInterface operatorInterface) {
        this.userInterface = userInterface;
        this.operatorInterface = operatorInterface;
    }

    /**
     * Runs the InterfaceFacade.
     * @exception IOException will be raised if the given file, datasource.txt, is not
     * found.
     */
    public void run() throws IOException {
        // As long as the program is running
        boolean running = true;
        while (running) {
            // Check who is running the program: User or Operator.
            InputChecker requestRole = new InputChecker("Who are you (" +
                    USER + "/" + OPERATOR + "): ", new isValidRole());
            String role = requestRole.checkCorrectness();

            // Execute the UserInterface if user is running the program.
            switch (role){
                case USER:
                    this.userInterface.run();
                case OPERATOR:
                    this.operatorInterface.run();
            }

            // Check whether the user wants to exit the program, if user inputs "true", the while loop will stop.
            InputChecker requestContinue =
                    new InputChecker("Do you want to exit? (" + TRUE +
                            "/" + FALSE + "):", new isValidBoolean());

            String whetherContinue = requestContinue.checkCorrectness();
            if (whetherContinue.equals(TRUE)){
                running = false;
            }
        }
    }

    // ============================ Predicates =================================
    /**
     * A predicate to determine if the role is a valid input
     */
    private static class isValidRole extends Predicate {
        private final String[] allowed;

        /**
         * Constructor
         */
        public isValidRole(){
            this.allowed = new String[]{USER, OPERATOR};
        }

        @Override
        public boolean run(String prompt) {
            return Arrays.asList(allowed).contains(prompt) ;
        }
    }


    /**
     * A predicate to determine if the input is valid boolean.
     */
    private static class isValidBoolean extends Predicate {
        private final String[] allowed;

        public isValidBoolean(){
            this.allowed = new String[]{TRUE, FALSE};
        }

        @Override
        public boolean run(String prompt) {
            return Arrays.asList(allowed).contains(prompt) ;
        }
    }

    /**
     * A InterfaceFacade. The main method of the program and the one that the
     * user interacts with.
     *
     * @exception IOException will be raised if the given file, datasource.txt, is
     * not found.
     *
     * @param args The arguments
     */
    public static void main(String[] args) throws IOException {
        UserInterface user = new UserInterface();
        InterfaceFacade facade = new InterfaceFacade(user, user.getOperator());
        facade.run();
    }
}
