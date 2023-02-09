package shop.mtcoding.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

    public static String getThumbnail(String html) {
        // 1. content 내용을 Document로 받고. img 찾아내서(1,2,3), src를 찾아서 thumbnail에 넣어주기
        String thumbnail = "";
        Document doc = Jsoup.parse(html);
        Elements els = doc.select("img");

        if (els.size() == 0) {
            thumbnail = "/images/dora.png";
        } else {
            Element el = els.get(0);
            thumbnail = el.attr("src");

        }
        return thumbnail;
    }
}
