package GUI.GUIcommands;

import Commands.Command;
import Commands.CreationCommands.MakeCourseCommand;
import Commands.NeedsCoursesCommand;
import DataGetting.CourseGetter;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import GUI.userview.ConflictDialog;
import GUI.userview.ScheduleCourseScreen;

import java.util.ArrayList;

/**
 * A command to create a Course Object.
 *
 * === Private Attributes ===
 * scheduledCourse: The courses to be scheduled to the manager.
 * scheduleCourseScreen: The window viewed by the user when scheduling a course.
 */
public class GUIMakeCourseCommand implements Command {
    private final TimeTableManager manager;
    private final ArrayList<Course> scheduledCourse;
    private final ScheduleCourseScreen scheduleCourseScreen;
    private final CourseGetter source;

    /**
     * A constructor to initialize what this command is connected to
     * @param scheduleCourseScreen is the window viewed by the user when scheduling a course
     */
    public GUIMakeCourseCommand(ScheduleCourseScreen scheduleCourseScreen,
                                TimeTableManager sendTo, CourseGetter dataSource){
        this.manager = sendTo;
        this.source = dataSource;
        ArrayList<Course> scheduledCourse = new ArrayList<>();
        scheduledCourse.add(scheduleCourseScreen.getTut());
        scheduledCourse.add(scheduleCourseScreen.getLec());
        scheduledCourse.add(scheduleCourseScreen.getPrac());

        this.scheduledCourse = scheduledCourse;
        this.scheduleCourseScreen = scheduleCourseScreen;
    }

    /**
     * Prompts the user to create a course object
     */
    @Override
    public void execute() {
        ArrayList<CourseSection> sections = new ArrayList<>();
        boolean hasConflict = hasConflicts(sections);

        if (!hasConflict){
            // Pass this to the TimeTableManager.
            for (CourseSection item : sections){
                manager.schedule(item);
            }
        } else {
            ConflictDialog cd = new ConflictDialog();
            cd.run();
        }
    }

    @Override
    public String toString() {
        return "Scheduled Course";
    }


    /**
     * Checks if there is a conflict in scheduling a course. Add courseSection
     * to `sections` of there are no conflicts
     * @param sections the list of courses
     * @return true iff there is a conflict. Returns false otherwise.
     */
    private boolean hasConflicts(ArrayList<CourseSection> sections) {
        for (Course course : this.scheduledCourse){
            if (course != null) {
                ArrayList<CourseSection> conflictCheckSections = course.split();
                TimeTableManager manager = scheduleCourseScreen.getController().getFactory().getCourseManager();
                for (CourseSection sectionOfCourse : conflictCheckSections){
                    if (!manager.hasConflicts(sectionOfCourse)){
                        sections.add(sectionOfCourse);
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // ============================ Predicates =================================
    /**
     * Return if there has already been a course been scheduled
     * @return true iff there has been a course scheduled.
     */
    protected boolean hasScheduled(){
        return scheduledCourse.size() != 0;
    }
}
