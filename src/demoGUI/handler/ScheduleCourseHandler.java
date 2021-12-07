
package demoGUI.handler;

import DataGetting.DataGetter;
import DataGetting.WebScraper;
import Helpers.Constants;
import Helpers.InvalidInputException;
import InterfaceAdaptors.CommandFactory;
import InterfaceAdaptors.GUICommandFactory;
import TimeTableObjects.Course;
import demoGUI.userview.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ScheduleCourseHandler implements ActionListener {
    private final ScheduleCourseScreen scheduleCourseScreen;

    public ScheduleCourseHandler(ScheduleCourseScreen scheduleCourseScreen){
        this.scheduleCourseScreen = scheduleCourseScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Back".equals(text)){
            backUser();
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
                scheduleCourseScreen.setLeturebox(lecture);
                scheduleCourseScreen.setTutBox(tutorial);

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
                String lecture = scheduleCourseScreen.getlecBox();
                String tut = scheduleCourseScreen.gettutBox();
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

    private void backUser(){
        scheduleCourseScreen.dispose();
    }



}


