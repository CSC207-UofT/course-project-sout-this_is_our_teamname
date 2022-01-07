package TimeTableObjects;

import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.CourseSection;

import java.time.LocalTime;

public class EventBuilder {
    private LocalTime startTime;
    private LocalTime endTime;
    private String date;
    private String term;
    private String name;
    private String description;

    public EventBuilder(){
        this.startTime = null;
        this.endTime = null;
        this.date = null;
        this.term = null;
        this.name = "";
        this.description = "";
    }

    public void calibrate(String name, String description,
                          LocalTime startTime, LocalTime endTime,
                          String theDate, String term){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = theDate;
        this.term = term;
        this.description = description;
    }

    public Activity getActivity(){
        assert isCalibrated();
        Activity activity = new Activity(startTime, endTime, date, term);
        activity.setDescription(description);
        activity.setName(name);
        return activity;
    }

    public CourseSection getCourseSection(String sectionName){
        assert isCalibrated();
        CourseSection section = new CourseSection(startTime, endTime, date, term);
        section.setName(name);
        section.setDescription(description);
        section.setSectionCode(sectionName);
        return section;
    }

    /**
     * Return if this is calibrated properly
     * @return true if this is calibrated properly
     */
    private boolean isCalibrated(){
        return this.name != null && this.startTime != null &&
                this.endTime != null && this.date != null && this.term != null
                && this.description != null;
    }
}
