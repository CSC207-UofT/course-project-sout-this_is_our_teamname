package EntitiesAndObjectsTests;

import TimeTableObjects.EventObjects.CourseSection;
import Helpers.Constants;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class SectionTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testToString() {
        LocalTime Time9 = LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);
        LocalTime Time11 = LocalTime.of(11,0,0);

        CourseSection lecture0 = new CourseSection("MAT257", Time9,Time10, Constants.MONDAY,Constants.YEAR,
                "LEC 0101");
        String description0 = "LEC 0100" + " of " + "Arts and Science" + " with " + "Professor.0" + " by " + "Online"
                + " session " + " at " + "SS100";
        lecture0.setName(description0);

        CourseSection lecture1 = new CourseSection("MAT137", Time10,Time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101");
        String description1 = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session " + " at " + "SS101";
        lecture1.setName(description1);


        assertEquals("MAT257 of Arts and Science with Gauss by In Person at SS100",
                lecture0.toString());
        assertEquals("MAT137 of Arts and Science with Alphonso by Online at SS100", lecture1.toString());
    }

    @Test
    public void getCode() {
    }
}
