package FunctionsTests;

import Controllers.CommandFactory;
import Controllers.DatabaseController;
import Functions.DfsSearch;
import Functions.Puzzle;
import Functions.TimeTablePuzzle;
import Helpers.Constants;
import Helpers.InvalidInputException;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.*;

public class DFSTest {

    @Test
    public void solve() {
        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);


        Object[] testDateTime1 = {Constants.MONDAY, startTime1, endTime1};
        Object[] testDateTime2 = {Constants.TUESDAY, startTime1, endTime1};

        HashMap<Object[], String> testDateTimeMap1 = new HashMap<>();
        testDateTimeMap1.put(testDateTime1, "LM161");
        testDateTimeMap1.put(testDateTime2, "LM161");
        Course A = new Course("CSC207","LEC0101", "Gries", "A&S", "In-Person",
                testDateTimeMap1, Constants.FALL, false);


        Object[] testDateTime3 = {Constants.WEDNESDAY, startTime1, endTime1};
        Object[] testDateTime4 = {Constants.THURSDAY, startTime1, endTime1};

        HashMap<Object[], String> testDateTimeMap2 = new HashMap<>();
        testDateTimeMap2.put(testDateTime3, "LM161");
        testDateTimeMap2.put(testDateTime4, "LM161");

        Course B = new Course("CSC207","LEC0201", "Gries", "A&S", "In-Person",
                testDateTimeMap2, Constants.FALL, false);

        Object[] testDateTime5 = {Constants.FRIDAY, startTime1, endTime2};


        HashMap<Object[], String> testDateTimeMap3 = new HashMap<>();
        testDateTimeMap3.put(testDateTime5, "LM161");


        Course C = new Course("CSC207","LEC0301", "Gries", "A&S", "In-Person",
                testDateTimeMap3, Constants.FALL, false);

        Object[] testDateTime6 = {Constants.MONDAY, startTime2, endTime2};

        HashMap<Object[], String> testDateTimeMap4 = new HashMap<>();
        testDateTimeMap4.put(testDateTime6, "LM161");

        Course D = new Course("CSC207","TUT0101", "Gries", "A&S", "In-Person",
                testDateTimeMap4, Constants.FALL, false);

        ArrayList<CourseSection> split = D.split();



        Object[] testDateTime7 = {Constants.TUESDAY, startTime2, endTime2};

        HashMap<Object[], String> testDateTimeMap5 = new HashMap<>();
        testDateTimeMap5.put(testDateTime7, "LM161");

        Course E = new Course("CSC207","TUT0201", "Gries", "A&S", "In-Person",
                testDateTimeMap5, Constants.FALL, false);


        Object[] testDateTime8 = {Constants.WEDNESDAY, startTime2, endTime2};

        HashMap<Object[], String> testDateTimeMap6 = new HashMap<>();
        testDateTimeMap6.put(testDateTime8, "LM161");

        Course F = new Course("CSC207","TUT0301", "Gries", "A&S", "In-Person",
                testDateTimeMap6, Constants.FALL, false);


        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();
        HashMap<String, ArrayList<Course>> csc = new HashMap<>();
        ArrayList<Course> cscLec = new ArrayList<>(Arrays.asList(A, B, C));
        ArrayList<Course> cscTut = new ArrayList<>(Arrays.asList(D, E, F));
        csc.put("LEC", cscLec);
        csc.put("TUT", cscTut);

        courses.put("CSC207", csc);

        TimeTableManager manager = new TimeTableManager();

        TimeTablePuzzle puzzle = new TimeTablePuzzle(courses, manager);

        DfsSearch solver = new DfsSearch();

        HashSet<String> seen = new HashSet<>();
        ArrayList<Puzzle> solved = solver.solve(puzzle, seen);
        int lastIndex = solved.size() - 1;
        Assertions.assertFalse(solved.get(lastIndex) == puzzle);

    }
}
