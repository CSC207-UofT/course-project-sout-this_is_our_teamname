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

        Task breakfast = new Task("", Constants.MONDAY, Constants.YEAR);
        breakfast.addToName("McDonald's");
        Task shower = new Task("", Constants.MONDAY, Constants.YEAR);
        assertEquals("McDonald's", breakfast.getName());
        assertEquals("", shower.getName());
    }

    @Test
    public void testToString() {
        LocalTime Time8 = LocalTime.of(8,0,0);
        LocalTime Time9 = LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);

        Task breakfast = new Task("", Constants.MONDAY, Constants.YEAR);
        breakfast.addToName("McDonald's");
        Task shower = new Task("", Constants.MONDAY, Constants.YEAR);
        shower.addToName("home");
        assertEquals("McDonald's", breakfast.toString());
        assertEquals("home", shower.toString());
    }
}
