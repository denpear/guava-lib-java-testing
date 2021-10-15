package htmlfiles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static guavagooglelibrary.GuavaCollectionsTest.print;


public class HandleHTML {
    @Test
    public void LaodHTMLinMemory() throws IOException {
        String home = System.getProperty("user.home");
        File input = new File(home+ "\\Downloads\\facebook-100006802965122\\posts\\your_posts_1.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements divsDescendant = doc.getElementsByClass("_3-95");
        divsDescendant.stream().forEach(div -> print(div.getElementsByClass("_3-95").text()));
    }
}