package TimeTableStuffTests;

import Helpers.Constants;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.EventObjects.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableManagerTest {

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
        CourseSection lecture = new CourseSection(time9,time10,Constants.MONDAY,"Year 2021");
        lecture.setName("MAT157");
        lecture.setSectionCode("LEC 0101");
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
        Activity activity = new Activity(time6,time9,Constants.MONDAY,"Winter 2021");
        activity.setDescription("nap");
        assertTrue(manager.schedule(activity));
    }

    @Test
    void testScheduleTask() {
        LocalTime start =  LocalTime.of(0,0,0);
        LocalTime end =  LocalTime.of(0,0,0);
        TimeTableManager manager = new TimeTableManager();
        // With current implementation, the timetable manager will automatically create the timetable
        // if it doesn't exist.
        Task task = new Task("", Constants.TUESDAY, "Fall 2021");
        assertTrue(manager.schedule(task));
    }

    @Test
    void getAllTimeTables() {
        TimeTableManager manager = new TimeTableManager();
        manager.addTimeTable("Winter 2021");
        manager.addTimeTable("Summer 2021");
        manager.addTimeTable("Fall 2021");
        assertEquals(3, manager.getAllTimeTables().length);
    }

    @Test
    public void hasConflict() {
        LocalTime time9 = LocalTime.of(9, 0, 0);
        LocalTime time10 = LocalTime.of(10, 0, 0);

        CourseSection lecture1 = new CourseSection(time9, time10, Constants.MONDAY,
                (Constants.YEAR + " 2021"));
        lecture1.setName("MAT257");
        lecture1.setSectionCode("LEC 0101");
        String description = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session" + " at " + "SS100";
        lecture1.setDescription(description);

        CourseSection lecture2 = new CourseSection(time9, time10, Constants.MONDAY,
                (Constants.FALL + " 2021"));
        lecture2.setName("MAT157");
        lecture2.setSectionCode("LEC 0201");
        String description2 = "LEC 0202" + " of " + "Arts and Science" + " with " + "Professor.B" + " by " + "in-person"
                + " session" + " at " + "SS101";
        lecture2.setDescription(description2);

        TimeTableManager manager = new TimeTableManager();
        manager.addTimeTable(Constants.FALL + " 2021");
        manager.addTimeTable(Constants.WINTER + " 2021");
        manager.schedule(lecture1);
        assertFalse(manager.hasConflicts(lecture2));
    }

    @Test
    public void testGetCourses() {
        LocalTime time9 = LocalTime.of(9, 0, 0);
        LocalTime time10 = LocalTime.of(10, 0, 0);

        CourseSection lecture1 = new CourseSection(time9, time10, Constants.MONDAY,
                (Constants.FALL + " 2021"));
        lecture1.setName("MAT257");
        lecture1.setSectionCode("LEC 0101");
        CourseSection lecture2 = new CourseSection(time9, time10, Constants.TUESDAY,
                (Constants.FALL + " 2021"));
        lecture2.setName("MAT157");
        lecture2.setSectionCode("LEC 0201");

        ArrayList<CourseSection> courses = new ArrayList<>(Arrays.asList(lecture1, lecture2));

        TimeTableManager manager = new TimeTableManager();
        manager.schedule(lecture1);
        manager.schedule(lecture2);

        assertEquals(manager.getCourses(), courses);

    }
}
