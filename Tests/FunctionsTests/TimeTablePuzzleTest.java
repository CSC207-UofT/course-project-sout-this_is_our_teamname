package FunctionsTests;

import Functions.TimeTablePuzzle;
import TimeTableObjects.Course;
import Helpers.Constants;

import TimeTableObjects.EventObjects.CourseSection;

import TimeTableContainers.TimeTableManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TimeTablePuzzleTest {

    @Test
    public void isSolved() {
        TimeTableManager manager = new TimeTableManager();

        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);


        Object[] testDateTime1 = {Constants.MONDAY, startTime1, endTime1};
        Object[] testDateTime2 = {Constants.THURSDAY, startTime1, endTime1};

        HashMap<Object[], String> testDateTimeMap1 = new HashMap<>();
        testDateTimeMap1.put(testDateTime1, "LM161");
        testDateTimeMap1.put(testDateTime2, "LM161");
        Course A = new Course("CSC207LEC0101", "Gries", "A&S", "In-Person",
                testDateTimeMap1, Constants.FALL, false);
        ArrayList<CourseSection> split = A.split();
        for (CourseSection section : split) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }


        Object[] testDateTime3 = {Constants.WEDNESDAY, startTime2, endTime2};
        HashMap<Object[], String> testDateTimeMap2 = new HashMap<>();
        testDateTimeMap2.put(testDateTime3, "LM161");
        Course B = new Course("CSC207LEC0201", "Calver", "A&S", "In-Person",
                testDateTimeMap2, Constants.FALL, false);


        Object[] testDateTime4 = {Constants.TUESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap3 = new HashMap<>();
        testDateTimeMap3.put(testDateTime4, "LM161");
        Course C = new Course("CSC207TUT0101", "TA", "A&S", "In-Person",
                testDateTimeMap3, Constants.FALL, false);
        for (CourseSection section : C.split()) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }


        Object[] testDateTime5 = {Constants.FRIDAY, startTime2, endTime2};
        HashMap<Object[], String> testDateTimeMap4 = new HashMap<>();
        testDateTimeMap4.put(testDateTime5, "LM161");
        Course D = new Course("CSC207TUT0201", "TA", "A&S", "In-Person",
                testDateTimeMap4, Constants.FALL, false);


        Object[] testDateTime6 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap5 = new HashMap<>();
        testDateTimeMap5.put(testDateTime6, "LM161");
        Course E = new Course("MAT157LEC0101", "Prof", "A&S", "In-Person",
                testDateTimeMap5, Constants.FALL, false);
        for (CourseSection section : E.split()) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }


        Object[] testDateTime7 = {Constants.WEDNESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap6 = new HashMap<>();
        testDateTimeMap6.put(testDateTime7, "LM161");
        Course F = new Course("MAT157LEC0201", "Prof", "A&S", "In-Person",
                testDateTimeMap6, Constants.FALL, false);


        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();
        HashMap<String, ArrayList<Course>> csc = new HashMap<>();
        ArrayList<Course> cscLec = new ArrayList<>(Arrays.asList(A, B));
        ArrayList<Course> cscTut = new ArrayList<>(Arrays.asList(C, D));
        csc.put("LEC", cscLec);
        csc.put("TUT", cscTut);

        HashMap<String, ArrayList<Course>> mat = new HashMap<>();
        ArrayList<Course> matLec = new ArrayList<>(Arrays.asList(E, F));
        mat.put("LEC", matLec);

        courses.put("CSC207", csc);
        courses.put("MAT157", mat);

        TimeTablePuzzle ttPuzzle = new TimeTablePuzzle(courses, manager);
        boolean truth = ttPuzzle.isSolved();
        Assertions.assertTrue(truth);

    }

    @Test
    public void failFast() {
        TimeTableManager manager = new TimeTableManager();

        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);


        Object[] testDateTime1 = {Constants.MONDAY, startTime1, endTime1};
        Object[] testDateTime2 = {Constants.THURSDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap1 = new HashMap<>();
        testDateTimeMap1.put(testDateTime1, "LM161");
        testDateTimeMap1.put(testDateTime2, "LM161");
        Course A = new Course("CSC207LEC0101", "Gries", "A&S", "In-Person",
                testDateTimeMap1, Constants.FALL, false);
        ArrayList<CourseSection> split = A.split();
        for (CourseSection section : split) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }


        Object[] testDateTime3 = {Constants.WEDNESDAY, startTime2, endTime2};
        HashMap<Object[], String> testDateTimeMap2 = new HashMap<>();
        testDateTimeMap2.put(testDateTime3, "LM161");
        Course B = new Course("CSC207LEC0201", "Calver", "A&S", "In-Person",
                testDateTimeMap2, Constants.FALL, false);


        Object[] testDateTime4 = {Constants.TUESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap3 = new HashMap<>();
        testDateTimeMap3.put(testDateTime4, "LM161");
        Course C = new Course("CSC207TUT0101", "TA", "A&S", "In-Person",
                testDateTimeMap3, Constants.FALL, false);


        Object[] testDateTime5 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap4 = new HashMap<>();
        testDateTimeMap4.put(testDateTime5, "LM161");
        Course D = new Course("CSC207TUT0201", "TA", "A&S", "In-Person",
                testDateTimeMap4, Constants.FALL, false);


        Object[] testDateTime6 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap5 = new HashMap<>();
        testDateTimeMap5.put(testDateTime6, "LM161");
        Course E = new Course("MAT157LEC0101", "Prof", "A&S", "In-Person",
                testDateTimeMap5, Constants.FALL, false);
        for (CourseSection section : E.split()) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }


        Object[] testDateTime7 = {Constants.WEDNESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap6 = new HashMap<>();
        testDateTimeMap6.put(testDateTime7, "LM161");
        Course F = new Course("MAT157LEC0201", "Prof", "A&S", "In-Person",
                testDateTimeMap6, Constants.FALL, false);

        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();
        HashMap<String, ArrayList<Course>> csc = new HashMap<>();
        ArrayList<Course> cscLec = new ArrayList<>(Arrays.asList(A, B));
        ArrayList<Course> cscTut = new ArrayList<>(Arrays.asList(C, D));
        csc.put("LEC", cscLec);
        csc.put("TUT", cscTut);

        HashMap<String, ArrayList<Course>> mat = new HashMap<>();
        ArrayList<Course> matLec = new ArrayList<>(Arrays.asList(E, F));
        mat.put("LEC", matLec);

        courses.put("CSC207", csc);
        courses.put("MAT157", mat);

        TimeTablePuzzle ttPuzzle = new TimeTablePuzzle(courses, manager);
        Assertions.assertFalse(ttPuzzle.failFast());


        for (CourseSection section : C.split()) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }
        Assertions.assertTrue(ttPuzzle.failFast());

    }

    @Test
    public void extensions() {
        TimeTableManager manager = new TimeTableManager();

        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);


        Object[] testDateTime1 = {Constants.MONDAY, startTime1, endTime1};
        Object[] testDateTime2 = {Constants.THURSDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap1 = new HashMap<>();
        testDateTimeMap1.put(testDateTime1, "LM161");
        testDateTimeMap1.put(testDateTime2, "LM161");
        Course A = new Course("CSC207LEC0101", "Gries", "A&S", "In-Person",
                testDateTimeMap1, Constants.FALL, false);
        ArrayList<CourseSection> split = A.split();
        for (CourseSection section : split) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }


        Object[] testDateTime3 = {Constants.WEDNESDAY, startTime2, endTime2};
        HashMap<Object[], String> testDateTimeMap2 = new HashMap<>();
        testDateTimeMap2.put(testDateTime3, "LM161");
        Course B = new Course("CSC207LEC0201", "Calver", "A&S", "In-Person",
                testDateTimeMap2, Constants.FALL, false);


        Object[] testDateTime4 = {Constants.TUESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap3 = new HashMap<>();
        testDateTimeMap3.put(testDateTime4, "LM161");
        Course C = new Course("CSC207TUT0101", "TA", "A&S", "In-Person",
                testDateTimeMap3, Constants.FALL, false);


        Object[] testDateTime5 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap4 = new HashMap<>();
        testDateTimeMap4.put(testDateTime5, "LM161");
        Course D = new Course("CSC207TUT0201", "TA", "A&S", "In-Person",
                testDateTimeMap4, Constants.FALL, false);


        Object[] testDateTime6 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap5 = new HashMap<>();
        testDateTimeMap5.put(testDateTime6, "LM161");
        Course E = new Course("MAT157LEC0101", "Prof", "A&S", "In-Person",
                testDateTimeMap5, Constants.FALL, false);


        Object[] testDateTime7 = {Constants.WEDNESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap6 = new HashMap<>();
        testDateTimeMap6.put(testDateTime7, "LM161");
        Course F = new Course("MAT157LEC0201", "Prof", "A&S", "In-Person",
                testDateTimeMap6, Constants.FALL, false);

        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();
        HashMap<String, ArrayList<Course>> csc = new HashMap<>();
        ArrayList<Course> cscLec = new ArrayList<>(Arrays.asList(A, B));
        ArrayList<Course> cscTut = new ArrayList<>(Arrays.asList(C, D));
        csc.put("LEC", cscLec);
        csc.put("TUT", cscTut);

        HashMap<String, ArrayList<Course>> mat = new HashMap<>();
        ArrayList<Course> matLec = new ArrayList<>(Arrays.asList(E, F));
        mat.put("LEC", matLec);

        courses.put("CSC207", csc);
        courses.put("MAT157", mat);
        TimeTablePuzzle ttPuzzle = new TimeTablePuzzle(courses, manager);

        ArrayList<Course> expected = new ArrayList<>(
            Arrays.asList(C, D, E, F));

        ArrayList<Course> actual = ttPuzzle.extensions();

        Assertions.assertEquals(expected, actual);
    }
}
