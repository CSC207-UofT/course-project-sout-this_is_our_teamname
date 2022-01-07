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
    /**
     * A method to get all courses from a hashmap
     */
    private ArrayList<ArrayList<Course>> hashMapToArraylist(HashMap<String,
            HashMap<String, ArrayList<Course>>> courses){
        ArrayList<ArrayList<Course>> theList = new ArrayList<>();
        for (HashMap<String, ArrayList<Course>> cor : courses.values()){
            theList.addAll(cor.values());
        }
        return theList;
    }

    @Test
    public void isSolved() {
        TimeTableManager manager = new TimeTableManager();

        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);

        // Create Course A
        Object[] testDateTime1 = {Constants.MONDAY, startTime1, endTime1};
        Object[] testDateTime2 = {Constants.THURSDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap1 = new HashMap<>();
        testDateTimeMap1.put(testDateTime1, "LM161");
        testDateTimeMap1.put(testDateTime2, "LM161");
        Course A = new Course("CSC207","LEC0101", "Gries", "A&S", "In-Person",
                testDateTimeMap1, Constants.FALL);

        // Create Course B
        Object[] testDateTime3 = {Constants.WEDNESDAY, startTime2, endTime2};
        HashMap<Object[], String> testDateTimeMap2 = new HashMap<>();
        testDateTimeMap2.put(testDateTime3, "LM161");
        Course B = new Course("CSC207", "LEC0201", "Calver", "A&S", "In-Person",
                testDateTimeMap2, Constants.FALL);

        // Create Course C
        Object[] testDateTime4 = {Constants.TUESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap3 = new HashMap<>();
        testDateTimeMap3.put(testDateTime4, "LM161");
        Course C = new Course("CSC207", "TUT0101", "TA", "A&S", "In-Person",
                testDateTimeMap3, Constants.FALL);

        // Create Course D
        Object[] testDateTime5 = {Constants.FRIDAY, startTime2, endTime2};
        HashMap<Object[], String> testDateTimeMap4 = new HashMap<>();
        testDateTimeMap4.put(testDateTime5, "LM161");
        Course D = new Course("CSC207", "TUT0201", "TA", "A&S", "In-Person",
                testDateTimeMap4, Constants.FALL);

        // Create Course E
        Object[] testDateTime6 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap5 = new HashMap<>();
        testDateTimeMap5.put(testDateTime6, "LM161");
        Course E = new Course("MAT157", "LEC0101", "Prof", "A&S", "In-Person",
                testDateTimeMap5, Constants.FALL);

        // Create Course F
        Object[] testDateTime7 = {Constants.WEDNESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap6 = new HashMap<>();
        testDateTimeMap6.put(testDateTime7, "LM161");
        Course F = new Course("MAT157", "LEC0201", "Prof", "A&S", "In-Person",
                testDateTimeMap6, Constants.FALL);

        // Create courses HashMap for TimeTablePuzzle
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

        // Schedule Course A
        ArrayList<CourseSection> split = A.split();
        for (CourseSection section : split) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }
        // Schedule Course C
        for (CourseSection section : C.split()) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }
        // Schedule Course E
        for (CourseSection section : E.split()) {
            manager.getTimetable(Constants.FALL).schedule(section);
        }

        TimeTablePuzzle ttPuzzle = new TimeTablePuzzle(courses, manager,
                hashMapToArraylist(courses),new ArrayList<>());
        ttPuzzle.addScheduledCourse(A);
        ttPuzzle.addScheduledCourse(C);
        ttPuzzle.addScheduledCourse(E);
        boolean truth = ttPuzzle.isSolved();
        Assertions.assertTrue(truth);

    }

    @Test
    public void extensions() {
        TimeTableManager manager = new TimeTableManager();

        TimeTableManager managerC = new TimeTableManager();
        TimeTableManager managerD = new TimeTableManager();
        TimeTableManager managerE = new TimeTableManager();
        TimeTableManager managerF = new TimeTableManager();

        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);


        Object[] testDateTime1 = {Constants.MONDAY, startTime1, endTime1};
        Object[] testDateTime2 = {Constants.THURSDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap1 = new HashMap<>();
        testDateTimeMap1.put(testDateTime1, "LM161");
        testDateTimeMap1.put(testDateTime2, "LM161");
        Course A = new Course("CSC207", "LEC0101", "Gries", "A&S", "In-Person",
                testDateTimeMap1, Constants.FALL);
        ArrayList<CourseSection> split = A.split();
        for (CourseSection section : split) {
            manager.getTimetable(Constants.FALL).schedule(section);
            managerC.getTimetable(Constants.FALL).schedule(section);
            managerD.getTimetable(Constants.FALL).schedule(section);
            managerE.getTimetable(Constants.FALL).schedule(section);
            managerF.getTimetable(Constants.FALL).schedule(section);
        }


        Object[] testDateTime3 = {Constants.WEDNESDAY, startTime2, endTime2};
        HashMap<Object[], String> testDateTimeMap2 = new HashMap<>();
        testDateTimeMap2.put(testDateTime3, "LM161");
        Course B = new Course("CSC207", "LEC0201", "Calver", "A&S", "In-Person",
                testDateTimeMap2, Constants.FALL);


        Object[] testDateTime4 = {Constants.TUESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap3 = new HashMap<>();
        testDateTimeMap3.put(testDateTime4, "LM161");
        Course C = new Course("CSC207", "TUT0101", "TA", "A&S", "In-Person",
                testDateTimeMap3, Constants.FALL);


        Object[] testDateTime5 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap4 = new HashMap<>();
        testDateTimeMap4.put(testDateTime5, "LM161");
        Course D = new Course("CSC207", "TUT0201", "TA", "A&S", "In-Person",
                testDateTimeMap4, Constants.FALL);


        Object[] testDateTime6 = {Constants.FRIDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap5 = new HashMap<>();
        testDateTimeMap5.put(testDateTime6, "LM161");
        Course E = new Course("MAT157", "LEC0101", "Prof", "A&S", "In-Person",
                testDateTimeMap5, Constants.FALL);


        Object[] testDateTime7 = {Constants.WEDNESDAY, startTime1, endTime1};
        HashMap<Object[], String> testDateTimeMap6 = new HashMap<>();
        testDateTimeMap6.put(testDateTime7, "LM161");
        Course F = new Course("MAT157", "LEC0201", "Prof", "A&S", "In-Person",
                testDateTimeMap6, Constants.FALL);

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
        TimeTablePuzzle ttPuzzle = new TimeTablePuzzle(courses, manager,
                hashMapToArraylist(courses), new ArrayList<>());
        ttPuzzle.addScheduledCourse(A);

        TimeTablePuzzle ttPuzzleC = new TimeTablePuzzle(courses, managerC,
                hashMapToArraylist(courses), new ArrayList<>());
        ttPuzzleC.addScheduledCourse(A);
        ArrayList<CourseSection> splitC = C.split();
        for (CourseSection section : splitC) {
            String term = section.getTerm();
            ttPuzzleC.getManager().getTimetable(term).schedule(section);
        }

        TimeTablePuzzle ttPuzzleD = new TimeTablePuzzle(courses, managerD,
                hashMapToArraylist(courses), new ArrayList<>());
        ttPuzzleD.addScheduledCourse(A);
        ArrayList<CourseSection> splitD = D.split();
        for (CourseSection section : splitD) {
            String term = section.getTerm();
            ttPuzzleD.getManager().getTimetable(term).schedule(section);
        }

        TimeTablePuzzle ttPuzzleE = new TimeTablePuzzle(courses, managerE,
                hashMapToArraylist(courses), new ArrayList<>());
        ttPuzzleE.addScheduledCourse(A);
        ArrayList<CourseSection> splitE = E.split();
        for (CourseSection section : splitE) {
            String term = section.getTerm();
            ttPuzzleE.getManager().getTimetable(term).schedule(section);
        }

        TimeTablePuzzle ttPuzzleF = new TimeTablePuzzle(courses, managerF,
                hashMapToArraylist(courses), new ArrayList<>());
        ttPuzzleF.addScheduledCourse(A);
        ArrayList<CourseSection> splitF = F.split();
        for (CourseSection section : splitF) {
            String term = section.getTerm();
            ttPuzzleF.getManager().getTimetable(term).schedule(section);
        }

        ArrayList<TimeTablePuzzle> expected = new ArrayList<>
                (Arrays.asList(ttPuzzleC, ttPuzzleD));

        ArrayList<TimeTablePuzzle> actual = ttPuzzle.extensions();

        Assertions.assertEquals(expected.size(), actual.size());

        // Schedule Course C and test again
        for (CourseSection indivSplitC : splitC) {
            String term = indivSplitC.getTerm();
            ttPuzzle.getManager().getTimetable(term).schedule(indivSplitC);
        }
        ttPuzzle.addScheduledCourse(C);

        ArrayList<TimeTablePuzzle> expected2 = new ArrayList<>
                (Arrays.asList(ttPuzzleE, ttPuzzleF));

        ArrayList<TimeTablePuzzle> actual2 = ttPuzzle.extensions();

        Assertions.assertEquals(expected2.size(), actual2.size());
    }

    @Test
    public void schedulePuzzle() {

        LocalTime time9 =  LocalTime.of(9,0,0);
        LocalTime time10 = LocalTime.of(10, 0,0);
        LocalTime time11 = LocalTime.of(11,0,0);

        CourseSection lecture1 = new CourseSection(time9, time10, Constants.MONDAY, Constants.YEAR);
        lecture1.setName("MAT257");
        lecture1.setSectionCode("LEC 0101");
        String description = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session " + " at " + "SS100";
        lecture1.setDescription(description);

        CourseSection lecture2 = new CourseSection(time9,time11,Constants.MONDAY,Constants.FALL);
        lecture2.setName("MAT157");
        lecture2.setSectionCode("LEC 0101");
        String description2 = "LEC 0202" + " of " + "Arts and Science" + " with " + "Professor.B" + " by " + "in-person"
                + " session " + " at " + "SS101";
        lecture2.setDescription(description2);

        CourseSection lecture3 = new CourseSection(time10,time11,Constants.MONDAY,Constants.FALL);
        lecture3.setName("MAT137");
        lecture3.setSectionCode("LEC 0101");
        String description3 = "LEC 0303" + " of " + "Arts and Science" + " with " + "Professor.C" + " by " + "in-person"
                + " session " + " at " + "SS102";
        lecture3.setDescription(description3);

        TimeTableManager manager1 = new TimeTableManager();
        manager1.getTimetable(Constants.FALL).schedule(lecture1);
        manager1.getTimetable(Constants.FALL).schedule(lecture2);
        manager1.getTimetable(Constants.FALL).schedule(lecture3);

        TimeTableManager manager2 = new TimeTableManager();
        manager2.getTimetable(Constants.FALL).schedule(lecture1);

        HashMap<String, HashMap<String, ArrayList<Course>>> courses = new HashMap<>();

        TimeTablePuzzle puzzle1 = new TimeTablePuzzle(courses, manager1,
                hashMapToArraylist(courses), new ArrayList<>());
        TimeTablePuzzle puzzle2 = new TimeTablePuzzle(courses, manager2,
                hashMapToArraylist(courses), new ArrayList<>());
        puzzle2.schedulePuzzle(puzzle1);
        // Get all courses for both TimeTablePuzzles
        ArrayList<CourseSection> allCourses1 = puzzle1.getManager().getCourses();
        ArrayList<CourseSection> allCourses2 = puzzle2.getManager().getCourses();

        Assertions.assertEquals(allCourses1, allCourses2);

    }
}
