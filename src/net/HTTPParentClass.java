package net;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

public class HTTPParentClass {

    List<String> cookies;

    HttpsURLConnection conn;

    final String USER_AGENT = "Mozilla/5.0";

    public List<String> getCookies() {
        return cookies;
    }

    void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }
}
