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
                "LEC 0101", false);
        String description0 = "LEC 0100" + " of " + "Arts and Science" + " with " + "Professor.0" + " by " + "Online"
                + " session " + " at " + "SS100";
        lecture0.setDescription(description0);

        CourseSection lecture1 = new CourseSection("MAT137", Time10,Time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101", false);
        String description1 = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session " + " at " + "SS101";
        lecture1.setDescription(description1);


        assertEquals(lecture0.toString(),
                "MAT257 of Arts and Science with Gauss by In Person at SS100");
        assertEquals(lecture1.toString(),
                "MAT137 of Arts and Science with Alphonso by Online at SS100");
    }

    @Test
    public void getCode() {
    }

    @Test
    public void compareTo() {
        LocalTime Time8 = LocalTime.of(8,0,0);
        LocalTime Time9 = LocalTime.of(9,0,0);
        LocalTime Time10 = LocalTime.of(10, 0,0);
        LocalTime Time1030 = LocalTime.of(10,30,0);
        LocalTime Time11 = LocalTime.of(11,0,0);

        CourseSection lecture0 = new CourseSection("MAT257", Time9,Time10,Constants.MONDAY,Constants.YEAR,
                "LEC 0101", false);
        String description0 = "LEC 0100" + " of " + "Arts and Science" + " with " + "Professor.0" + " by " + "Online"
                + " session " + " at " + "SS100";
        lecture0.setDescription(description0);

        CourseSection lecture1 = new CourseSection("MAT257", Time9,Time10, Constants.MONDAY,Constants.YEAR,
                "LEC 0101", false);
        String description1 = "LEC 0101" + " of " + "Arts and Science" + " with " + "Professor.A" + " by " + "Online"
                + " session " + " at " + "SS101";
        lecture1.setDescription(description1);

        CourseSection lecture2 = new CourseSection("MAT157", Time9,Time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101", false);
        String description2 = "LEC 0102" + " of " + "Arts and Science" + " with " + "Professor.B" + " by " + "in person"
                + " session " + " at " + "SS102";
        lecture2.setDescription(description2);

        CourseSection lecture3 = new CourseSection("MAT137", Time10,Time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101", false);
        String description3 = "LEC 0103" + " of " + "Arts and Science" + " with " + "Professor.C" + " by " + "in person"
                + " session " + " at " + "SS103";
        lecture3.setDescription(description3);

        CourseSection lecture4 = new CourseSection("MAT137", Time1030,Time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101", false);
        String description4 = "LEC 0104" + " of " + "Arts and Science" + " with " + "Professor.D" + " by " + "in person"
                + " session " + " at " + "SS104";
        lecture4.setDescription(description4);

        CourseSection lecture5 = new CourseSection("MAT137", Time8,Time10,Constants.MONDAY,Constants.FALL,
                "LEC 0101", false);
        String description5 = "LEC 0105" + " of " + "Arts and Science" + " with " + "Professor.E" + " by " + "in person"
                + " session " + " at " + "SS105";
        lecture5.setDescription(description5);

        CourseSection lecture6 = new CourseSection("MAT137", Time8,Time11,Constants.MONDAY,Constants.FALL,
                "LEC 0101", false);
        String description6 = "LEC 0106" + " of " + "Arts and Science" + " with " + "Professor.E" + " by " + "in person"
                + " session " + " at " + "SS106";
        lecture6.setDescription(description6);

        assertEquals(lecture1.compareTo(lecture0), -1);
        assertEquals(lecture1.compareTo(lecture2), -1);
        assertEquals(lecture1.compareTo(lecture3), 1);
        assertEquals(lecture1.compareTo(lecture4), 1);
        assertEquals(lecture1.compareTo(lecture5), -1);
        assertEquals(lecture2.compareTo(lecture1), -1);
        assertEquals(lecture2.compareTo(lecture3), -1);
        assertEquals(lecture6.compareTo(lecture2), -1);
    }
}
