package demoGUI.handler;


import DataGetting.CSVScraper;
import DataGetting.WebScraper;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.DatabaseController;
import Interfaces.InterfaceFacade;
import Interfaces.OperatorInterface;
import TimeTableContainers.TimeTableManager;
import demoGUI.userview.HomeScreen;
import demoGUI.userview.OperatorScreen;
import demoGUI.userview.UserScreen;
import demoGUI.handler.HomeScreenHandler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Handle's schedule course button when clicked
 */


public class OperatorScreenHandler implements ActionListener {
    private OperatorScreen operatorScreen;

    public OperatorScreenHandler(OperatorScreen operatorScreen){
        this.operatorScreen = operatorScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backHome();
        } else if ("Taiga".equals(text)){
            System.out.println("Taiga!");
            JOptionPane.showMessageDialog(operatorScreen,"You touched Taiga's head Aww");
        } else if ("Apply".equals(text)) {
            // Get user input
//            String type = operatorScreen.getDatasource();
//            OperatorInterface operator = operatorScreen.getOperator();
//            CommandFactory factory = new CommandFactory(operator.getControl());
//
//            try {
//                operator.SetDatasource(factory, type);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//            operatorScreen.setOperator(operator);
//
//            JOptionPane.showMessageDialog(operatorScreen,"Successfully applied " + type);
        }
    }

    private void backHome(){
        new HomeScreen();
        operatorScreen.dispose();
    }

    private void SetDatasource(CommandFactory theFactory, DatabaseController controller, String input) throws IOException {
        // Set the datasource of theFactory to be CSVScraper if operator chooses it.

        if (input.equals("CSVScraper")) {
            theFactory.setDataSource(new CSVScraper());
            controller.setFactory(theFactory);

            // Set the datasource of theFactory to be WebScraper if operator chooses it.
        } else if (input.equals("WebScraper")) {
            theFactory.setDataSource(new WebScraper());
            //theFactory.setManager(new TimeTableManager());
            controller.setFactory(theFactory);
        }
    }
}