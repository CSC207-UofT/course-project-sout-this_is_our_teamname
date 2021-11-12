package Interfaces;

import DataCollection.CSVScraper;
import DataCollection.WebScraper;

import DatabaseController.DatabaseController;
import DatabaseController.CommandFactory;

import TimeTableStuff.TimeTableManager;


import java.util.Scanner;



public class OperatorInterface {
    private final DatabaseController control;

    /**
     * Constructor.
     *
     * === Private Attributes ===
     * control: This is a DatabaseController
     */
    public OperatorInterface(DatabaseController controller) {
        this.control = controller;
    }


    /**
     * Set the DataGetter to the CommandFactory according to the operator's choice.
     *
     */
    private void SetDatasource(CommandFactory theFactory, String input){
        if (input.equals("CSVScraper")) {
            theFactory.setDataSource(new CSVScraper());
            theFactory.setManager(new TimeTableManager());
            this.control.setFactory(theFactory);

        } else if (input.equals("WebScraper")) {
            theFactory.setDataSource(new WebScraper());
            theFactory.setManager(new TimeTableManager());
            this.control.setFactory(theFactory);
        } else {
            System.out.print("Please type proper datasource.");
        }
    }

    /**
     * Runs the OperatorInterface
     */
    public void run() {
        // As long as the program is running
        boolean running = true;

        while (running) {
            System.out.println("Which datasource do you want to set (CSVScraper/WebScraper): ");
            Scanner objectScanner = new Scanner(System.in);
            String type = objectScanner.nextLine();
            CommandFactory theFactory = new CommandFactory(control);
            this.SetDatasource(theFactory, type);
            Scanner continueQuestion = new Scanner(System.in);
            System.out.println("Do you want to set a new datasource? " +
                    "(true/false):");
            String continueResponse = continueQuestion.nextLine();

            // Checks if the user wants to set a new datasource.
            if (continueResponse.equals("false")) {
                running = false;


            }

        }
    }
}


