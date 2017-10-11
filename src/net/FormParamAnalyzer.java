package net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class FormParamAnalyzer {

    public String getFormParams(String html, String username, String password, List<String> elementIDList)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        List<String> paramList = new ArrayList<>();

        for (String elementID : elementIDList){
            Element loginform = doc.getElementById(elementID);
            Elements inputElements = loginform.getElementsByTag("input");
            for (Element inputElement : inputElements) {
                String key = inputElement.attr("name");
                String value = inputElement.attr("value");

                if (key.equals("username"))
                    value = username;
                else if (key.equals("password"))
                    value = password;
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));

                //System.out.println(key + ": " + value);
            }
        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&").append(param);
            }
        }
        return result.toString();
    }

    public Document getDocument(String html){
        return Jsoup.parse(html);
    }

    public boolean checkLogin(String html, String elementID){
        Document doc = Jsoup.parse(html);

        if (doc.getElementById(elementID) != null){return true;}
        else {return false;}
    }
}
