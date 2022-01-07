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

        CourseSection lecture0 = new CourseSection(Time9,Time10, Constants.MONDAY,Constants.YEAR);
        lecture0.setName("MAT257");
        lecture0.setSectionCode("LEC 0101");
        String description0 = "LEC 0100" + " of " + "Arts and Science" + " with " + "Professor.0" + " by " + "Online"
                + " session" + " at " + "SS100";
        lecture0.setDescription(description0);

        CourseSection lecture1 = new CourseSection(Time10,Time11,Constants.MONDAY,Constants.FALL);
        lecture1.setName("MAT137");
        lecture1.setSectionCode("LEC 0101");
        String description1 = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session" + " at " + "SS101";
        lecture1.setDescription(description1);


        assertEquals("MAT257: LEC 0100 of Arts and Science with Professor.0 by Online session at SS100",
                lecture0.toString());
        assertEquals("MAT137: LEC 0101 of Arts and Science with Professor.A by Online session at SS101", lecture1.toString());
    }
}
