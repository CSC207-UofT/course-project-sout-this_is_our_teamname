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
    @BeforeEach
    //public void setUp() {
    //    LocalTime startTime1 = LocalTime.of(9,0,0);
    //    LocalTime startTime2 = LocalTime.of(10, 0,0);
    //    LocalTime endTime1 = LocalTime.of(10,0,0);
    //    LocalTime endTime2 = LocalTime.of(11,0,0);
//
    //    TimeTableObject lecture1 = new Section(startTime1,"2","SS100",Constants.MONDAY,Constants.YEAR,
    //            "MAT257","Gauss","Arts and Science","In Person");
//
    //    TimeTableObject lecture1 = new Section("1","2",Constants.MONDAY,"MY150","MAT257",
    //            "Gauss","Arts and Science","Online");
    //    TimeTableObject lecture2 = new Section("1","3",Constants.MONDAY,"MY151","MAT157",
    //            "Descartes","Arts and Science","Online");
    //    TimeTableObject lecture3 = new Section("1","3",Constants.FRIDAY,"MY151","MAT137",
    //            "Alphonso","Arts and Science","Online");
    //    TimeTable table = new TimeTable();
    //}

    //TODO do we really need to test both schedule and checkConflicts? or am I not doing the test right?
    @Test
    public void schedule() {
        assertTrue(table.schedule(lecture1));
        assertFalse(table.schedule(lecture2));
        assertTrue(table.schedule(lecture3));
    }

    //@Test
    //public void checkConflicts() {
    //    assertTrue(table.checkConflicts(lecture1));
    //    assertFalse(table.checkConflicts(lecture2));
    //}

    @Test
    public void testToString() {
        //toString's hashmap prints the key in different order each time, so I am not sure how to test it.
        //Thought about testing for empty string, but that won't work since the keys are already there to be printed.
        //assertEquals(table.toString(), "");

        //maybe check for specific strings after slicing?
    }
}