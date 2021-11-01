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
            Element ele = doc.getElementById("u272_line3");
            System.out.println(ele);

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