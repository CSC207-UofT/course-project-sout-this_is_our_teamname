package DataCollection;

import EntitiesAndObjects.Course;
import GlobalHelpers.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

/**
 * A WebScraper class. It is a DataGetter class that gets the data from a the course finder
 * website.
 *
 */
public class WebScraper extends DataGetter{

    /**
     * Constructor of the CSVScraper. Reads and filters the data correctly
     * into the data hashmap.
     *
     * @param courseName the name of the course
     * @param theTerm the term of the course
     * @param theYear the course starts.
     */
    @Override
    public void CalibrateData(String courseName, String theTerm,
                              String theYear) throws FileNotFoundException {

        String searchQuery = buildSearchQuery(theTerm, courseName, theYear);

        // format the url and connect to the coursefinder.
        Document doc;
        try {
             doc = Jsoup.connect(
                     "https://coursefinder.utoronto.ca/course-search/" +
                             "search/courseInquiry?methodToCall=start&viewId=" +
                             "CourseDetails-InquiryView&courseId="
                             + courseName + searchQuery)
                    .get();
        } catch (IOException e){
            throw new FileNotFoundException();
        }

        // If the html has no information about term. i.e. files not found.
        if (doc.select("span#u158").text().isEmpty()){
            throw new FileNotFoundException();
        }

        filterData(doc);
    }

    // ============================== Helpers ==================================

    /**
     * Build a formatted string for url with given term, course name and year.
     *
     * @param courseName the name of the course
     * @param theTerm the term of the course
     * @param theYear the course starts
     */
    private String buildSearchQuery(String theTerm, String courseName, String theYear) {
        String termKey = "";
        String termId = "";

        if (theTerm.equals(Constants.FALL)){
            termKey = "9";
            char c = courseName.charAt(courseName.length() - 2);
            if (c == 'Y'){
                termId = "Y";
            }
            else if (c == 'H'){
                termId = "F";
            }

        } else if (theTerm.equals(Constants.WINTER)){
            termKey = "1";
            termId = "S";
        }
        return termId + theYear + termKey;
    }

    /**
     * Scrapes down and filters raw data from html.
     *
     * @param doc a document object associated with corresponding html.
     */
    private void filterData(Document doc) {
        // find element by combination of elements with id.
        String term = removeCss(doc.select("span#u158").text());
        String faculty = removeCss(doc.select("span#u13").text());
        String courseCode = removeCss(doc.select("div#u19").text());

        String a = doc.select("span#u254_line1").text();
        System.out.println(a.length() == 0);

        // loop over the rows in the html and add corresponding sections.
        String section = removeCss(doc.select("span#u245_line" + 0).text());
        String professor = removeCss(doc.select("span#u263_line" + 0).text());
        String time = removeCss(doc.select("span#u254_line" + 0).text());
        String location = removeCss(doc.select("span#u272_line" + 0).text());
        String deliveryMethod =
                removeCss(doc.select("span#u314_line" + 0).text());

        int i = 0;
        while (!section.equals("")){
            if (professor.isEmpty()){
                professor = Constants.TBA;
            }

            ArrayList<Object[]> times = splitDateTime(time);
            ArrayList<String> locations = splitLocations(location);

            // create the location time map.
            HashMap<Object[], String> locationTimeMap = new HashMap<>();

            for (int j = 0; j < locations.size(); j++){
                locationTimeMap.put(times.get(j), locations.get(j));
            }

            String theTerm;
            if (courseCode.contains("Y1")){
                theTerm = Constants.YEAR;
            } else if (term.contains("Fall")){
                theTerm = Constants.FALL;
            } else if (term.contains("Winter")){
                theTerm = Constants.WINTER;
            } else {
                theTerm = "";
            }
            addCourseToData(theTerm, section, faculty, locationTimeMap,
                    professor, deliveryMethod);

            i++;

            section = removeCss(doc.select("span#u245_line"+ i).text());
            professor = removeCss(doc.select("span#u263_line"+ i).text());
            time = removeCss(doc.select("span#u254_line"+ i).text());
            location = removeCss(doc.select("span#u272_line"+ i).text());
            deliveryMethod = removeCss(doc.select("span#u314_line"+ i).text());
        }
    }

