package Interfaces;

import DataGetting.CSVScraper;
import DataGetting.WebScraper;

import Controllers.DatabaseController;
import Controllers.CommandFactory;

import TimeTableContainers.TimeTableManager;


import java.io.IOException;
import java.io.*;
import java.util.Scanner;



public class OperatorInterface {
    private final DatabaseController control;
    private String datasource;

    /**
     * Constructor.
     *
     * === Private Attributes ===
     * control: This is a DatabaseController
     */
    public OperatorInterface(DatabaseController controller) {
        this.control = controller;
        this.datasource = "CSVScraper";
    }


    /**
     * Set the DataGetter to the CommandFactory according to the operator's choice.
     *
     */
    public void SetDatasource(CommandFactory theFactory, String input){
        if (input.equals("CSVScraper")) {
            theFactory.setDataSource(new CSVScraper());
            theFactory.setManager(new TimeTableManager());
            this.control.setFactory(theFactory);
            this.datasource = input;

        } else if (input.equals("WebScraper")) {
            theFactory.setDataSource(new WebScraper());
            theFactory.setManager(new TimeTableManager());
            this.control.setFactory(theFactory);
            this.datasource = input;

        } else {
            System.out.print("Please type proper datasource.");
        }
    }

    /**
     * Get the chosen data source.
     *
     */
    public String getDatasource(){
        return this.datasource;
    }


    public void download(String source) throws IOException {
        FileWriter file = new  FileWriter("src/Interfaces/datasource.txt");
        file.write(source);
        file.flush();
        file.close();
    }


    /**
     * Runs the OperatorInterface
     */
    public void run() throws IOException {
        // As long as the program is running
        boolean running = true;

        while (running) {
            System.out.println("Which datasource do you want to set (CSVScraper/WebScraper): ");
            Scanner objectScanner = new Scanner(System.in);
            String type = objectScanner.nextLine();
            this.datasource = type;
            this.download(type);
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


