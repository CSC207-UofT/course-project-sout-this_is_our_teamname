package TimeTableStuffTests;

import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.Task;
import GlobalHelpers.Constants;

import EntitiesAndObjects.TimeTableObjects.CourseSection;

import TimeTableStuff.TimeTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {

    @Test
    public void schedule() {
        LocalTime time5 =  LocalTime.of(5,0,0);
        LocalTime time6 =  LocalTime.of(6,0,0);
        LocalTime time9 =  LocalTime.of(9,0,0);
        LocalTime time10 = LocalTime.of(10, 0,0);
        LocalTime time11 = LocalTime.of(11,0,0);

        CourseSection lecture1 = new CourseSection(time9,time10,"SS100",Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person", false);
        CourseSection lecture2 = new CourseSection(time9,time11,"SS101",Constants.MONDAY,Constants.FALL,
                "MAT157", "Descartes","Arts and Science","Online", false);
        CourseSection lecture3 = new CourseSection(time10,time11,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);
        Activity activity = new Activity(time6,time9,Constants.MONDAY,Constants.FALL, "home");
        Task task= new Task(time5,time6,"home",Constants.MONDAY,Constants.FALL);
        TimeTable table = new TimeTable();
        assertTrue(table.schedule(lecture1));
        assertFalse(table.schedule(lecture2));
        assertTrue(table.schedule(lecture3));
        assertTrue(table.schedule(activity));
        assertTrue(table.schedule(task));
    }

    @Test
    public void testToString() {
        LocalTime Time9 =  LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);
        LocalTime Time11 = LocalTime.of(11,0,0);

        CourseSection lecture0 = new CourseSection(Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person", false);
        CourseSection lecture1 = new CourseSection(Time10,Time11,"SS100",Constants.THURSDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);

        TimeTable table = new TimeTable();
        table.schedule(lecture0);
        table.schedule(lecture1);

        String actual = table.toString();

        //TODO: change expected to new format
        String expected = "{Monday=[09:00 - 10:00: MAT257 of Arts and Science with Gauss by In Person at SS100], " +
                           "Tuesday=[], " +
                           "Wednesday=[], " +
                           "Thursday=[10:00 - 11:00: MAT137 of Arts and Science with Alphonso by Online at SS100], " +
                           "Friday=[], " +
                           "Saturday=[], " +
                           "Sunday=[]}";

//        Assertions.assertEquals(expected, actual);
    }
}
