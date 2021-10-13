package DataCollection;

import TimeTableObjects.CourseStuff.Course;

import java.util.HashMap;

public abstract class DataGetter {
    private HashMap<String, Course> data;

    public DataGetter(){
        this.data = new HashMap<>();
    }

    public void placeToData(String name, Course theCourse) {
        this.data.put(name, theCourse);
    }

    abstract void CalibrateData(String cName);

    public HashMap<String, Course> getData(String courseName){
        CalibrateData(courseName);
        return this.data;
    }
}
