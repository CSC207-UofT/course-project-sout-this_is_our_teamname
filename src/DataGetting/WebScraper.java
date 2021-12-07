package DataGetting;

import Helpers.Search;
import TimeTableObjects.Course;
import Helpers.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

/**
 * TODO REMOVE THIS SENTENCE
 *
 * A WebScraper class. It is a DataGetter class that gets the data from a the course finder
 * website.
 */
public class WebScraper extends CourseGetter {

    /**
     * Constructor of the WebScraper. Reads and filters the data correctly
     * into the data hashmap.
     *
     * @param courseName the name of the course
     * @param theTerm the term of the course
     * @param theYear the course starts.
     * @exception FileNotFoundException Throws file not found exception if the html for the
     * given course is not found.
     */
    @Override
    public void CalibrateData(String courseName, String theTerm,
                              String theYear) throws FileNotFoundException {

        String searchQuery = formatSearchQuery(theTerm, courseName, theYear);

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

        filterData(doc, theYear);
    }

    // ============================== Helpers ==================================

    /**
     * Build a formatted string for url with given term, course name and year.
     *
     * @param courseName the name of the course
     * @param theTerm the term of the course
     * @param theYear the course starts
     */
    private String formatSearchQuery(String theTerm, String courseName, String theYear) {
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
    private void filterData(Document doc, String year) {
        // find element by combination of elements with id.
        String term = removeCss(doc.select("span#u158").text());
        String faculty = removeCss(doc.select("span#u13").text());
        String courseCode = removeCss(doc.select("div#u19").text());


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

            if (locations.size() < times.size()){
                int diff = times.size() - locations.size();
                for (int k = 0; k < diff; k++){
                    locations.add((int) Math.ceil((double) diff / 2),
                            Constants.TBA);
                }
            }

            for (int j = 0; j < locations.size(); j++){
                locationTimeMap.put(times.get(j), locations.get(j));
            }

            String theTerm = formatTerm(courseCode, term);

            addCourseToData(courseCode, theTerm, section, faculty,
                    locationTimeMap, professor, deliveryMethod, year);

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
    private void addCourseToData(String courseName,
                                 String term,
                                 String sectionName,
                                 String faculty,
                                 HashMap<Object[], String> timeToLocationMap,
                                 String theInstructor,
                                 String theDeliveryMethod,
                                 String year){

        // We will fix this in phase 2. Wait list current can only be set to
        // false.
        Course theCourse = new Course(courseName, sectionName, theInstructor,
                faculty, theDeliveryMethod, timeToLocationMap,
                term + " " + year);
        placeToData(sectionName, theCourse);
    }

    // ============================== CSS Filters ==============================
    /**
     * Clean up the strings with Css tags.
     * Example of unfiltered string <\div> CSC207H1 <\div> and wanted string CSC207H1.
     *
     * @param unfilteredString the string being cleaned.
     * @return a string without css tags
     */

    private String removeCss(String unfilteredString){
        return unfilteredString.replaceAll("(?is)<style.*?>.*?</style>", "");
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

        ArrayList<Object[]> timesList = new ArrayList<>();
        if (formattedTimeString.length() != 0){
            for (String timeEntry : times) {
                if (isTimeString(timeEntry)){
                    // Remove white space
                    timeEntry = timeEntry.trim();
                    String[] dateAndTime = timeEntry.split(" ");

                    LocalTime formattedStart = formatTime(dateAndTime[1].split("-")[0]);
                    LocalTime formattedEnd = formatTime(dateAndTime[1].split("-")[1]);

                    timesList.add(new Object[]{formatDate(dateAndTime[0]), formattedStart,
                            formattedEnd});
                }
            }
        } else {
            timesList.add(new Object[]{Constants.TBA, LocalTime.of(0, 0, 0),
                    LocalTime.of(0, 0, 0)});
        }
        return timesList;
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
     * Reformat the time string so that it corresponds to what course object wants.
     *
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
     * Get the formatted term from the given course code and term.
     *
     * @param courseCode string of the course code.
     * @param term string of the term.
     * @return new string of the determined term.
     */
    private String formatTerm(String courseCode, String term){
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
        return theTerm;
    }

    /**
     * An helper method to check if the string for time is a correct date and
     * time string. Used to help avoid fringe cases (eg ECE110H1, Winter 2022)
     * @param input the string to be checked
     * @return true if the string is a valid time string
     */
    private boolean isTimeString(String input){
        String[] splicedInput = input.trim().split(" ");
        if (splicedInput.length != 2){
            return false;
        }

        String[] splicedTime = splicedInput[1].split("-");
        return splicedTime.length == 2;
    }

    public String toString(){
        return "WebScraper";
    }

    public static void main(String[] args) {
        CourseGetter scraper = new WebScraper();
        try {
            LinkedHashMap<String, Course> data = scraper.retrieveData(
                    "ECE110H1", "Winter", "2022");
            for (String course : data.keySet()){
                System.out.print(course + ": ");
                System.out.println(data.get(course));
            }
        } catch (FileNotFoundException e){
            System.out.println(":(");
        }
    }
}


