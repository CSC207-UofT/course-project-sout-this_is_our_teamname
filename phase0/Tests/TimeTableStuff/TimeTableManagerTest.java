package TimeTableStuff;

import ConstantsAndExceptions.Constants;
import TimeTableObjects.CourseStuff.Course;
import TimeTableObjects.CourseStuff.Section;
import TimeTableObjects.TimeTableObject;
import TimeTableStuff.TimeTable;
import TimeTableStuff.TimeTableManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Time;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableManagerTest {
    private TimeTableManager manager;
    private Course course;
    private TimeTableObject event;

    @Test
    public void addTimeTable() {
        //TODO Check if the name of the timetable is correct, add successful/not
        TimeTableManager manager = new TimeTableManager();
        assertTrue(manager.addTimeTable(Constants.SUMMER));
        assertEquals(manager.getAllTimeTables().length, 3);
    }

    @Test
    void removeTimeTable() {
        //TODO Check if the name of the timetable is correct, add successful/not
        TimeTableManager manager = new TimeTableManager();
        assertTrue(manager.removeTimeTable(Constants.FALL));
        assertEquals(manager.getAllTimeTables().length, 1);
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