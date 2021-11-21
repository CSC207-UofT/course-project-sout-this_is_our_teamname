package Functions;

import java.util.ArrayList;

/**
 * TRANSCRIBED FROM CODE PROVIDED IN CSC148H1 202101 TERM. PERMISSION TO USE
 * GRANTED BY JONATHAN CALVER (RT #363)
 *
 * An abstract Puzzle class to be solved.
 */
public abstract class Puzzle {
    public abstract boolean failFast();

    public abstract boolean isSolved();

    public abstract Puzzle[] extensions();
}




