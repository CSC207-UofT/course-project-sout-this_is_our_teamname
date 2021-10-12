package DataCollection;

import TimeTableObjects.CourseStuff.Course;

import java.util.HashMap;

import org.junit.*;

import static org.junit.Assert.*;

public class CSVScraperTest {
    HashMap<String, Course> receivedData;

    @Before
    public void setUp(){
        CSVScraper a = new CSVScraper();
        receivedData = a.getData("CSC207H1");
    }

    @Test(timeout = 10)
    public void theTest(){
        int expectedLen = 13;
        assertEquals(receivedData.size(), expectedLen);
    }
}
