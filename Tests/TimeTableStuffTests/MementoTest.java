package TimeTableStuffTests;

import Helpers.ConflictException;
import Helpers.Constants;
import TimeTableContainers.Caretaker;
import TimeTableContainers.Originator;
import TimeTableContainers.TimeTable;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.Events;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MementoTest {

    @Test
    public void testMemento() throws ConflictException {
        //Create events
        LocalTime time6 =  LocalTime.of(6,0,0);
        LocalTime time9 =  LocalTime.of(9,0,0);
        LocalTime time10 = LocalTime.of(10, 0,0);

        CourseSection lecture = new CourseSection("MAT257", time9, time10, Constants.MONDAY, Constants.YEAR,
                "LEC 0101", false);
        String description = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session " + " at " + "SS100";
        lecture.setDescription(description);
        Activity activity = new Activity(time6,time9,Constants.MONDAY,Constants.FALL,"nap");

        //Create expected strings
        String expectedCourse = "9:00 MAT257: LEC 0101 of Arts and Science with Professor.A by Online session  at SS100";
        String expectedActivity1 = "6:00 nap";
        String expectedActivity2 = "7:00 nap";
        String expectedActivity3 = "8:00 nap";

        //Initialize variables
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        int saveFiles = 0;
        int currentManager = 0;

        //Create TimeTableManager
        TimeTableManager manager = new TimeTableManager();
        originator.setManager(manager);
        caretaker.addMemento(currentManager, originator.storeInMemento());
        saveFiles++;
        currentManager++;

        //Schedule lecture
        manager.schedule(lecture);
        originator.setManager(manager);
        caretaker.addMemento(currentManager, originator.storeInMemento());
        saveFiles++;
        currentManager++;

        //Schedule activity
        manager.schedule(activity);
        originator.setManager(manager);
        caretaker.addMemento(currentManager, originator.storeInMemento());
        saveFiles++;
        currentManager++;

        //undo
        currentManager--;
        HashMap<String, TimeTable> previousManager = originator.restoreFromMemento(caretaker.getMemento(currentManager-1));
        manager.setTimetables(previousManager);

        String actualUndo = manager.toString();
        boolean expectedUndo = actualUndo.contains(expectedCourse) && !actualUndo.contains(expectedActivity1)
                && !actualUndo.contains(expectedActivity2) && !actualUndo.contains(expectedActivity3);
        assertEquals(3, saveFiles);
        assertEquals(2, currentManager);
        assertTrue(expectedUndo);

        //redo
        currentManager++;
        HashMap<String, TimeTable> nextManager = originator.restoreFromMemento(caretaker.getMemento(currentManager-1));
        manager.setTimetables(nextManager);

        String actualRedo = manager.toString();
        boolean expectedRedo = actualRedo.contains(expectedCourse) && actualRedo.contains(expectedActivity1)
                && actualRedo.contains(expectedActivity2) && actualRedo.contains(expectedActivity3);
        assertEquals(3, saveFiles);
        assertEquals(3, currentManager);
        assertTrue(expectedRedo);
    }

}
