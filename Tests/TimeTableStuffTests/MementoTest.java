package TimeTableStuffTests;

import EntitiesAndObjects.TimeTableObjects.CourseSection;
import GlobalHelpers.Constants;
import TimeTableStuff.Caretaker;
import TimeTableStuff.Originator;
import TimeTableStuff.TimeTableManager;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class MementoTest {

    @Test
    public void testMemento() {
        LocalTime Time9 =  LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);
        LocalTime Time11 = LocalTime.of(11,0,0);

        CourseSection lecture0 = new CourseSection(Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person", false);
        CourseSection lecture1 = new CourseSection(Time10,Time11,"SS100",Constants.THURSDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);

        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        int saveFiles = 0;
        int currentTimetable = 0;

        TimeTableManager manager = new TimeTableManager();
        originator.setCalender(manager);
        caretaker.addMemento(originator.storeInMemento());
        saveFiles++;
        currentTimetable++;

        //add lecture0 to timetable
        manager.schedule(lecture0);
        originator.setCalender(manager);
        caretaker.addMemento(originator.storeInMemento());
        saveFiles++;
        currentTimetable++;

        //add lecture1 to timetable
        manager.schedule(lecture1);
        originator.setCalender(manager);
        caretaker.addMemento(originator.storeInMemento());
        saveFiles++;
        currentTimetable++;

        //TODO: change expected to new format
        //undo
        currentTimetable--;
        manager = caretaker.getMemento(currentTimetable-1);
        String actualUndo = manager.toString();
        String expectedUndo =
                "{Winter={Monday=[09:00 - 10:00: MAT257 of Arts and Science with Gauss by In Person at SS100], " +
                         "Tuesday=[], " +
                         "Wednesday=[], " +
                         "Thursday=[], " +
                         "Friday=[], " +
                         "Saturday=[], " +
                         "Sunday=[]}, " +
                "Fall={Monday=[09:00 - 10:00: MAT257 of Arts and Science with Gauss by In Person at SS100], " +
                      "Tuesday=[], " +
                      "Wednesday=[], " +
                      "Thursday=[], " +
                      "Friday=[], " +
                      "Saturday=[], " +
                      "Sunday=[]}}";
//        assertEquals(expectedUndo, actualUndo);
        assertEquals(3, saveFiles);
        assertEquals(2, currentTimetable);

        //redo
        currentTimetable++;
        manager = caretaker.getMemento(currentTimetable-1);
        String actualRedo = manager.toString();
        String expectedRedo =
        "{Winter={Monday=[09:00 - 10:00: MAT257 of Arts and Science with Gauss by In Person at SS100], " +
                 "Tuesday=[], " +
                 "Wednesday=[], " +
                 "Thursday=[], " +
                 "Friday=[], " +
                 "Saturday=[], " +
                 "Sunday=[]}, " +
        "Fall={Monday=[09:00 - 10:00: MAT257 of Arts and Science with Gauss by In Person at SS100], " +
                "Tuesday=[], " +
                "Wednesday=[], " +
                "Thursday=[10:00 - 11:00: MAT137 of Arts and Science with Alphonso by Online at SS100], " +
                "Friday=[], " +
                "Saturday=[], " +
                "Sunday=[]}}";
//        assertEquals(expectedRedo, actualRedo);
        assertEquals(3, saveFiles);
        assertEquals(3, currentTimetable);
        }
}
