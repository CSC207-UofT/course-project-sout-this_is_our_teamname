
package demoGUI.handler;

import DataGetting.WebScraper;
import Helpers.Constants;
import Helpers.InvalidInputException;
import TimeTableObjects.Course;
import demoGUI.userview.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/** A ScheduleCourseHandler class that handles the events on the ScheduleCourseScreen.
 *
 * === Private Attributes ===
 * scheduleCourseScreen: the ScheduleCourseScreen object
 */
public class ScheduleCourseHandler implements ActionListener {
    private final ScheduleCourseScreen scheduleCourseScreen;


    /** The constructor method to connect the ScheduleCourseHandler to the ScheduleCourseScreen.
     *
     * @param scheduleCourseScreen the ScheduleCourseScreen object.
     */
    public ScheduleCourseHandler(ScheduleCourseScreen scheduleCourseScreen){
        this.scheduleCourseScreen = scheduleCourseScreen;
    }

    /** Handles the event on the ScheduleCourseScreen
     *
     * @param e the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        // if the user uses the "Back" button
        if ("Back".equals(text)){
            backUser();

        // if the user uses the "Solver" button
        } else if ("Solver".equals(text)){
            String name = scheduleCourseScreen.getCourseName();
            String term = scheduleCourseScreen.getTerm();
            String year = scheduleCourseScreen.getYear();
            String datasource = "WebScraper";

        // if the user uses the "Search" button
        } else if ("Search".equals(text)){
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
                // set the information of lectures and tutorials to corresponding JComboBox.
                scheduleCourseScreen.setLeturebox(lecture);
                scheduleCourseScreen.setTutBox(tutorial);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        // if the user uses "Schedule" button
        } else if ("Schedule".equals(text)){
            String term = scheduleCourseScreen.getTerm();
            String year = scheduleCourseScreen.getYear();
            String name = scheduleCourseScreen.getCourseName();
            WebScraper webscraper = new WebScraper();
            // schedule the selected lecture and tutorial section
            try {
                LinkedHashMap<String, Course> map = webscraper.retrieveData(name, term, year);
                String lecture = scheduleCourseScreen.getlecBox();
                String tut = scheduleCourseScreen.gettutBox();
                String lecCode = lecture.split(":")[0];
                String tutCode = tut.split(":")[0];
                Course lecSection = map.get(lecCode);
                Course tutSection = map.get(tutCode);
                scheduleCourseScreen.setLecture(lecSection);
                scheduleCourseScreen.setTutorial(tutSection);
                scheduleCourseScreen.getController().getFactory().setScreen(scheduleCourseScreen);
                scheduleCourseScreen.getController().runCommand(Constants.SCHEDULE_COURSE);
            } catch (FileNotFoundException | InvalidInputException ex) {
                ex.printStackTrace();
            }
            scheduleCourseScreen.getScreen().refreshTimetableTabs(
                    scheduleCourseScreen.getController().getFactory().getCourseManager());
            scheduleCourseScreen.dispose();
        }
    }

    /** Handles the click of the "Back" button
     *
     */
    private void backUser(){
        scheduleCourseScreen.dispose();
    }



}


