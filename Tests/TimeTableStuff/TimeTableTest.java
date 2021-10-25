package TimeTableStuff;

import GlobalHelpers.Constants;

import EntitiesAndObjects.TimeTableObjects.Section;

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
}
