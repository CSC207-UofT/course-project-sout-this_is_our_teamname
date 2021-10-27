package FunctionsAndCommands.Commands;

import FunctionsAndCommands.DfsSearch;
import FunctionsAndCommands.Puzzle;
import FunctionsAndCommands.Solver;
import TimeTableStuff.TimeTableManager;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * THIS IS AN EXAMPLE OF A COMMAND OBJECT. IT IS POORLY DESIGNED ON PURPOSE.
 * TODO PLEASE IMPLEMENT THIS CLASS
 */
public class SolverCommand implements Command {
    private final TimeTableManager ATimeTableManagerOrAnthingElseToConnectTo;

    /**
     * Constructor. Feel free to delete if you don't need.
     */
    public SolverCommand(){
        ATimeTableManagerOrAnthingElseToConnectTo = new TimeTableManager();
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
//      ttbmg.schedule(TheSolutionsToTheAforementionedPuzzleAsAnArrayListOfTheStepsItTookToGetFromThePuzzleStateToTheCurrentState.get(TheSolutionsToTheAforementionedPuzzleAsAnArrayListOfTheStepsItTookToGetFromThePuzzleStateToTheCurrentState.size() - 1));
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
}
