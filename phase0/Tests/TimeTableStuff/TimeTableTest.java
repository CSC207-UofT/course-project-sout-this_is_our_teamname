package TimeTableStuff;

import ConstantsAndExceptions.Constants;

import TimeTableObjects.CourseStuff.Section;
import TimeTableObjects.TimeTableObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {
    private TimeTable table;
    private TimeTableObject lecture1;
    private TimeTableObject lecture2;
    private TimeTableObject lecture3;

    // TODO double check on the format on Sections before running!
    //@BeforeEach
    //public void setUp() {
    //    Time startTime1 = new Time(9,0,0);
    //    Time startTime2 = new Time(10, 0,0);
    //    Time endTime1 = new Time (10,0,0);
    //    Time endTime2 = new Time(11,0,0);
//
    //    TimeTableObject lecture1 = new Section(startTime1,endTime1,"SS100",Constants.MONDAY,Constants.YEAR,
    //            "MAT257","Gauss","Arts and Science","In Person");
    //    TimeTableObject lecture2 = new Section(startTime1,endTime2,"SS101",Constants.MONDAY,Constants.FALL,
    //            "MAT157", "Descartes","Arts and Science","Online");
    //    TimeTableObject lecture3 = new Section(startTime2,endTime2,"SS100",Constants.MONDAY,Constants.FALL,
    //            "MAT137", "Alphonso","Arts and Science","Online");
    //    TimeTable table = new TimeTable();
    //}

    @Test
    public void schedule() {
        Time startTime1 = new Time(9,0,0);
        Time startTime2 = new Time(10, 0,0);
        Time endTime1 = new Time (10,0,0);
        Time endTime2 = new Time(11,0,0);

        Section lecture1 = new Section(startTime1,endTime1,"SS100",Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person");
        Section lecture2 = new Section(startTime1,endTime2,"SS101",Constants.MONDAY,Constants.FALL,
                "MAT157", "Descartes","Arts and Science","Online");
        Section lecture3 = new Section(startTime2,endTime2,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online");
        TimeTable table = new TimeTable();
        assertTrue(table.schedule(lecture1));
        assertFalse(table.schedule(lecture2));
        assertTrue(table.schedule(lecture3));
    }

    //@Test
    //public void checkConflicts() {
    //    Time startTime1 = new Time(9,0,0);
    //    Time startTime2 = new Time(10, 0,0);
    //    Time endTime1 = new Time (10,0,0);
    //    Time endTime2 = new Time(11,0,0);
//
    //    Section lecture1 = new Section(startTime1,endTime1,"SS100",Constants.MONDAY,Constants.YEAR,
    //            "MAT257","Gauss","Arts and Science","In Person");
    //    Section lecture2 = new Section(startTime1,endTime2,"SS101",Constants.MONDAY,Constants.FALL,
    //            "MAT157", "Descartes","Arts and Science","Online");
    //    TimeTable table = new TimeTable();
    //    assertTrue(table.checkConflicts(lecture1));
    //    assertFalse(table.checkConflicts(lecture2));
    //}
//
    //@Test
    //public void testToString() {
        //toString's hashmap prints the key in different order each time, so I am not sure how to test it.
        //Thought about testing for empty string, but that won't work since the keys are already there to be printed.
        //assertEquals(table.toString(), "");

        //maybe check for specific strings after slicing?
    //}
}