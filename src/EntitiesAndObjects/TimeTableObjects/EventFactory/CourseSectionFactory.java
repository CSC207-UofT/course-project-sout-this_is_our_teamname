package EntitiesAndObjects.TimeTableObjects.EventFactory;

import EntitiesAndObjects.Course;
import EntitiesAndObjects.TimeTableObjects.Activity;
import EntitiesAndObjects.TimeTableObjects.CourseSection;
import EntitiesAndObjects.TimeTableObjects.Events;

import java.time.LocalTime;
import java.util.ArrayList;

public class CourseSectionFactory extends EventFactory{
    @Override
    public Events createEvent() {
        return null;
    }

    //Overloads createEvent
    //TODO check after parameter changes
    /** Creates one or more CourseSection
     * @param course is a Course object containing one or more time intervals
     * @return an ArrayList of CourseSections
     */
    public ArrayList<CourseSection> createEvent(Course course) {
        return course.split();
        //TODO add description?
        }
}