    /**
     * Add the given data to self.data
     *
     * @param term the term of course
     * @param sectionName the name of the section
     * @param faculty the associated faculty
     * @param timeToLocationMap the current time and location. Given as a
*                         HashMap of the Time -> Location
     * @param theInstructor the instructor of the course section
     * @param theDeliveryMethod the delivery method of the course.
     */
    private void addCourseToData(String term,
                                 String sectionName,
                                 String faculty,
                                 HashMap<Object[], String> timeToLocationMap,
                                 String theInstructor,
                                 String theDeliveryMethod){

        // We will fix this in phase 2. Wait list current can only be set to
        // false.
        Course theCourse = new Course(sectionName, theInstructor, faculty,
                theDeliveryMethod, timeToLocationMap, term, false);
        placeToData(sectionName, theCourse);
    }

    // ============================== CSS Filters ==============================
    /**
     * Clean up the strings with Css tags.
     *
     * @param dirtyString the string being cleaned.
     * @return a string without css tags
     */
    private String removeCss(String dirtyString){
        return dirtyString.replaceAll("(?is)<style.*?>.*?</style>", "");
    }

    // ================================ Formatters =============================
    /**
     * Splits the formattedTimeString into the date, start time, end time in
     * that order
     *
     * If the time is TBA, assign the time to be 00:00:00
     *
     * @param formattedTimeString the formattedTimeString of the date, start,
     *                           and end times
     * @return nested arraylist with the form [{date, start time, end time}, {date, start time, end time}]
     */
    private ArrayList<Object[]> splitDateTime(String formattedTimeString){
        // If each section has multiple times, split into an array of those
        // times.
        String[] times = formattedTimeString.split("(?=\\s[A-Z])");

        ArrayList<Object[]> retList = new ArrayList<>();
        if (formattedTimeString.length() != 0){
            for (String timeEntry : times) {
                // Remove white space
                timeEntry = timeEntry.trim();
                String[] dateAndTime = timeEntry.split(" ");

                LocalTime formattedStart = formatTime(dateAndTime[1].split("-")[0]);
                LocalTime formattedEnd = formatTime(dateAndTime[1].split("-")[1]);

                retList.add(new Object[]{formatDate(dateAndTime[0]), formattedStart,
                        formattedEnd});
            }
        } else {
            retList.add(new Object[]{Constants.TBA, LocalTime.of(0, 0, 0),
                    LocalTime.of(0, 0, 0)});
        }
        return retList;
    }

    /**
     * Splits the formattedLocationString into an arraylist with [location, location]
     * If there is no location then the location is TBA.
     *
     * @param formattedLocationString the formattedLocationString of the "Location Location"
     * @return arraylist with the form [location, location]
     */
    private ArrayList<String> splitLocations(String formattedLocationString){
        ArrayList<String> retList = new ArrayList<>();

        if (!formattedLocationString.equals("")){
            // If there are multiple locations, get all the locations
            String[] locations = formattedLocationString.split("(?=\\s[A-Z][A-Z])");
            for (String element : locations) {
                element = element.trim();
                retList.add(element);
            }
        } else {
            // If no locations
            retList.add(Constants.TBA);
        }
        return retList;
    }

    /**
     * Returns a time object that
     * @param time the time as a string
     * @return the time as a Local time
     */
    private LocalTime formatTime(String time){
        String[] spliced = time.split(":");
        return LocalTime.of(Integer.parseInt(spliced[0]),
                Integer.parseInt(spliced[1]), 0, 0);
    }

    /**
     * Change the letter case of the given date.
     *
     * @param date a string of date with all upper case letter.
     * @return new string of date with the upper case at the front and lower case follows.
     */
    private String formatDate(String date){
        String firstLetter = date.substring(0, 1);
        String remainingLetters = date.substring(1).toLowerCase();
        return firstLetter + remainingLetters;
    }

    /**
     * A main method to develop this module
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        String [] courses = {"CSC207H1", "APS110H1", "CIV100H1", "MAT137Y1"};
        WebScraper a = new WebScraper();
        for (String course : courses){
            try {
                LinkedHashMap<String,
                        ArrayList<Course>> got = a.getData(course, "Fall",
                        "2021");
                System.out.println(got);
            } catch (FileNotFoundException e){
                System.out.println("File Not Found");
            }
        }
    }
}


