package net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AulisHTTPLogin {

    private HTTPSiteLoader httpSiteLoader;
    private FormParamAnalyzer formParamAnalyzer;
    private HTTPPostSender httpPostSender;

    private String url = "https://www.aulis.hs-bremen.de/login.php?client_id=hsbremen&lang=de";

    private AulisHTTPLogin(){
        this.httpSiteLoader = new HTTPSiteLoader();
        this.formParamAnalyzer = new FormParamAnalyzer();
        this.httpPostSender = new HTTPPostSender();
    }

    public static void main(String[] args){
        AulisHTTPLogin aulisHTTPLogin = new AulisHTTPLogin();
        boolean success = aulisHTTPLogin.login();
        System.out.println(success);
    }

    private boolean login(){

        // make sure cookies is turn on
        CookieHandler.setDefault(new CookieManager());

        try {
            // 1. Send a "GET" request, so that you can extract the form's data.
            String page;
            page = httpSiteLoader.getHTTP(url);
            String postParams = formParamAnalyzer.getFormParams(page, "fniehaus", "Hallo08!");

            // 2. Construct above post's content and then send a POST request for
            // authentication
            httpPostSender.sendPost(url, postParams);

            // 3. success then go to gmail.
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
