package EntitiesAndObjects;

import EntitiesAndObjects.TimeTableObjects.CourseSection;
import EntitiesAndObjects.TimeTableObjects.Interfaces.Sliceable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Course class stores all relevant information, like professor, location, delivery method,
 * etc.
 */
public class Course implements Sliceable<CourseSection> {
    private final String description;
    private final String term;
    private final String code;
    private final HashMap<ArrayList<Object>, String> timeLocation;

    // Constants for TimeLocation key
    final int THE_DATE = 0;
    final int THE_START = 1;
    final int THE_END = 2;

    /**
     * Construct a course with the given section, professor, faculty, delivery method,
     * time and location.
     * @param description The description for this course
     * @param term The term for this course
     * @param code The code for this course
     * @param timeLocation The time and corresponding location for this course
     *                     section **NEW** The hashmap will be in the form of
     *                     String[], String, where the string array will hold
     *                     the date and time information like the following:
     *                     {date, startTime, endTime}
     */
    public Course(String description,
                  String term,
                  String code,
                  HashMap<ArrayList<Object>, String> timeLocation) {
        this.term = term;
        this.description = description;
        this.code = code;
        this.timeLocation = timeLocation;
    }

    /**
     * Get the Course code for this Course
     *
     * @return the course code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Get the times and corresponding locations for this Course
     *
     * @return the times and corresponding locations
     */
    public HashMap<ArrayList<Object>, String> getTimeLocation() {
        return this.timeLocation;
    }

    /**
     * Return the details of this Course
     *
     * @return the details of this course
     */
    public String toString() {
        StringBuilder CourseString = new StringBuilder();
        for (ArrayList<Object> loc : this.timeLocation.keySet()){
            CourseString.append(loc.get(THE_DATE)).append(" ");
            CourseString.append(loc.get(THE_START)).append(" - ");
            CourseString.append(loc.get(THE_END)).append(" at ");
            CourseString.append(timeLocation.get(loc)).append(". ");
        }
        CourseString.append("Details: ").append(this.description);
        return CourseString.toString();
    }

    /**
     * Get the description for this course
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the term for this course
     *
     * @return the term of this course
     */
    public String getTerm() {
        return term;
    }


     /** Split the course into section objects
     *
     * @return A list of section objects
     */
    @Override
    public ArrayList<CourseSection> split(){
        ArrayList<CourseSection> courseSectionList = new ArrayList<>();

        for (ArrayList<Object> time : this.timeLocation.keySet()) {
            LocalTime start = ((LocalTime) time.get(1));
            LocalTime end = ((LocalTime) time.get(2));
            String date = ((String) time.get(0));
            CourseSection s = new CourseSection(start, end, this.description,
                    date, this.term, this.code);
            courseSectionList.add(s);
        }
        return courseSectionList;
    }

    public static void main(String[] args) {
        ArrayList<Object> testDateTime1 = new ArrayList<>();
        testDateTime1.add("Friday");
        testDateTime1.add(LocalTime.of(9, 0, 0));
        testDateTime1.add(LocalTime.of(10, 0, 0));

        ArrayList<Object> testDateTime2 = new ArrayList<>();
        testDateTime2.add("Monday");
        testDateTime2.add(LocalTime.of(9, 0, 0));
        testDateTime2.add(LocalTime.of(10, 0, 0));

        HashMap<ArrayList<Object>, String> testDateTimeMap = new HashMap<>();
        testDateTimeMap.put(testDateTime1, "LM161");
        testDateTimeMap.put(testDateTime2, "LM161");

        Course A = new Course("Professor: Paul Gries, Faculty: A&S, " +
                "Delivery Method: In-Person", "Fall", "LEC 0101",
                testDateTimeMap);
        System.out.println(A);
    }
}
