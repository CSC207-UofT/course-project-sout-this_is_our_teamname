package GlobalHelperMethods;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.assertEquals;

public class StringToTimeTest {
    String input;

    @Before
    public void TestSetup(){
        this.input = "9:00AM";
    }

    @Test (timeout = 10)
    public void Test(){
        Time expected = new Time(9, 0, 0);
        Time actual = StringToTime.makeTime(this.input);
        assertEquals(actual, expected);
    }
}
