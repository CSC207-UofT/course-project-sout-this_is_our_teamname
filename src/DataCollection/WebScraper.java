package DataCollection;

import EntitiesAndObjects.Course;
import GlobalHelpers.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class WebScraper extends DataGetter{

    @Override
    public void CalibrateData(String courseName){
        try {
            Document doc = Jsoup.connect(
                    "https://coursefinder.utoronto.ca/course-search/search/courseInquiry?methodToCall=start&viewId=CourseDetails-InquiryView&courseId=CSC207H1F20219").get();
            System.out.println(doc.title());

            // find element by combination of elements with id.
            String term = doc.select("span#u158").text();
            String faculty = doc.select("span#u13").text();
            String coursecode = doc.select("div#u19").text();
            // clean the string
            term = RemoveCss(term);
            faculty = RemoveCss(faculty);
            coursecode = RemoveCss(coursecode);

            int i = 0;
            // rows
            while(i <= 100){
                String section = doc.select("span#u245_line"+ i).text();
                String prof = doc.select("span#u263_line"+ i).text();
                String time = doc.select("span#u254_line"+ i).text();
                String location = doc.select("span#u272_line"+ i).text();
                String delmethod = doc.select("span#u314_line"+ i).text();
                if (section.equals("")){
                    break;
                }

                // remove css
                section = RemoveCss(section);
                prof = RemoveCss(prof);
                delmethod = RemoveCss(delmethod);
                if (prof.isEmpty()){
                    prof = Constants.TBA;
                }
                ArrayList<ArrayList<Object>> times = splitDateTime(RemoveCss(time));
                ArrayList<String> locations = splitLocations(RemoveCss(location));

                // create and put course objects
                HashMap<ArrayList<Object>, String> locationTimeMap = new HashMap<>();
                int j = 0;
                while (j < locations.size()){
                    locationTimeMap.put(times.get(j), locations.get(j));
                    j++;
                }

                //TODO Waitlist and summer course?
                if (coursecode.contains("Y1")){
                    addYearCourseToData(section, faculty, locationTimeMap, prof, delmethod, false);
                }
                else if (term.contains("Fall")){
                    addTermedCourseToData("Fall", section, faculty, locationTimeMap, prof, delmethod, false);
                }
                else if (term.contains("Winter")){
                    addTermedCourseToData("Winter", section, faculty, locationTimeMap, prof, delmethod, false);
                }
                i++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    //TODO @ Matthew consider putting these method in datagetter? Since I am reusing them.
    // Waitlist always false
    /**
     * Add the given data to self.data
     * @param term the term of course
     * @param sectionName the name of the section
     * @param faculty the associated faculty
     * @param timeToLocationMap the current time and location. Given as a
     *                         HashMap of the Time -> Location
     * @param theInstructor the instructor of the course section
     * @param theDeliveryMethod the delivery method of the course.
     */
    private void addTermedCourseToData(String term,
                                       String sectionName,
                                       String faculty,
                                       HashMap<ArrayList<Object>,
                                               String> timeToLocationMap,
                                       String theInstructor,
                                       String theDeliveryMethod,
                                       boolean theWaitlist){
        Course theCourse = new Course(sectionName, theInstructor, faculty,
                theDeliveryMethod, timeToLocationMap, term, theWaitlist);
        placeToData(sectionName, theCourse);
    }

    /**
     * Add the given data to super.data.
     *
     * @param sectionName the name of the section
     * @param faculty the associated faculty
     * @param timeToLocationMap the current time and location. Given as a
     *                         HashMap of the Time -> Location
     * @param theInstructor the instructor of the course section
     * @param theDeliveryMethod the delivery method of the course.
     */
    private void addYearCourseToData(String sectionName,
                                     String faculty,
                                     HashMap<ArrayList<Object>, String> timeToLocationMap,
                                     String theInstructor,
                                     String theDeliveryMethod,
                                     boolean theWaitlist){
        Course theCourse = new Course(sectionName, theInstructor, faculty,
                theDeliveryMethod, timeToLocationMap, Constants.YEAR, theWaitlist);
        placeToData(sectionName, theCourse);
    }

    private String RemoveCss(String dirty){
        String cleaned;
        cleaned = dirty.replaceAll("(?is)<style.*?>.*?</style>", "");
        return cleaned;
    }

    /**
     * Splits the formattedTimeString into the date, start time, end time in
     * that order
     *
     * If the time is TBA, assign the time to be 00:00:00.
     * WE WILL RESOLVE THIS IN PHASE 1.
     *
     * @param formattedTimeString the formattedTimeString of the date, start,
     *                           and end times
     * @return nested arraylist with the form [[date, start time, end time], [date, start time, end time]]
     */
    private ArrayList<ArrayList<Object>> splitDateTime(String formattedTimeString){
        String[] times = formattedTimeString.split("(?=\\s[A-Z])");
        ArrayList<ArrayList<Object>> retList = new ArrayList<>();
        if (times.length != 0){
            for (String element : times) {
                element = element.trim();
                String[]elementl = element.split(" ");
                ArrayList<Object> l = new ArrayList<>();
                l.add(formatDate(elementl[0]));
                // split into times.
                l.add(elementl[1].split("-")[0] + ":00");
                l.add(elementl[1].split("-")[1] + ":00");
                retList.add(l);
            }
        }
        else{
            ArrayList<Object> l = new ArrayList<>();
            l.add(Constants.TBA);
            l.add(LocalTime.of(0, 0, 0));
            l.add(LocalTime.of(0, 0, 0));
            retList.add(l);
        }
        return retList;
    }

    private ArrayList<String> splitLocations(String formattedLocationString){
        ArrayList<String> retList = new ArrayList<>();
        if (!formattedLocationString.isEmpty()){
            String[] locations = formattedLocationString.split("(?=\\s[A-Z])");
            for (String element : locations) {
                element = element.trim();
                retList.add(element);
            }
        }
        else{
            retList.add(Constants.TBA);
        }
        return retList;
    }

    private String formatDate(String date){
        String firstLet = date.substring(0, 1);
        String remLet = date.substring(1);
        remLet = remLet.toLowerCase();
        return firstLet + remLet;
    }

    /**
     * A main method to develop this module
     *
     * @param args arguments
     */
    public static void main(String[] args) throws IOException {
        WebScraper a = new WebScraper();
        LinkedHashMap<String, Course> data = a.getData("");
        for (String key : data.keySet()) {
            System.out.println(data.get(key));
        }

    }
}


