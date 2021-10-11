package DataCollection;


public class WebScraper extends DataGetter{

    public WebScraper(String course_name){
        super(course_name);
    }

    @Override
    public void configureData() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This is not Implemented in " +
                "Phase 0.");
    }
}
