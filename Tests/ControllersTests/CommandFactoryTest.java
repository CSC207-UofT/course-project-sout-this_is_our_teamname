package ControllersTests;

import Commands.Command;
import Commands.CreationCommands.GetAllTimeTablesCommand;
import Commands.CreationCommands.PrintHistoryCommand;
import Controllers.CommandFactory;
import Controllers.DatabaseController;
import DataGetting.CSVScraper;
import Helpers.InvalidInputException;
import TimeTableContainers.TimeTableManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the CommandFactory.
 *
 * These tests are not comprehensive, but it is challenging to test methods
 * that use `System.in`. That will be dealt with in Phase 2 (hopefully)
 */
public class CommandFactoryTest {
    CommandFactory factory;
    TimeTableManager manager;

    @Before
    public void Setup(){
        factory = new CommandFactory(new DatabaseController());
        manager = new TimeTableManager();
        factory.setManager(manager);
        factory.setDataSource(new CSVScraper());
    }

    @Test(timeout = 10)
    public void TestGetCommand() {
        try {
            Command cmd1 = factory.getCommand("Show TimeTables");
            assertTrue(cmd1 instanceof GetAllTimeTablesCommand);

            Command cmd2 = factory.getCommand("Get History");
            assertTrue(cmd2 instanceof PrintHistoryCommand);

            Command cmd3 = factory.getCommand("Log Out");
            assertNull(cmd3);

        } catch (InvalidInputException e){
            System.out.println("This should not happen, but if it does, here " +
                    "is an error message...");

            e.printStackTrace();
        }
    }
}
