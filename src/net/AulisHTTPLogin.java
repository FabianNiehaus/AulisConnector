package net;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import javax.swing.plaf.synth.Region;
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

        // set Login Form IDs
        List<String> elementIDList = new ArrayList<>();
        elementIDList.add("il_prop_cont_username");
        elementIDList.add("il_prop_cont_password");

        // set user data
        String username = "fniehaus";
        String password = "Hallo08!";

        try {
            // 1. Send a "GET" request, so that you can extract the form's data.
            String page;
            page = httpSiteLoader.getHTTP(url);
            String postParams = formParamAnalyzer.getFormParams(page, username, password, elementIDList);

            // 2. Construct above post's content and then send a POST request for
            // authentication
            httpPostSender.sendPost(url, postParams);

            // 3. success then go to gmail
            String result = httpSiteLoader.getHTTP("https://www.aulis.hs-bremen.de/ilias.php?baseClass=ilPersonalDesktopGUI&cmd=jumpToSelectedItems");

            /*
            boolean login = formParamAnalyzer.checkLogin(result, "block_pditems_0_blhead");

            if (login) {
                System.out.println("Login erfolgreich!");
                return true;
            } else {
                System.out.println("Login fehlgeschlagen!");
                return false;
            }
            */

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
