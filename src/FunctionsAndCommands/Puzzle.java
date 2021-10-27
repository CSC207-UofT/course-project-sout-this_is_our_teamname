package FunctionsAndCommands;

import java.util.ArrayList;
import java.util.Set;

/**
 * TRANSCRIBED FROM CODE PROVIDED IN CSC148H1 202101 TERM. PERMISSION TO USE
 * GRANTED BY JONATHAN CALVER (RT #363)
 */
public abstract class Puzzle {
    public abstract boolean fail_fast();

    public abstract boolean is_solved();

    public abstract Puzzle[] extensions();
}




