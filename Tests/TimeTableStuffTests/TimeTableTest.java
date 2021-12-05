package TimeTableStuffTests;

import TimeTableObjects.EventObjects.Activity;
import Helpers.Constants;

import TimeTableObjects.EventObjects.CourseSection;

import TimeTableContainers.TimeTable;
import TimeTableObjects.EventObjects.Task;
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

        CourseSection lecture1 = new CourseSection("MAT257", time9, time10, Constants.MONDAY, Constants.YEAR,
                "LEC 0101");
        String description = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session " + " at " + "SS100";
        lecture1.setDescription(description);

        CourseSection lecture2 = new CourseSection("MAT157", time9,time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101");
        String description2 = "LEC 0202" + " of " + "Arts and Science" + " with " + "Professor.B" + " by " + "in-person"
                + " session " + " at " + "SS101";
        lecture2.setDescription(description2);

        CourseSection lecture3 = new CourseSection("MAT137", time10,time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101");
        String description3 = "LEC 0303" + " of " + "Arts and Science" + " with " + "Professor.C" + " by " + "in-person"
                + " session " + " at " + "SS102";
        lecture3.setDescription(description3);

        Activity activity = new Activity(time6,time9,Constants.MONDAY,Constants.FALL,"nap");
        Task reminder = new Task(time5,time6,Constants.MONDAY,
                Constants.FALL);
        reminder.addToName("home");
        TimeTable table = new TimeTable();
//        assertTrue(table.schedule(lecture1));
//        assertFalse(table.schedule(lecture2));
//        assertTrue(table.schedule(lecture3));
        assertTrue(table.schedule(activity));
//        assertTrue(table.schedule(reminder));
    }

    @Test
    public void testToString() {
        LocalTime time5 =  LocalTime.of(5,0,0);
        LocalTime time6 =  LocalTime.of(6,0,0);
        LocalTime time9 =  LocalTime.of(9,0,0);
        LocalTime time10 = LocalTime.of(10, 0,0);
        LocalTime time11 = LocalTime.of(11,0,0);

        CourseSection lecture1 = new CourseSection("MAT257", time9, time10, Constants.MONDAY, Constants.YEAR,
                "LEC 0101");
        String description = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session" + " at " + "SS100";
        lecture1.setDescription(description);

        CourseSection lecture2 = new CourseSection("MAT157", time10, time11,Constants.MONDAY,Constants.FALL,
                "LEC 0201");
        String description2 = "LEC 0202" + " of " + "Arts and Science" + " with " + "Professor.B" + " by " + "in-person"
                + " session" + " at " + "SS101";
        lecture2.setDescription(description2);

        Activity activity = new Activity(time6,time9,Constants.MONDAY,Constants.FALL,"nap");
        Task reminder1 = new Task(time5,time6,Constants.MONDAY,
                Constants.FALL);
        Task reminder2 = new Task(time5,time6,Constants.MONDAY,
                Constants.FALL);
        reminder1.addToName("home");
        reminder2.addToName("test");
        TimeTable table = new TimeTable();
        table.schedule(lecture1);
        table.schedule(lecture2);
        table.schedule(activity);
        table.addTasks(reminder1);
        table.addTasks(reminder2);
        String actual = table.toString();

        String expectedCourse0 = "9:00 MAT257: LEC 0101 of Arts and Science with Professor.A by Online session at SS100";
        String expectedCourse1 = "10:00 MAT157: LEC 0202 of Arts and Science with Professor.B by in-person session at SS101";
        String expectedActivity1 = "6:00 nap";
        String expectedActivity2 = "7:00 nap";
        String expectedActivity3 = "8:00 nap";
        String expectedTask = "Reminders: home, test";
        assertTrue(actual.contains(expectedCourse0));
        assertTrue(actual.contains(expectedCourse1));
        assertTrue(actual.contains(expectedActivity1)
                && actual.contains(expectedActivity2) &&actual.contains(expectedActivity3));
        assertTrue(actual.contains(expectedTask));
    }

    @Test
    public void remove() {
        LocalTime start1 =  LocalTime.of(6,0,0);
        LocalTime end1 =  LocalTime.of(7,0,0);
        Activity dinner = new Activity(start1, end1, Constants.MONDAY, Constants.FALL, "Dinner with Friends" );
        int intStart1 = start1.getHour();

        LocalTime start2 =  LocalTime.of(1,0,0);
        LocalTime end2 =  LocalTime.of(2,0,0);
        Activity walk = new Activity(start2, end2, Constants.WEDNESDAY, Constants.FALL, "Walking the Dog" );
        int intStart2 = start2.getHour();

        TimeTable timeTable = new TimeTable();
        timeTable.schedule(dinner);
        timeTable.schedule(walk);

        timeTable.remove(start1, end1, Constants.MONDAY);

        assertNull(timeTable.getCalendar().get(Constants.MONDAY)[intStart1]);
        assertSame(timeTable.getCalendar().get(Constants.WEDNESDAY)[intStart2], walk);
    }

    @Test
    public void checkConflict() {
        LocalTime time9 =  LocalTime.of(9,0,0);
        LocalTime time10 = LocalTime.of(10, 0,0);

        CourseSection lecture1 = new CourseSection("MAT257", time9, time10, Constants.MONDAY, Constants.YEAR,
                "LEC 0101");
        String description = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session" + " at " + "SS100";
        lecture1.setDescription(description);

        CourseSection lecture2 = new CourseSection("MAT157", time9, time10,Constants.MONDAY,Constants.FALL,
                "LEC 0201");
        String description2 = "LEC 0202" + " of " + "Arts and Science" + " with " + "Professor.B" + " by " + "in-person"
                + " session" + " at " + "SS101";

        TimeTable tt = new TimeTable();
        tt.schedule(lecture1);
        assertFalse(tt.hasConflicts(lecture2));
    }
}
