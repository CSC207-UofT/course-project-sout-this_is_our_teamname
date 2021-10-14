package TimeTableStuff;

import TimeTableObjects.Course;
import TimeTableObjects.Parents.Entity;
import TimeTableObjects.Life;

/**
 * This module controls all the classes that can be mapped onto a timetable.
 *
 * Any developer who would like to create another object must add to this class
 *
 */
public class PlugInManager {
    public Entity Mapping(String key){
        if (key.equals("course")){
            return null; // TODO Returns a course somehow
        } else if (key.equals("life")){
            return null; // TODO Returns a life somehow
        } else {
            return null;
        }
    }
}
