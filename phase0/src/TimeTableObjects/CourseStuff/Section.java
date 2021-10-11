package TimeTableObjects.CourseStuff;

public class Section extends TimeTableObject implements Comparable<Section>{
    private String time;
    private String location;
    private String code;
    private String professor;
    private String faculty;
    private String deliveryMethod;

    /**
     * Construct a TimeTable section for the given time, location, section, professor,
     * faculty and delivery method
     *
     * @param time The time of this section.
     * @param location The location of this section
     * @param code The code for this course
     * @param professor The professor teaching this course section
     * @param faculty The faculty this course belongs to
     * @param deliveryMethod The delivery method for this course section
     */
    public Section(String time, String location, String code, String professor,
                   String faculty, String deliveryMethod) {
        super(time, location);
        this.code = code;
        this.professor = professor;
        this.faculty = faculty;
        this.deliveryMethod = deliveryMethod;
    }

    /**
     * Get the time for this course section.
     *
     * @return the time this section is at
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Get the location for this course section.
     *
     * @return the location this section is at
     */
    public String getLocation() {
        return this.location;
    }

    @Override
    public int compareTo(Section anotherSection)
}
