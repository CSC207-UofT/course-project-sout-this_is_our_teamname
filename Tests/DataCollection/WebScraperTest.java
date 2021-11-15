package DataCollection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;


public class WebScraperTest {
    @Before
    public void setUp() {
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void invalidInputFilesNotFoundException() throws FileNotFoundException {
        WebScraper a = new WebScraper();

        exception.expect(FileNotFoundException.class);
        a.getData("", "", "");
    }

    @Test
    public void invalidInput2FilesNotFoundException() throws FileNotFoundException {
        WebScraper a = new WebScraper();

        exception.expect(FileNotFoundException.class);
        a.getData("CSC236", "S", "2222");
    }

}
