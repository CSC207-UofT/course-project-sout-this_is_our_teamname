package Functions;

import TimeTableObjects.Course;

import java.util.ArrayList;
import java.util.Set;

/**
 * A Depth first search program
 *
 * TRANSCRIBED FROM CODE WRITTEN USED IN CSC148H1 202101 TERM. PERMISSION TO
 * USE GRANTED BY JONATHAN CALVER (RT #363)
 *
 */
public class DfsSearch {
    ArrayList<TimeTablePuzzle> path;

    /**
     * Constructor. Initializes a FunctionsAndCommands.DfsSearch object
     */
    public DfsSearch(){
        this.path = new ArrayList<>();
    }

    /**
     * Return if the path already contains the puzzle.
     *
     * @param puzzle the puzzle to be checked
     * @return true if the puzzle is in the path.
     */
    private boolean SearchPath(TimeTablePuzzle puzzle){
        for (TimeTablePuzzle item : path){
            if (item == puzzle){
                return true;
            }
        }
        return false;
    }

    /**
     * Solves the puzzle
     * @param puzzle The puzzle that needs to be solved
     * @param seen The set of all puzzle configurations that has been seen
     * @return An array of all the moves that led up to the solution.
     */

    public ArrayList<TimeTablePuzzle> solve(TimeTablePuzzle puzzle, Set<TimeTablePuzzle> seen) {

        // If the puzzle is already solved
        if (puzzle.isSolved()){
            this.path.add(puzzle);
            return this.path;
        }

        // If the puzzle is not solved and is possible
        else {
            TimeTablePuzzle next_item = puzzle;
            path.add(next_item);


            // While the puzzle is not solved.
            while (!next_item.isSolved()){
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
                    seen.add(next_item);
                }

                // Add the next move to the puzzle if it is not null.
                else {
                    path.add(next_item);
                    // Add the next move(scheduled course) to the puzzle accumulator
                    Course next_course = next_item.getExtendedCourse();
                    puzzle.addScheduledCourse(next_course);
                    next_item.addScheduledCourse(next_course);
                }
            }
        }
        return path;
    }

    // ============================= Helpers ===================================

    /**
     * A helper class to get the next move of the solver
     *
     * @param puzzle the current puzzle
     * @param seen a set of all the puzzle states seen.
     *
     * @return the puzzle state of the next move. Returns null if puzzle
     * cannot be solved.
     */
    private TimeTablePuzzle getNextMove(TimeTablePuzzle puzzle, Set<TimeTablePuzzle> seen) {
        ArrayList<TimeTablePuzzle> configs = puzzle.extensions();

        // If there are no extensions to the puzzle, or the puzzle cannot be solved.
        // if (configs.length == 0 || puzzle.failFast()){
        if (configs.size() == 0){
            seen.add(puzzle);
            return null;
        }

        for (TimeTablePuzzle possible_move : configs){
            if (possible_move.isSolved()){
                return possible_move;
            }
            // If the puzzle:
            // - is not in seen
            // - the puzzle is possible
            // - is not in the path
            else if (!seen.contains(possible_move) && !SearchPath(possible_move)){
                return possible_move;
            }
        }
        // If all possible moves are not valid
        return null;
    }
}
