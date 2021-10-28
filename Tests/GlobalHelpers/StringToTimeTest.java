package GlobalHelpers;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class StringToTimeTest {
    String input;

    @Before
    public void TestSetup(){
        this.input = "9:00AM";
    }

    @Test (timeout = 10)
    public void Test(){
        LocalTime expected = LocalTime.of(9, 0, 0);
        LocalTime actual = StringToTime.makeTime(this.input);
        assertEquals(actual, expected);
    }
}
