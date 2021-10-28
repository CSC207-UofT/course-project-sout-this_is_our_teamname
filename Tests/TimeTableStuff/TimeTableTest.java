package TimeTableStuff;

import GlobalHelpers.Constants;

import EntitiesAndObjects.TimeTableObjects.CourseSection;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {

    @Test
    public void schedule() {
        LocalTime startTime1 =  LocalTime.of(9,0,0);
        LocalTime startTime2 = LocalTime.of(10, 0,0);
        LocalTime endTime1 = LocalTime.of(10,0,0);
        LocalTime endTime2 = LocalTime.of(11,0,0);

        CourseSection lecture1 = new CourseSection(startTime1,endTime1,"SS100",Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person");
        CourseSection lecture2 = new CourseSection(startTime1,endTime2,"SS101",Constants.MONDAY,Constants.FALL,
                "MAT157", "Descartes","Arts and Science","Online");
        CourseSection lecture3 = new CourseSection(startTime2,endTime2,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online");
        TimeTable table = new TimeTable();
        assertTrue(table.schedule(lecture1));
        assertFalse(table.schedule(lecture2));
        assertTrue(table.schedule(lecture3));
    }
}
