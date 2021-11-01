package EntitiesAndObjects.TimeTableObjects;

import GlobalHelpers.Constants;
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

        CourseSection lecture0 = new CourseSection(Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person", false);
        CourseSection lecture1 = new CourseSection(Time10,Time11,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);

        
        assertEquals(lecture0.toString(),
                "09:00 - 10:00: MAT257 of Arts and Science with Gauss by In Person at SS100");
        assertEquals(lecture1.toString(),
                "10:00 - 11:00: MAT137 of Arts and Science with Alphonso by Online at SS100");
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

        CourseSection lecture0 = new CourseSection(Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person", false);
        CourseSection lecture1 = new CourseSection(Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person", false);
        CourseSection lecture2 = new CourseSection(Time9,Time11,"SS101",Constants.MONDAY,Constants.FALL,
                "MAT157", "Descartes","Arts and Science","Online", false);
        CourseSection lecture3 = new CourseSection(Time10,Time11,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);
        CourseSection lecture4 = new CourseSection(Time1030,Time11,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);
        CourseSection lecture5 = new CourseSection(Time8,Time10,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);
        CourseSection lecture6 = new CourseSection(Time8,Time11,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online", false);

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
