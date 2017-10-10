package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class HTTPTestOne {

    private String url = "http://example.com";
    private String charset = java.nio.charset.StandardCharsets.UTF_8.name();
    private String param1 = "value1";
    private String param2 = "value2";

    private String query;

    public static void main(String[] args){

        HTTPTestOne httpTestOne = new HTTPTestOne();

    }

    private HTTPTestOne() {
        try {
            query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(param1, charset),
                    URLEncoder.encode(param2, charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            fireHTTPRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fireHTTPRequest() throws IOException {
        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();

        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }

    }
}
