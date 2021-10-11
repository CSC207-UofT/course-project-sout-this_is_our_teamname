package DataCollection;

import TimeTableObjects.CourseStuff.Course;

import java.util.HashMap;

public abstract class DataGetter {
    public HashMap<String, Course> data;
    public String course_name;

    public DataGetter(String coursename){
        this.data = new HashMap<>();
        this.course_name = coursename;
    }

    public HashMap<String, Course> getData(){
        return this.data;
    }

    public abstract void configureData();
}
