package EntitiesAndObjects;

import EntitiesAndObjects.TimeTableObjects.Section;
import EntitiesAndObjects.TimeTableObjects.Interfaces.Sliceable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;


public class Course implements Sliceable<Section> {
    private final String code;
    private final String professor;
    private final String faculty;
    private final String deliveryMethod;
    private final String term;
    private final HashMap<ArrayList<Object>, String> timeLocation;

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
     * @param term The term for this course
     */
    public Course(String section,
                  String professor,
                  String faculty,
                  String deliveryMethod,
                  HashMap<ArrayList<Object>, String> timeLocation,
                  String term) {
        this.code = section;
        this.professor = professor;
        this.faculty = faculty;
        this.deliveryMethod = deliveryMethod;
        this.timeLocation = timeLocation;
        this.term = term;
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
            CourseString.append(timeLocation.get(loc)).append(", ");
        }
        CourseString.append("with ").append(this.professor);
        return CourseString.toString();
    }

    /**
     * Get the term for this course
     *
     * @return the term of this course
     */
    public String getTerm() {
        return term;
    }

    //TODO place getDescription here after parameter change

     /** Split the course into section objects
     *
     * @return A list of section objects
     */
    @Override
    public ArrayList<Section> split(){
        ArrayList<Section> sectionList = new ArrayList<>();
        for (ArrayList<Object> time : this.timeLocation.keySet()) {
            Time start = ((Time) time.get(1));
            Time end = ((Time) time.get(2));
            String date = ((String) time.get(0));
            Section s = new Section(start, end, this.timeLocation.get(time),
                    date, this.term, this.code, this.professor,
                    this.faculty, this.deliveryMethod);
            sectionList.add(s);
        }
        return sectionList;
    }

    public static void main(String[] args) {
        ArrayList<Object> testDateTime1 = new ArrayList<>();
        testDateTime1.add("Friday");
        testDateTime1.add(new Time(9, 0, 0));
        testDateTime1.add(new Time(10, 0, 0));

        ArrayList<Object> testDateTime2 = new ArrayList<>();
        testDateTime2.add("Monday");
        testDateTime2.add(new Time(9, 0, 0));
        testDateTime2.add(new Time(10, 0, 0));

        HashMap<ArrayList<Object>, String> testDateTimeMap = new HashMap<>();
        testDateTimeMap.put(testDateTime1, "LM161");
        testDateTimeMap.put(testDateTime2, "LM161");

        Course A = new Course("LEC 0101", "Paul Gries", "A&S", "In-Person",
                testDateTimeMap, "Fall");
        System.out.println(A);
    }
}
