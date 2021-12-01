package TimeTableObjects;

import TimeTableObjects.EventObjects.CourseSection;
import TimeTableObjects.Interfaces.Splittable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

//TODO rename this
/**
 * This is a course section (LEC/TUT/PRA) chosen by the user
 *
 * === Private Attributes ===
 * sectionName is the section code of the course section
 * professor The professor teaching this course section
 * faculty The faculty this course belongs to
 * deliveryMethod The delivery method for this course section
 * timeLocation The time and corresponding location for this course
 * term The term for this course
 * wait_list Whether the course is waitlisted
 * courseName is the course code
 */
public class Course implements Splittable<CourseSection> {
    private final String sectionName;
    private final String professor;
    private final String faculty;
    private final String deliveryMethod;
    private final String term;
    private final HashMap<Object[], String> timeLocation;
    private final boolean wait_list;
    private final String courseName;

    // Constants for TimeLocation key
    final int THE_DATE = 0;
    final int THE_START = 1;
    final int THE_END = 2;

    /**
     * Construct a course with the given section, professor, faculty, delivery method,
     * time and location.
     * @param section The section code for this course
     * @param professor The professor teaching this course section
     * @param faculty The faculty this course belongs to
     * @param deliveryMethod The delivery method for this course section
     * @param timeLocation The time and corresponding location for this course
     *                     section **NEW** The hashmap will be in the form of
     *                     String[], String, where the string array will hold
     *                     the date and time information like the following:
     *                     {date, startTime, endTime}
     * @param term The term and year for this course e.g. Fall 2021
     * @param wait_list Whether the course is waitlisted
     */
    public Course(String CourseName,
                  String section,
                  String professor,
                  String faculty,
                  String deliveryMethod,
                  HashMap<Object[], String> timeLocation,
                  String term,
                  boolean wait_list
                  ) {
        this.courseName = CourseName;
        this.sectionName = section;
        this.professor = professor;
        this.faculty = faculty;
        this.deliveryMethod = deliveryMethod;
        this.timeLocation = timeLocation;
        this.term = term;
        this.wait_list = wait_list;
    }

    /** Split the course into section objects
     *
     * @return A list of section objects
     */
    @Override
    public ArrayList<CourseSection> split(){
        ArrayList<CourseSection> courseSectionList = new ArrayList<>();

        for (Object[] time : this.timeLocation.keySet()) {

            LocalTime start = ((LocalTime) time[THE_START]);
            LocalTime end = ((LocalTime) time[THE_END]);
            String date = ((String) time[THE_DATE]);
            String description =
                    sectionName + " of" + faculty + " with " + professor + " " +
                            "by " + deliveryMethod + " session" + " at "
                            + this.timeLocation.get(time);

            CourseSection section = new CourseSection(this.courseName, start, end, date, this.term,
                    this.sectionName, this.wait_list);
            section.setDescription(description);
            section.setName(courseName);

            courseSectionList.add(section);
        }
        return courseSectionList;
    }

    /**
     * Add to the TimeLocation List.
     *
     * @param dateTimeArray the time and date to add
     * @param location the location of the object
     */
    public void addToTimeLocation(Object[] dateTimeArray, String location){
        this.timeLocation.put(dateTimeArray, location);
    }

    /**
     * Get the Course code for this Course
     *
     * @return the course code
     */
    public String getCourseName() {
        return this.courseName;
    }

    /**
     * Get the section for this Course
     *
     * @return the section
     */
    public String getSectionName() {return this.sectionName; }

    /**
     * Get the times and corresponding locations for this Course
     *
     * @return the times and corresponding locations
     */
    public HashMap<Object[], String> getTimeLocation() {return this.timeLocation;}

    /**
     * Get the term for this course
     *
     * @return the term of this course
     */
    public String getTerm() {
        return term;
    }

    /**
     * Return the details of this Course
     *
     * @return the details of this course
     */
    public String toString() {
        StringBuilder CourseString = new StringBuilder();
        for (Object[] location : this.timeLocation.keySet()){
            CourseString.append(location[THE_DATE]).append(" ");
            CourseString.append(location[THE_START]).append(" - ");
            CourseString.append(location[THE_END]).append(" at ");
            CourseString.append(timeLocation.get(location)).append(", ");
        }
        CourseString.append("with ").append(this.professor);
        return CourseString.toString();
    }


    public static void main(String[] args) {
        Object[] testDateTime1 = {"Friday",
                LocalTime.of(9, 0, 0),
                LocalTime.of(10, 0, 0)};

        Object[] testDateTime2 = {"Monday",
                LocalTime.of(9, 0, 0),
                LocalTime.of(10, 0, 0)};

        HashMap<Object[], String> testDateTimeMap = new HashMap<>();
        testDateTimeMap.put(testDateTime1, "LM161");
        testDateTimeMap.put(testDateTime2, "LM161");

        Course A = new Course("CSC207H1", "LEC 0101", "Paul Gries", "A&S",
                "In-Person", testDateTimeMap, "Fall", false);
        System.out.println(A);
    }
}
