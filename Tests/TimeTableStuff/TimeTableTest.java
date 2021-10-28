package TimeTableStuff;

import GlobalHelpers.Constants;

import EntitiesAndObjects.TimeTableObjects.CourseSection;

import org.junit.jupiter.api.Test;

import java.sql.Time;


import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {

    @Test
    public void schedule() {
        Time startTime1 = new Time(9,0,0);
        Time startTime2 = new Time(10, 0,0);
        Time endTime1 = new Time (10,0,0);
        Time endTime2 = new Time(11,0,0);

        CourseSection lecture1 = new CourseSection(startTime1,endTime1, "Professor: Gauss, Faculty: " +
                "Arts and Science, Delivery Method: In Person, Location: SS100", Constants.MONDAY, Constants.YEAR,
                 "MAT257");
        CourseSection lecture2 = new CourseSection(startTime1,endTime2, "Professor: Descartes, Faculty: " +
                "Arts and Science, Delivery Method: Online, Location: SS101",Constants.MONDAY,Constants.FALL,
                "MAT157");
        CourseSection lecture3 = new CourseSection(startTime2,endTime2,"Professor: Alphonso, Faculty: " +
                "Arts and Science, Delivery Method: Online, Location: SS100",Constants.MONDAY,Constants.FALL,
                "MAT137");
        TimeTable table = new TimeTable();
        assertTrue(table.schedule(lecture1));
        assertFalse(table.schedule(lecture2));
        assertTrue(table.schedule(lecture3));
    }
}
