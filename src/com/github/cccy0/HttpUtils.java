package com.github.cccy0;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author Zhai
 * 2020/8/20 10:03
 */

public class HttpUtils {
    public static void doGetAsync(String urlString, CallBack callback) {
//        new Thread(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5 * 1000);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    String message = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    inputStream.close();
                    connection.disconnect();
                    callback.onRequestComplete(message);
                } finally {
                    if (connection != null) {
                        connection.getInputStream().close();
                        connection.disconnect();
                    }
                }
            } catch (Exception e) {
                callback.onRequestComplete("翻译失败");
            }
//        });
    }

    interface CallBack {
        void onRequestComplete(String result);
    }
}
