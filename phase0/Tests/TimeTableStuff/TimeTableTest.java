package TimeTableStuff;

import TimeTableObjects.CourseStuff.Section;
import TimeTableObjects.TimeTableObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {
    TimeTable table;
    TimeTableObject lecture1;
    TimeTableObject lecture2;
    TimeTableObject lecture3;

    // TODO double check on the format on Sections before running!
    @BeforeEach
    public void setUp() {
        TimeTableObject lecture1 = new Section("1","2","MONDAY","MY150","MAT257",
                "Gauss","Arts and Science","Online");
        TimeTableObject lecture2 = new Section("1","3","MONDAY","MY151","MAT157",
                "Descartes","Arts and Science","Online");
        TimeTableObject lecture3 = new Section("1","3","FRIDAY","MY151","MAT137",
                "Alphonso","Arts and Science","Online");
        TimeTable table = new TimeTable();
    }

    //TODO do we really need to test both schedule and checkConflicts? or am I not doing the test right?
    @Test
    public void schedule() {
        assertTrue(table.schedule(lecture1));
        assertFalse(table.schedule(lecture2));
    }

    @Test
    public void checkConflicts() {
        assertTrue(table.checkConflicts(lecture1));
        assertFalse(table.checkConflicts(lecture2));

    }

    @Test
    public void testToString() {
    }
}