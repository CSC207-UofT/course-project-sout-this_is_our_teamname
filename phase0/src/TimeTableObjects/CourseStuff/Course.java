package TimeTableObjects.CourseStuff;
import CourseStuff.Sliceable;
import TimeTableObjects.CourseStuff.Section;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.sql.Time;


public class Course implements Sliceable<Section>{
    private final String code;
    private final String professor;
    private final String faculty;
    private final String deliveryMethod;
    private final String term;
    private final HashMap<ArrayList<Object>, String> timeLocation;

    /**
     * Construct a course with the given section, professor, faculty, delivery method,
     * time and location.
     *
     * @param section The section code for this course
     * @param professor The professor teaching this course section
     * @param faculty The faculty this course belongs to
     * @param deliveryMethod The delivery method for this course section
     * @param timeLocation The time and corresponding location for this course
     *                     section **NEW** The hashmap will be in the form of
     *                     String[], String, where the string array will hold
     *                     the date and time information like the following:
     *                     {date, startTime, endTime}
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
     * Get the Professor teaching this Course
     *
     * @return the name of the Professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * Get the Faculty this course belongs to
     *
     * @return the Faculty this course belongs to
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Get the delivery method this course is delivered in
     *
     * @return the delivery method for this course
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
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
//        StringBuilder details =
//                new StringBuilder(this.code + " with " + this.professor +
//                " in the Faculty of " + this.faculty + "\n");
//        details.append("The delivery method is ").append(this.deliveryMethod).append("\n").append("This course meets at ");
//        for (String[] time : this.timeLocation.keySet()) {
//            String timeString = time[0] + ", " + time[1] + " - " + time[2];
//            details.append(timeString).append(" at ").append(this.timeLocation.get(time)).append(",");
//        }
//
//        details.deleteCharAt(details.length() - 1);
//        return details.toString();
        // TODO : fix this
        return "";
    }

    public String getTerm() {
        return term;
    }

     /** Split the course into section objects
     *
     * @return A list of section objects
     */
    @Override
    public ArrayList<Section> split(){
        ArrayList<Section> sectionList = new ArrayList<Section>();
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

}
