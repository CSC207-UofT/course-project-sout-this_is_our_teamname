package TimeTableStuffTests;

import GlobalHelpers.Constants;
import TimeTableStuff.TimeTableManager;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableManagerTest {

    @Test
    public void addTimeTable() {
        TimeTableManager manager = new TimeTableManager();
        assertTrue(manager.addTimeTable(Constants.SUMMER));
        assertEquals(manager.getAllTimeTables().length, 3);
        Set<String> expected = new HashSet<>();
        expected.add("Winter");
        expected.add("Summer");
        expected.add("Fall");
        Set<String> actual = manager.getTerms();
        assertEquals(actual, expected);
    }

    @Test
    void removeTimeTable() {
        TimeTableManager manager = new TimeTableManager();
        assertTrue(manager.removeTimeTable(Constants.FALL));
        assertEquals(manager.getAllTimeTables().length, 1);
        Set<String> expected = new HashSet<>();
        expected.add("Winter");
        Set<String> actual = manager.getTerms();
        assertEquals(actual, expected);
    }

    @Test
    void getTimetable() {
        TimeTableManager manager = new TimeTableManager();
        assertNotNull(manager.getTimetable(Constants.FALL));
    }

    @Test
    void testScheduleCourse() {
    }

    @Test
    void testScheduleEvent() {
    }

    @Test
    void getAllTimeTables() {
    }
}
