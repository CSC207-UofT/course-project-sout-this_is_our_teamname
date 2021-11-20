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

        Task breakfast = new Task(Time8, Time9, "McDonald's", Constants.MONDAY, Constants.YEAR);
        Task shower = new Task(Time9, Time10, "home", Constants.MONDAY, Constants.YEAR);

        assertEquals(breakfast.getDescription(), "at McDonald's");
        assertNull(shower.getDescription());
    }

    @Test
    public void testToString() {
        LocalTime Time8 = LocalTime.of(8,0,0);
        LocalTime Time9 = LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);

        Task breakfast = new Task(Time8, Time9, "McDonald's", Constants.MONDAY, Constants.YEAR);
        Task shower = new Task(Time9, Time10,"home", Constants.MONDAY, Constants.YEAR);

        assertEquals(breakfast.toString(), "08:00 - 09:00: at McDonald's");
        assertEquals(shower.toString(), "09:00 - 10:00: N/A");
    }
}
