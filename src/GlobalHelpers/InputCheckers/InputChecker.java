package GlobalHelpers.InputCheckers;

import java.util.Scanner;

/**
 * A helper class to help check the validity of user inputs.
 *
 * For a class to use this, they must implement a predicate object. It is
 * highly recommended making the predicate a private class within your
 * class that you want to use this in.
 *
 * === Attributes ===
 * prompt: the string prompt to ask the user
 * predicate: the predicate that must be satisfied to accept the response
 */
public class InputChecker {
    private final String prompt;
    private final Predicate predicate;

    /**
     * Constructor. stores the prompt and predicate
     *
     * @param thePrompt the prompt to ask the user
     * @param thePredicate the predicate object that must be true in order to
     *                     accept the response.
     */
    public InputChecker(String thePrompt, Predicate thePredicate){
        this.prompt = thePrompt;
        this.predicate = thePredicate;
    }

    /**
     * Runs the validity checker. Will continuously prompt the user until a
     * correct and valid input is given
     *
     * For instance, if `predicate.run()` returns true iff x % 3 == 0, then
     * the program will continue until is condition is met.
     *
     * @return the accepted input of the user, as a string
     */
    public String checkCorrectness() {
        assert predicate != null;

        String userResponse = "";
        boolean running = true;
        while (running) {
            Scanner InputScanner = new Scanner(System.in);
            System.out.println(prompt);
            userResponse = InputScanner.nextLine();

            if (predicate.run(userResponse)){
                running = false;
            } else {
                System.out.println("Invalid Input. Try again!");
            }
        }
        return userResponse;
    }
}
