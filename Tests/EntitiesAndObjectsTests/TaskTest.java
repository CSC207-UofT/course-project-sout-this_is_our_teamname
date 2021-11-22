package EntitiesAndObjectsTests;

import TimeTableObjects.EventObjects.Task;
import Helpers.Constants;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class TaskTest {

    @Before
    public void setUp() {
    }

    @Test
    public void getDescription() {
        LocalTime Time8 = LocalTime.of(8,0,0);
        LocalTime Time9 = LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);

        Task breakfast = new Task(Time8, Time9, Constants.MONDAY, Constants.YEAR);
        breakfast.addToName("McDonald's");
        Task shower = new Task(Time9, Time10, Constants.MONDAY, Constants.YEAR);
        shower.addToName("home");
        assertEquals(breakfast.getName(), "at McDonald's");
        assertNull(shower.getName());
    }

    @Test
    public void testToString() {
        LocalTime Time8 = LocalTime.of(8,0,0);
        LocalTime Time9 = LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);

        Task breakfast = new Task(Time8, Time9, Constants.MONDAY, Constants.YEAR);
        breakfast.addToName("McDonald's");
        Task shower = new Task(Time9, Time10, Constants.MONDAY, Constants.YEAR);
        shower.addToName("home");
        assertEquals(breakfast.toString(), "08:00 - 09:00: at McDonald's");
        assertEquals(shower.toString(), "09:00 - 10:00: N/A");
    }
}
