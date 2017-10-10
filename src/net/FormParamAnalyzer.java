package net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

class FormParamAnalyzer {

    String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id


        List<String> paramList = new ArrayList<>();

        Element loginform = doc.getElementById("il_prop_cont_username");
        Elements inputElements = loginform.getElementsByTag("input");
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("Email"))
                value = username;
            else if (key.equals("Passwd"))
                value = password;
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
        }

        loginform = doc.getElementById("il_prop_cont_password");
        inputElements = loginform.getElementsByTag("input");
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("Email"))
                value = username;
            else if (key.equals("Passwd"))
                value = password;
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
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
}
