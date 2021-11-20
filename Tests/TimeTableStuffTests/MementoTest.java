package TimeTableStuffTests;

import Helpers.ConflictException;
import Helpers.Constants;
import TimeTableContainers.Caretaker;
import TimeTableContainers.Originator;
import TimeTableContainers.TimeTableManager;
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
        //Create courses
        LocalTime Time9 =  LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);
        LocalTime Time11 = LocalTime.of(11,0,0);
        CourseSection lecture0 = new CourseSection("MAT257Y1", Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person", false);
        CourseSection lecture1 = new CourseSection("MAT137H1", Time10,Time11,"SS100",Constants.THURSDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);

        //Initialize variables
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        int saveFiles = 0;
        int currentTimetable = 0;

        //Create TimeTableManager
        TimeTableManager manager = new TimeTableManager();
        originator.setCalender(manager);
        caretaker.addMemento(currentTimetable, originator.storeInMemento());
        saveFiles++;
        currentTimetable++;

        //add lecture0 to timetable
        manager.schedule(lecture0);
        originator.setCalender(manager);
        caretaker.addMemento(currentTimetable, originator.storeInMemento());
        saveFiles++;
        currentTimetable++;

        //add lecture1 to timetable
        manager.schedule(lecture1);
        originator.setCalender(manager);
        caretaker.addMemento(currentTimetable, originator.storeInMemento());
        saveFiles++;
        currentTimetable++;

        //undo
        currentTimetable--;
        HashMap<String, LinkedHashMap<String, Events[]>> table = originator.restoreFromMemento(caretaker.getMemento(currentTimetable-1));
        manager = new TimeTableManager(table);

        String actualUndo = manager.toString();
        String expectedCourse0 = "9:00 MAT257Y1: MAT257 of Arts and Science with Gauss by In Person at SS100";
        String expectedCourse1 = "10:00 MAT137H1: MAT137 of Arts and Science with Alphonso by Online at SS100";
        assertTrue(actualUndo.contains(expectedCourse0) &&  !actualUndo.contains(expectedCourse1));
        assertEquals(3, saveFiles);
        assertEquals(2, currentTimetable);

        //redo
        currentTimetable++;
        HashMap<String, LinkedHashMap<String, Events[]>> table2 = originator.restoreFromMemento(caretaker.getMemento(currentTimetable-1));
        manager = new TimeTableManager(table2);

        String actualRedo = manager.toString();
        assertTrue(actualRedo.contains(expectedCourse0) &&  actualRedo.contains(expectedCourse1));
        assertEquals(3, saveFiles);
        assertEquals(3, currentTimetable);
    }

}
