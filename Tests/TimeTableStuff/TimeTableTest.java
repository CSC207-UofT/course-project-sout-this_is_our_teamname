package TimeTableStuff;

import GlobalHelpers.Constants;

import EntitiesAndObjects.TimeTableObjects.CourseSection;
import EntitiesAndObjects.TimeTableObjects.Events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {

    @Test
    public void schedule() {
        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);

        CourseSection lecture1 = new CourseSection(startTime1, endTime1, "SS100", Constants.MONDAY, Constants.YEAR,
                "MAT257", "Gauss", "Arts and Science", "In Person", false);
        CourseSection lecture2 = new CourseSection(startTime1, endTime2, "SS101", Constants.MONDAY, Constants.FALL,
                "MAT157", "Descartes", "Arts and Science", "Online", false);
        CourseSection lecture3 = new CourseSection(startTime2, endTime2, "SS100", Constants.MONDAY, Constants.FALL,
                "MAT137", "Alphonso", "Arts and Science", "Online", false);
        TimeTable table = new TimeTable();
        assertTrue(table.schedule(lecture1));
        assertFalse(table.schedule(lecture2));
        assertTrue(table.schedule(lecture3));
    }

    @Test
    public void testToString() {
        LocalTime Time9 = LocalTime.of(9, 0, 0);
        LocalTime Time10 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime Time11 = LocalTime.of(11, 0, 0);

        CourseSection lecture0 = new CourseSection(Time9, Time10, "SS100", Constants.MONDAY, Constants.YEAR,
                "MAT257", "Gauss", "Arts and Science", "In Person", false);
        CourseSection lecture1 = new CourseSection(Time10, Time11, "SS100", Constants.THURSDAY, Constants.FALL,
                "MAT137", "Alphonso", "Arts and Science", "Online", false);

        TimeTable table = new TimeTable();
        table.schedule(lecture0);
        table.schedule(lecture1);

        String actual = table.toString();

        String expected = "{Monday=[09:00 - 10:00: MAT257 of Arts and Science with Gauss by In Person at SS100], " +
                "Tuesday=[], " +
                "Wednesday=[], " +
                "Thursday=[10:00 - 11:00: MAT137 of Arts and Science with Alphonso by Online at SS100], " +
                "Friday=[], " +
                "Saturday=[], " +
                "Sunday=[]}";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkCourse() {
        LocalTime startTime1 = LocalTime.of(9, 0, 0);
        LocalTime startTime2 = LocalTime.of(10, 0, 0);
        LocalTime endTime1 = LocalTime.of(10, 0, 0);
        LocalTime endTime2 = LocalTime.of(11, 0, 0);

        CourseSection lecture1 = new CourseSection(startTime1, endTime1, "SS100", Constants.MONDAY, Constants.YEAR,
                "MAT257LEC0101", "Gauss", "Arts and Science", "In Person", false);
        CourseSection lecture2 = new CourseSection(startTime2, endTime2, "SS101", Constants.MONDAY, Constants.FALL,
                "MAT257LEC0201", "Descartes", "Arts and Science", "Online", false);
        CourseSection lecture3 = new CourseSection(startTime2, endTime2, "SS100", Constants.TUESDAY, Constants.FALL,
                "MAT257PRA0101", "Alphonso", "Arts and Science", "Online", false);
        CourseSection lecture4 = new CourseSection(startTime2, endTime2, "BA1160", Constants.WEDNESDAY, Constants.FALL,
                "CSC207LEC0101", "Calver", "Arts and Science", "In Person", false);
        CourseSection lecture5 = new CourseSection(startTime1, endTime1, "BA1160", Constants.WEDNESDAY, Constants.FALL,
                "CSC207TUT0101", "TA", "Arts and Science", "Online", false);
        CourseSection lecture6 = new CourseSection(startTime1, endTime1, "BA1160", Constants.THURSDAY, Constants.FALL,
                "CSC236TUT0101", "TA", "Arts and Science", "Online", false);

        TimeTable table = new TimeTable();
        table.schedule(lecture1);
        table.schedule(lecture2);
        table.schedule(lecture3);
        table.schedule(lecture4);
        table.schedule(lecture5);
        table.schedule(lecture6);
//        ArrayList<String> courses = new ArrayList<String>();
//        courses.add("CSC236");
//        courses.add("MAT257");
        ArrayList<Events> actual = table.checkCourse("MAT257");

        ArrayList<Events> expected = new ArrayList<>();
        expected.add(lecture1);
        expected.add(lecture2);
        expected.add(lecture3);

        Assertions.assertEquals(expected, actual);
    }
}
