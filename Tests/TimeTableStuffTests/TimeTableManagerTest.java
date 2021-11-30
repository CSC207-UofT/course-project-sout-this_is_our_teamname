package TimeTableStuffTests;

import Helpers.Constants;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Reminder;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableManagerTest {

    @Test
    public void reformat(){
        LocalTime time5 =  LocalTime.of(5,0,0);
        LocalTime time6 =  LocalTime.of(6,0,0);
        LocalTime time9 =  LocalTime.of(9,0,0);
        LocalTime time10 = LocalTime.of(10, 0,0);
        LocalTime time11 = LocalTime.of(11,0,0);
        LocalTime time12 = LocalTime.of(12,0,0);

        // creates Events objects
        CourseSection lecture1 = new CourseSection("MAT257", time9, time11, Constants.MONDAY, Constants.YEAR,
                "LEC 0101", false);
        String description1 = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session" + " at" + " SS100";
        lecture1.setDescription(description1);

        Activity activity = new Activity(time6,time9,Constants.MONDAY,Constants.FALL,"nap");
        activity.addToName("naptime");
        Reminder reminder0 = new Reminder(time5,time6,Constants.MONDAY,Constants.FALL);
        reminder0.addToName("home");
        Reminder reminder1 = new Reminder(time11,time12,Constants.MONDAY,Constants.FALL);
        reminder1.addToName("work");

        //Schedule Events objects from above in TimeTableManager.
        TimeTableManager manager = new TimeTableManager();
        TimeTable timetable = manager.getTimetable(Constants.FALL);
        timetable.schedule(lecture1);
        timetable.schedule(activity);
        timetable.addTasks(reminder0);
        timetable.addTasks(reminder1);

        // reformat fall timetable in manager
        LinkedHashMap<String, ArrayList<ArrayList<String>>> calendar = manager.reformat(timetable);

        // test lecture1
        ArrayList<String> list1 = new ArrayList<>(
                Arrays.asList("CourseSection", "MAT257", "LEC 0101", description1));
        assertEquals(calendar.get(Constants.MONDAY).get(9), list1);
        assertEquals(calendar.get(Constants.MONDAY).get(10), list1);
        //test activity
        ArrayList<String> list2 = new ArrayList<>(
                Arrays.asList("Activity", "naptime", "nap"));
        assertEquals(calendar.get(Constants.MONDAY).get(6), list2);
        assertEquals(calendar.get(Constants.MONDAY).get(7), list2);
        assertEquals(calendar.get(Constants.MONDAY).get(8), list2);
        //test task
        assertEquals(calendar.get(Constants.MONDAY).get(24).get(0), "home");
        assertEquals(calendar.get(Constants.MONDAY).get(24).get(1), "work");

    }

    @Test
    public void addTimeTable() {
        TimeTableManager manager = new TimeTableManager();
        assertTrue(manager.addTimeTable(Constants.SUMMER));
        assertEquals(1, manager.getAllTimeTables().length);
        manager.getTimetable("Winter 2021");
        Set<String> expected = new HashSet<>();
        expected.add("Winter 2021");
        expected.add("Summer");
        Set<String> actual = manager.getTerms();
        assertEquals(actual, expected);
    }

    @Test
    void removeTimeTable() {
        TimeTableManager manager = new TimeTableManager();
        manager.addTimeTable("Winter 2020");
        manager.addTimeTable("Winter 2021");
        assertFalse(manager.removeTimeTable(Constants.FALL));
        assertTrue(manager.removeTimeTable("Winter 2021"));
        assertEquals(1, manager.getAllTimeTables().length);
        Set<String> expected = new HashSet<>();
        expected.add("Winter 2020");
        Set<String> actual = manager.getTerms();
        assertEquals(actual, expected);
    }

    @Test
    void getTimetable() {
        TimeTableManager manager = new TimeTableManager();
        assertNotNull(manager.getTimetable(Constants.FALL));
        assertEquals(1, manager.getAllTimeTables().length);
    }

    @Test
    void testScheduleCourse() {
        LocalTime time9 =  LocalTime.of(9,0,0);
        LocalTime time10 = LocalTime.of(10, 0,0);
        CourseSection lecture = new CourseSection("MAT157", time9,time10,Constants.MONDAY,"Year 2021",
                "LEC 0101", false);
        String description2 = "LEC 0202" + " of " + "Arts and Science" + " with " + "Professor.B" + " by " + "in-person"
                + " session " + " at " + "SS101";
        lecture.setDescription(description2);
        TimeTableManager manager = new TimeTableManager();
        manager.addTimeTable("Winter 2021");
        assertTrue(manager.schedule(lecture));
    }

    @Test
    void testScheduleEvent() {
        LocalTime time6 =  LocalTime.of(6,0,0);
        LocalTime time9 =  LocalTime.of(9,0,0);
        TimeTableManager manager = new TimeTableManager();
        // With current implementation, the timetable manager will automatically create the timetable
        // if it doesn't exist.
        Activity activity = new Activity(time6,time9,Constants.MONDAY,"Winter 2021","nap");
        assertTrue(manager.schedule(activity));
    }

    @Test
    void getAllTimeTables() {
        TimeTableManager manager = new TimeTableManager();
        manager.addTimeTable("Winter 2021");
        manager.addTimeTable("Summer 2021");
        manager.addTimeTable("Fall 2021");
        assertEquals(3, manager.getAllTimeTables().length);
    }
}
