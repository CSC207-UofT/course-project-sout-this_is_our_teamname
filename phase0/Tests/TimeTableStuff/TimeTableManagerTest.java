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

    @BeforeEach
    public void setUp() {
        TimeTableManager manager = new TimeTableManager();
        Course course = new Course("CSC207H1", "Paul Gries", "Faculty of Arts and Science",
                "In Person", new HashMap<>(), "Fall");
        TimeTableObject event = new TimeTableObject(new Time(3,2,0), new Time(4,2,0),
                "Home",
                "2021.12.10", "Fall");
    }


    @Test
    public void addTimeTable() {
        //TODO Check if the name of the timetable is correct, add successful/not
        assertTrue(manager.addTimeTable(Constants.SUMMER));
        assertEquals(manager.getAllTimeTables().length, 3);
    }

    @Test
    void removeTimeTable() {
        //TODO Check if the name of the timetable is correct, add successful/not
        assertTrue(manager.removeTimeTable(Constants.FALL));
        assertEquals(manager.getAllTimeTables().length, 1);
    }

    @Test
    void getTimetable() {
        assertNotNull(manager.getTimetable(Constants.FALL));
    }
}
//    @Test
//    void testScheduleCourse() {
//        try{
//            manager.schedule(course);
//        }
//        C
//    }

//    @Test
//    void testScheduleEvent() {
//    }
//
//    @Test
//    void getAllTimeTables() {
//    }
//}