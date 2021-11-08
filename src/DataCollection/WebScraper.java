package DataCollection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

public class WebScraper extends DataGetter{
    @Override
    public void CalibrateData(String courseName){
        throw new UnsupportedOperationException("This is not Implemented in " +
                "Phase 0.");
    }

}



class Main {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(
                    "https://coursefinder.utoronto.ca/course-search/search/courseInquiry?methodToCall=start&viewId=CourseDetails-InquiryView&courseId=CSC207H1F20219").get();
            System.out.println(doc.title());
            // find element by combination of elements with id.
            while
            Elements ele = doc.select("span#u245_line2");
            String code = ele.text();
            // remove css tags.
            code = code.replaceAll("(?is)<style.*?>.*?</style>", "");
            System.out.println(code);

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

//            String description = doc.select("meta[name=description]").get(0).attr("content");
//            System.out.println("Meta description : " + description);
//
//            String keywords = doc.select("meta[name=keywords]").first().attr("content");
//            System.out.println("Meta keyword : " + keywords);
//            Element formElement = doc.getElementById("loginForm");




        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}