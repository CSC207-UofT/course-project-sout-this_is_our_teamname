package TimeTableObjects.CourseStuff;

public class HCourse extends Course{
    private String term;

    public HCourse(String code, String professor, String faculty,
                   String deliveryMethod, Hashmap timeLocation, String term {
        super(code, professor, faculty, deliveryMethod, timeLocation);
        this.term = term;
    }
}
