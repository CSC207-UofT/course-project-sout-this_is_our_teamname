package Domain;

import java.util.ArrayList;
import java.util.Set;

public abstract class Puzzle {
    public abstract boolean fail_fast();

    public abstract boolean is_solved();

    public abstract Puzzle[] extensions();
}

abstract class Solver {
    abstract ArrayList<Puzzle> solve(Puzzle puzzle, Set<String> seen);
}


