package InterfaceAdaprotsTests;

import Commands.Command;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.DatabaseController;
import DataGetting.CSVScraper;
import Helpers.InvalidInputException;
import TimeTableContainers.TimeTableManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Test the DatabaseController.
 *
 * These tests are not comprehensive, but it is challenging to test methods
 * that use `System.in`. That will be dealt with in Phase 2 (hopefully)
 */
public class DatabaseControllerTest {
    DatabaseController controller;

    @Before
    public void TestSetup(){
        controller = new DatabaseController();
        CommandFactory theFactory = new CommandFactory(controller);
        theFactory.setDataSource(new CSVScraper());
        controller.setFactory(theFactory);
    }

    @Test(timeout = 10)
    public void TestGetCommandHistory(){
        try {
            controller.runCommand("Show TimeTables");
            Stack<Command> actualStack = controller.getCommandHistory();

            assertEquals(1, actualStack.size());
        } catch (InvalidInputException e){
            System.out.println("This should not happen, but if it does, here " +
                    "is an error message...");

            e.printStackTrace();
        }
    }
}
