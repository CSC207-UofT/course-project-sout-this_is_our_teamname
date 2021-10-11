package DataCollection;

import TimeTableObjects.CourseStuff.Course;

import java.util.HashMap;

public abstract class DataGetter {
    public HashMap<String, Course> data;

    public DataGetter(){
        this.data = new HashMap<>();
    }

    public HashMap<String, Course> getData(){
        return this.data;
    }

    public abstract void configureData();
}
