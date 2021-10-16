package TimeTableObjects.CourseStuff;

import ConstantsAndExceptions.Constants;
import TimeTableStuff.TimeTable;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;

public class SectionTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void getCode() {
    }

    @Test
    public void getProfessor() {
    }

    @Test
    public void getFaculty() {
    }

    @Test
    public void getDeliveryMethod() {
    }

    @Test
    public void compareTo() {
        Time Time8 = new Time(8,0,0);
        Time Time9 = new Time(9,0,0);
        Time Time10 = new Time(10, 0,0);

        Time Time1030 = new Time (10,30,0);
        Time Time11 = new Time(11,0,0);

        Section lecture0 = new Section(Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person");
        Section lecture1 = new Section(Time9,Time10,"SS100", Constants.MONDAY,Constants.YEAR,
                "MAT257","Gauss","Arts and Science","In Person");
        Section lecture2 = new Section(Time9,Time11,"SS101",Constants.MONDAY,Constants.FALL,
                "MAT157", "Descartes","Arts and Science","Online");
        Section lecture3 = new Section(Time10,Time11,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online");
        Section lecture4 = new Section(Time1030,Time11,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online");
        Section lecture5 = new Section(Time8,Time10,"SS100",Constants.MONDAY,Constants.FALL,
                "MAT137", "Alphonso","Arts and Science","Online");
        TimeTable table = new TimeTable();
        assertEquals(lecture1.compareTo(lecture0), -1);
        assertEquals(lecture1.compareTo(lecture2), -1);
        assertEquals(lecture1.compareTo(lecture3), 1);
        assertEquals(lecture1.compareTo(lecture4), 1);
        assertEquals(lecture1.compareTo(lecture5), -1);
    }
}