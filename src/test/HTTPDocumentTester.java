package test;

import net.FormParamAnalyzer;
import net.HTTPSiteLoader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTTPDocumentTester {

    public static void main(String[] args){
        HTTPSiteLoader httpSiteLoader = new HTTPSiteLoader();
        FormParamAnalyzer formParamAnalyzer = new FormParamAnalyzer();

        String url = "https://www.aulis.hs-bremen.de/login.php?client_id=hsbremen&lang=de";

        String elementID = "il_prop_cont_username";

        try {
            String html = httpSiteLoader.getHTTP(url);
            Document doc = formParamAnalyzer.getDocument(html);

            Element element = doc.getElementById(elementID);
            Elements inputElements = element.getElementsByTag("input");

            for(Element inputElement : inputElements){
                System.out.println(inputElement.attr("name"));
                System.out.println(inputElement.attr("value"));
                System.out.println();
            }

            //System.out.println(doc.getElementById(elementID));

            /*
            for (Element e : doc.getAllElements()) {
                System.out.println(e.toString());
            }
            */

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
