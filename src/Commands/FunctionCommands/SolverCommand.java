package Commands.FunctionCommands;

import Commands.Command;
import Functions.DfsSearch;
import Functions.Puzzle;
import Functions.Solver;
import Functions.TimeTablePuzzle;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * THIS IS AN EXAMPLE OF A COMMAND OBJECT. IT IS POORLY DESIGNED ON PURPOSE.
 * TODO PLEASE IMPLEMENT THIS CLASS @TA IF THIS MESSAGE IS STILL HERE WHEN
 * TODO YOU MARK THIS, DO NOT MARK THIS!!!
 */
public class SolverCommand implements Command {
    private final DfsSearch dfsSolver;
    private final TimeTablePuzzle puzzle;

    public SolverCommand(DfsSearch dfsSolver, TimeTablePuzzle puzzle) {
        this.dfsSolver = dfsSolver;
        this.puzzle = puzzle;
    }



    /**
     * Treat this method as like the "main" (psvm method, or if __name__ ==
     * "__main__" method in Python).
     */
    @Override
    public void execute(){
        // TODO Add your main like Method here. The following code is just a
        //  placeholder to show you how everything works.
        Puzzle APuzzleToDemonstrateWhatThisClassDoes = new examplePuzzle();
        Solver ASolverToSolveTheAformentionedPuzzle = new DfsSearch();
        ArrayList<Puzzle> TheSolutionsToTheAforementionedPuzzleAsAnArrayListOfTheStepsItTookToGetFromThePuzzleStateToTheCurrentState = ASolverToSolveTheAformentionedPuzzle.solve(APuzzleToDemonstrateWhatThisClassDoes, new HashSet<>());
        // Do something...
    }

    /**
     * Just an example. TODO Please delete!!!
     *
     */
    private static class examplePuzzle extends Puzzle{
        @Override
        public boolean fail_fast() {
            return false;
        }

        @Override
        public boolean is_solved() {
            return false;
        }

        @Override
        public Puzzle[] extensions() {
            return new Puzzle[0];
        }
    }

    @Override
    public String toString() {
        return "Used the Solver Function";
    }
}
