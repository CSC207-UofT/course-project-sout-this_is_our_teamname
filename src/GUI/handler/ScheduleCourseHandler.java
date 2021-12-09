package GUI.handler;

// TODO THE FOLLOWING IMPORT STATEMENTS SHOULD NOT EXIST IN THIS FILE
import DataGetting.WebScraper;
import Helpers.InvalidInputException;
import java.io.FileNotFoundException;
// =============================================================================
import Helpers.Constants;
import InterfaceAdaptors.GUICommandFactory;
import TimeTableObjects.Course;

import GUI.userview.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    /**
     * Handles the event on the ScheduleCourseScreen
     *
     * @param e the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        // =====================================================================
        String text = jButton.getText();
        // if the user uses the "Back" button
        switch (text) {
            case "Back":
                scheduleCourseScreen.dispose();
                break;
            case "Search": {
                try {
                    searchCourse();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                break;
            }
            case "Schedule": {
                // schedule the selected lecture and tutorial section
                try {
                    scheduleCourse();
                } catch (FileNotFoundException | InvalidInputException ex) {
                    ex.printStackTrace();
                }

                scheduleCourseScreen.getScreen().refreshTimetableTabs(
                        scheduleCourseScreen.getController().getFactory().getCourseManager());
                scheduleCourseScreen.dispose();
                break;
            }
        }
    }

    /**
     * Use the choice in the JcomboBox to schedule the corresponding course.
     *
     */
    private void scheduleCourse() throws FileNotFoundException, InvalidInputException {
        String term = scheduleCourseScreen.getTerm();
        String year = scheduleCourseScreen.getYear();
        String name = scheduleCourseScreen.getCourseName();
        WebScraper webscraper = new WebScraper();
        LinkedHashMap<String, Course> map = webscraper.retrieveData(name, term, year);

        String lecture = scheduleCourseScreen.getlecBox();
        String tut = scheduleCourseScreen.gettutBox();
        String prac = scheduleCourseScreen.getpracBox();

        String lecCode = lecture.split(":")[0];
        String tutCode = tut.split(":")[0];
        String pracCode = prac.split(":")[0];

        Course lecSection = map.get(lecCode);
        Course tutSection = map.get(tutCode);
        Course pracSection = map.get(pracCode);

        scheduleCourseScreen.setLecture(lecSection);
        scheduleCourseScreen.setTutorial(tutSection);
        scheduleCourseScreen.setPractical(pracSection);

        GUICommandFactory factory = ((GUICommandFactory) scheduleCourseScreen.getController().getFactory());
        factory.setScreen(scheduleCourseScreen);
        scheduleCourseScreen.getController().runCommand(Constants.SCHEDULE_COURSE);
        scheduleCourseScreen.clearBoxes();
    }

    /**
     * The method to search course and output the result to the JComboBox.
     *
     */
    private void searchCourse() throws FileNotFoundException {
        scheduleCourseScreen.clearBoxes();
        String term = scheduleCourseScreen.getTerm();
        String year = scheduleCourseScreen.getYear();
        String name = scheduleCourseScreen.getCourseName();
        WebScraper webscraper = new WebScraper();

        LinkedHashMap<String, Course> courseMap = webscraper.retrieveData(name, term, year);

        // Append different sections to corresponding ArrayLists
        ArrayList<String> lecture = new ArrayList<>();
        ArrayList<String> tutorial = new ArrayList<>();
        ArrayList<String> practical = new ArrayList<>();
        for (String x : courseMap.keySet()) {
            if (x.charAt(0) == 'L') {
                lecture.add(x + ": " + courseMap.get(x));
            } else if (x.charAt(0) == 'T') {
                tutorial.add(x + ": " + courseMap.get(x));
            } else if (x.charAt(0) == 'P') {
                practical.add(x + ": " + courseMap.get(x));
            }
        }

        // set the information of lectures and tutorials to corresponding JComboBox.
        scheduleCourseScreen.setLeturebox(lecture);
        scheduleCourseScreen.setTutBox(tutorial);
        scheduleCourseScreen.setpracBox(practical);
    }

}


