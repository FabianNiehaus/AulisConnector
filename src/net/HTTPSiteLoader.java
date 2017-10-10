package net;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class HTTPSiteLoader extends HTTPParentClass {

    String getHTTP(String url) throws IOException {
        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        if (cookies != null) {
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
            in.close();

        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));

        return response.toString();
    }
}
