package Domain;

import java.util.ArrayList;
import java.util.Set;

/**
 * A Depth first search program
 *
 */
public class DfsSearch extends Solver{
    /**
     * A Depth first search program
     *
     */
    ArrayList<Puzzle> path;

    /**
     * Constructor. Initializes a DfsSearch object
     */
    public DfsSearch(){
        super();
        this.path = new ArrayList<>();
    }

    /**
     * Return if the path already contains the puzzle.
     *
     * @param puzzle the puzzle to be checked
     * @return true if the puzzle is in the path.
     */
    private boolean SearchPath(Puzzle puzzle){
        for (Puzzle item : path){
            if (item == puzzle){
                return true;
            }
        }
        return false;
    }

    /**
     * A helper class to get the next move of the solver
     *
     * @param puzzle the current puzzle
     * @param seen a set of all the puzzle states seen.
     *
     * @return the puzzle state of the next move. Returns null if puzzle
     * cannot be solved.
     */
    private Puzzle getNextMove(Puzzle puzzle, Set<String> seen){
        Puzzle[] configs = puzzle.extensions();

        // If there are no extensions to the puzzle, or the puzzle cannot be
        // solved.
        if (configs.length == 0 || puzzle.fail_fast()){
            seen.add(puzzle.toString());
            return null;
        }

        for (Puzzle possible_move : configs){
            // If the puzzle:
            // - is not in seen
            // - the puzzle is possible
            // - is not in the path
            if (!seen.contains(puzzle.toString()) && !possible_move.fail_fast()
                    && !SearchPath(possible_move)){
                return possible_move;
            }
        }
        // If all possible moves are not valid
        return null;
    }

    /**
     * Solves the puzzle
     * @param puzzle The puzzle that needs to be solved
     * @param seen The set of all puzzle configurations that has been seen
     * @return An array of all the moves that led up to the solution.
     */
    @Override
    public ArrayList<Puzzle> solve(Puzzle puzzle, Set<String> seen) {
        // If the puzzle is not possible
        if (puzzle.fail_fast()) {
            seen.add(puzzle.toString());
            return new ArrayList<>();
        }

        // If the puzzle is already solved
        else if (puzzle.is_solved()){
            this.path.add(puzzle);
            return this.path;
        }

        // If the puzzle is not solved and is possible
        else {
            Puzzle next_item = puzzle;
            path.add(next_item);

            // While the puzzle is not solved.
            while (!next_item.is_solved()){
                next_item = getNextMove(next_item, seen);

                // If there cannot be a next move generated and the puzzle is
                // the only object remaining in the path
                if (next_item == null && this.path.size() == 1) {
                    next_item = puzzle;
                }

                // If the next item cannot be generated and the path is empty
                else if (next_item == null && this.path.size() == 0){
                    return new ArrayList<>();
                }

                // If the next move cannot be generated and there are more
                // than one puzzle config in the path
                else if (next_item == null){
                    next_item = path.remove(path.size() - 1);
                    seen.add(next_item.toString());
                }

                // Add the next move to the puzzle if it is not null.
                else {
                    path.add(next_item);
                }
            }
        }
        return path;
    }
}
