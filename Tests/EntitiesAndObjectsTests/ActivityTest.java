package EntitiesAndObjectsTests;

import TimeTableObjects.EventObjects.Activity;
import Helpers.Constants;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class ActivityTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testToString() {
        LocalTime Time8 = LocalTime.of(8,0,0);
        LocalTime Time9 = LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);

        Activity breakfast = new Activity(Time8, Time9, Constants.MONDAY, Constants.YEAR);
        breakfast.setDescription("buy coffee");
        Activity shower = new Activity(Time9, Time10, Constants.MONDAY, Constants.YEAR);
        shower.setDescription("remember to wash clothes");

        assertEquals("buy coffee", breakfast.toString());
        assertEquals("remember to wash clothes", shower.toString());
    }
}
