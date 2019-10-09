package com.joabe.getwebpagesourcecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebCrawlerUtil {

    static String getWebPageSourceCode(String queryUrl) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String webPageSource = null;

        try {
            URL requestURL = new URL(queryUrl);

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader((new InputStreamReader(inputStream)));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            webPageSource = builder.toString();

        } catch (IOException e) {

        }

        return webPageSource;
    }
}
