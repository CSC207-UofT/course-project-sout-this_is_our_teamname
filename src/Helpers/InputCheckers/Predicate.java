package Helpers.InputCheckers;

/**
 * TODO DELETE THIS SENTENCE
 *
 * A predicate class for the validityChecker helper. Defines what is an
 * accepting input.
 *
 * It is strongly recommended that you define child classes of this class
 * as private classes in your own class
 */
public abstract class Predicate {
    /**
     * An abstract run method. Will run the predicate
     *
     * @param prompt the prompt to ask the user
     * @return true iff the prompt is to be accepted
     */
    public abstract boolean run(String prompt);
}
