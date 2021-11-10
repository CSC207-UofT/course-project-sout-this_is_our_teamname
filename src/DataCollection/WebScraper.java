package DataCollection;

import EntitiesAndObjects.Course;
import GlobalHelpers.Constants;
import GlobalHelpers.StringToTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
            String term;
            String coursecode;
            Elements ele5 = doc.select("span#u158");
            Elements ele6 = doc.select("div#u19");
            System.out.println(ele6.text());
//            if (ele6.text().contains("Y1")){
//                addYearCourseToData();
//            }
//            else if (ele5.text().contains("Fall")){
//                term = 'Fall';
//                addTermedCourseToData(term,);
//            }
//            else if (ele5.text().contains("Winter")){
//                term = 'Winter';
//                addTermedCourseToData(term,);
//            }
            int i = 0;
            while(i <= 100){
                Elements ele = doc.select("span#u245_line"+ i);
                String code = ele.text();
                Elements ele2 = doc.select("span#u254_line"+ i);
                String time = ele2.text();
                if (code.equals("")){
                    break;
                }
                // remove css
                code = RemoveCss(code);
                time = RemoveCss(time);

                System.out.println(code);
                System.out.println(time);
                i++;
            }

            Elements ele2 = doc.select("span#u254_line2");
            String time = ele2.text();
            // remove css tags.
            time = time.replaceAll("(?is)<style.*?>.*?</style>", "");
            System.out.println(time);

            Elements ele3 = doc.select("span#u263_line2");
            String prof = ele3.text();
            // remove css tags.
            prof = prof.replaceAll("(?is)<style.*?>.*?</style>", "");
            System.out.println(prof);

            Elements ele4 = doc.select("span#u272_line2");
            String loc = ele4.text();
            // remove css tags.
            loc = loc.replaceAll("(?is)<style.*?>.*?</style>", "");
            System.out.println(loc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    //TODO @ Matthew consider putting these method in datagetter? Since I am reusing them.
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
     * @return the string array of length 3 of the date, start time, and end
     * time
     */
    private ArrayList<Object> splitDateTime(String formattedTimeString){
        String[] splicedInfo = formattedTimeString.split(" ");
        ArrayList<Object> retList = new ArrayList<>();
        if (!formattedTimeString.isEmpty()) {
            retList.add(formatDate(splicedInfo[0]));
            // split into times.
            retList.add(splicedInfo[1].split("-")[0] + ":00");
            retList.add(splicedInfo[1].split("-")[1] + ":00");
        } else {
            retList.add(Constants.TBA);
            retList.add(LocalTime.of(0,0,0));
            retList.add(LocalTime.of(0,0, 0));
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
    public static void main(String[] args) {
        WebScraper a = new WebScraper();
        a.CalibrateData("");
        String s = "18:00-20:00";
       String[] b = s.split("-");
        System.out.println(Arrays.toString(b));
    }
}


