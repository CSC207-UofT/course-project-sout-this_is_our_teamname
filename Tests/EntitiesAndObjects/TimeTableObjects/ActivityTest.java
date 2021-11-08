package EntitiesAndObjects.TimeTableObjects;

import GlobalHelpers.Constants;
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

        Activity breakfast = new Activity(Time8, Time9, "McDonald's", Constants.MONDAY, Constants.YEAR,
                "buy coffee");
        Activity shower = new Activity(Time9, Time10, Constants.MONDAY, Constants.YEAR,
                "remember to wash clothes");

        assertEquals(breakfast.toString(), "08:00 - 09:00: buy coffee at McDonald's");
        assertEquals(shower.toString(), "09:00 - 10:00: remember to wash clothes");
    }
}
