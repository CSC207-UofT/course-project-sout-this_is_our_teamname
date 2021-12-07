package demoGUI.GUIcommands;

import Commands.Command;
import Commands.NeedsCoursesCommand;
import TimeTableContainers.TimeTableManager;
import TimeTableObjects.Course;
import TimeTableObjects.EventObjects.CourseSection;
import demoGUI.userview.ScheduleCourseScreen;

import java.util.ArrayList;

/**
 * A command to create a Course Object.
 *
 * === Private Attributes ===
 * dataSource: The source where the data is from.
 * manager: The manager that will eventually schedule the object
 */
public class GUIMakeCourseCommand extends NeedsCoursesCommand implements Command {

    private final ArrayList<Course> scheduledCourse;
    private final ScheduleCourseScreen scheduleCourseScreen;

    /**
     * A constructor to initialize what this command is connected to
     *
     *
     */
    public GUIMakeCourseCommand(ScheduleCourseScreen scheduleCourseScreen){
        ArrayList<Course> scheduledCourse = new ArrayList<>();
        scheduledCourse.add(scheduleCourseScreen.getTut());
        scheduledCourse.add(scheduleCourseScreen.getLec());

        this.scheduledCourse = scheduledCourse;
        this.scheduleCourseScreen = scheduleCourseScreen;
    }

    /**
     * Prompts the user to create a course object
     */
    @Override
    public void execute() {

        ArrayList<CourseSection> sections = new ArrayList<>();

        TimeTableManager manager = scheduleCourseScreen.getController().getFactory().getCourseManager();

        boolean hasConflict = hasConflicts(sections);


        if (!hasConflict){
            // Pass this to the TimeTableManager.
            for (CourseSection item : sections){
                manager.schedule(item);
            }
        } else {
            System.out.println("A Conflict has occurred. Please Try Again!");
        }
    }


    /**
     * Checks if there is a conflict in scheduling a course. Add courseSection
     * to `sections` of there are no conflicts
     * @param sections the list of courses
     * @return true iff there is a conflict. Returns false otherwise.
     */
    private boolean hasConflicts(ArrayList<CourseSection> sections) {
        for (Course course : this.scheduledCourse){
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
