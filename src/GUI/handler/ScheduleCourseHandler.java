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
     * TODO BREAK INTO HELPERS!!!
     * Handles the event on the ScheduleCourseScreen
     *
     * @param e the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        // =====================================================================
        String text = jButton.getText();

        // TODO Put this entire switch-case block into the command object.
        //  (Account for bugs)
        // if the user uses the "Back" button
        switch (text) {
            case "Back":
                scheduleCourseScreen.dispose();

                // if the user uses the "Solver" button
                scheduleCourseScreen.dispose();

                // if the user uses the "Search" button
                break;
                // TODO you have repeating code in the following two cases
            case "Search": {
                String term = scheduleCourseScreen.getTerm();
                String year = scheduleCourseScreen.getYear();
                String name = scheduleCourseScreen.getCourseName();

                // TODO Once in Command Object, switch with CourseGetter instead
                WebScraper webscraper = new WebScraper();
                try {
                    scheduleCourseScreen.clearBoxes();

                    // TODO Fix name map pls
                    LinkedHashMap<String, Course> map = webscraper.retrieveData(name, term, year);

                    // TODO Comment what you are trying to do
                    ArrayList<String> lecture = new ArrayList<>();
                    ArrayList<String> tutorial = new ArrayList<>();
                    ArrayList<String> practical = new ArrayList<>();
                    for (String x : map.keySet()) {
                        if (x.charAt(0) == 'L') {
                            lecture.add(x + ": " + map.get(x));
                        } else if (x.charAt(0) == 'T') {
                            tutorial.add(x + ": " + map.get(x));
                        } else if (x.charAt(0) == 'P') {
                            practical.add(x + ": " + map.get(x));
                        }
                    }

                    // set the information of lectures and tutorials to corresponding JComboBox.
                    scheduleCourseScreen.setLeturebox(lecture);
                    scheduleCourseScreen.setTutBox(tutorial);
                    scheduleCourseScreen.setpracBox(practical);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                // if the user uses "Schedule" button
                break;
            }
            case "Schedule": {
                String term = scheduleCourseScreen.getTerm();
                String year = scheduleCourseScreen.getYear();
                String name = scheduleCourseScreen.getCourseName();
                WebScraper webscraper = new WebScraper();
                // schedule the selected lecture and tutorial section
                try {

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
                } catch (FileNotFoundException | InvalidInputException ex) {
                    ex.printStackTrace();
                }

                scheduleCourseScreen.getScreen().refreshTimetableTabs(
                        // TODO WHY? It's like saying "hey! I'll go to Bahan from
                        //  Myhal by going through UTM!!!" SEE? IT DOESN"T MAKE
                        //  ANY SENSE!!! I would urge you to put the manager call
                        //  from GUICommandFactory and call it that way. Use
                        //  Aliasing!!!
                        scheduleCourseScreen.getController().getFactory().getCourseManager());
                scheduleCourseScreen.dispose();
                break;
            }
        }
    }
}


