
package demoGUI.handler;

import DataGetting.WebScraper;
import Helpers.Constants;
import Helpers.InvalidInputException;
import InterfaceAdaptors.GUICommandFactory;
import TimeTableObjects.Course;
import demoGUI.userview.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * Handler to handle actions performed by the user when scheduling course
 *
 * === Attributes ===
 * scheduleCourseScreen: The window viewed by the user when scheduling course
 */
public class ScheduleCourseHandler implements ActionListener {
    private final ScheduleCourseScreen scheduleCourseScreen;

    /**
     * Constructor to set the handler.
     * @param scheduleCourseScreen is the window viewed by the user when scheduling course
     */
    public ScheduleCourseHandler(ScheduleCourseScreen scheduleCourseScreen){
        this.scheduleCourseScreen = scheduleCourseScreen;
    }

    /**
     * Handles the action user performed on the screen
     * @param e the action user performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            scheduleCourseScreen.dispose();
        } else if ("Solver".equals(text)){
            String name = scheduleCourseScreen.getCourseName();
            String term = scheduleCourseScreen.getTerm();
            String year = scheduleCourseScreen.getYear();
            String datasource = "WebScraper";
        } else if ("Search".equals(text)){

            // TODO input checker
            String term = scheduleCourseScreen.getTerm();
            String year = scheduleCourseScreen.getYear();
            String name = scheduleCourseScreen.getCourseName();
            WebScraper webscraper = new WebScraper();
            try {
                scheduleCourseScreen.clearBoxes();

                LinkedHashMap<String, Course> map = webscraper.retrieveData(name, term, year);

                ArrayList<String> lecture = new ArrayList<>();
                ArrayList<String> tutorial = new ArrayList<>();
                for (String x: map.keySet()) {
                    if (x.charAt(0) == 'L') {
                        lecture.add(x + ": " + map.get(x));
                    } else {
                        tutorial.add(x + ": " + map.get(x));
                    }
                }
                scheduleCourseScreen.addLecture(lecture);
                scheduleCourseScreen.addTutorial(tutorial);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }


        } else if ("Schedule".equals(text)){
            String term = scheduleCourseScreen.getTerm();
            String year = scheduleCourseScreen.getYear();
            String name = scheduleCourseScreen.getCourseName();
            WebScraper webscraper = new WebScraper();
            try {

                LinkedHashMap<String, Course> map = webscraper.retrieveData(name, term, year);
                String lecture = scheduleCourseScreen.getLecBox();
                String tut = scheduleCourseScreen.getTutBox();
                String lecCode = lecture.split(":")[0];
                String tutCode = tut.split(":")[0];
                Course lecSection = map.get(lecCode);
                Course tutSection = map.get(tutCode);
                scheduleCourseScreen.setLecture(lecSection);
                scheduleCourseScreen.setTutorial(tutSection);
                GUICommandFactory factory = ((GUICommandFactory)scheduleCourseScreen.getController().getFactory());
                factory.setScreen(scheduleCourseScreen);
                scheduleCourseScreen.getController().runCommand(Constants.SCHEDULE_COURSE);

            } catch (FileNotFoundException | InvalidInputException ex) {
                ex.printStackTrace();
            }
            scheduleCourseScreen.getScreen().refreshTimetableTabs(
                    scheduleCourseScreen.getController().getFactory().getCourseManager());
            //opens conflict dialog when there is a conflict in scheduling
            if (scheduleCourseScreen.getConflict()){
                ConflictDialog.main(new String[]{""});
            }
            scheduleCourseScreen.dispose();
        }


    }
}


