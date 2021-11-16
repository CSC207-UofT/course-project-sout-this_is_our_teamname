package Functions;

import java.util.ArrayList;
import java.util.Set;

/**
 * TRANSCRIBED FROM CODE PROVIDED IN CSC148H1 202101 TERM. PERMISSION TO USE
 * GRANTED BY JONATHAN CALVER (RT #363)
 */
public abstract class Solver {
    public abstract ArrayList<Puzzle> solve(Puzzle puzzle, Set<String> seen);
}
