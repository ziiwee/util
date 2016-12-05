package com.ziiwee.util.detail;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by ziiwee on 2016/11/24.
 */
public class HttpRequestUtil {

    /**
     * GET请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return request(Method.GET, ContentType.X_WWW_FORM_URLENCODED, url, null);
    }

    /**
     * GET请求
     *
     * @param url
     * @param parameters
     * @return
     */
    public static String get(String url, Map<String, Object> parameters) {
        return request(Method.GET, ContentType.X_WWW_FORM_URLENCODED, url + "?" + serializeParameters(parameters), null);
    }

    /**
     * POST请求
     *
     * @param url
     * @param parameters
     * @return
     */
    public static String post(String url, Map<String, Object> parameters) {
        return request(Method.POST, ContentType.X_WWW_FORM_URLENCODED, url, serializeParameters(parameters));
    }

    /**
     * POST请求
     *
     * @param url
     * @param requestBody
     * @return
     */
    public static String post(String url, String requestBody) {
        return request(Method.POST, ContentType.X_WWW_FORM_URLENCODED, url, requestBody);
    }

    /**
     * POST请求
     *
     * @param url
     * @param requestBody
     * @return
     */
    public static String post(String url, Map<String, Object> parameters, String requestBody) {
        return request(Method.POST, ContentType.X_WWW_FORM_URLENCODED, url + "?" + serializeParameters(parameters), requestBody);
    }

    /**
     * 发起HTTP请求
     *
     * @param method
     * @param contentType
     * @param url
     * @param parameters
     * @return
     */
    public static String request(Method method, ContentType contentType, String url, String parameters) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);//请求不能使用缓存
            connection.setConnectTimeout(10000);
            connection.setRequestMethod(method.name());
            connection.setRequestProperty("Content-Type", contentType.getValue());
            if (parameters != null && parameters.length() > 0) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(parameters.getBytes());
                outputStream.close();
            }
            connection.connect();
            return inputStreamToString(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将map参数装换成链接参数
     *
     * @param parameters
     * @return
     */
    public static String serializeParameters(Map<String, Object> parameters) {
        String result;
        try {
            result = "";
            for (String key : parameters.keySet()) {
                result += key;
                result += "=";
                result += URLEncoder.encode(parameters.get(key).toString(), "UTF-8");
                result += "&";
            }
            result = result.substring(0, result.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }


    public enum Method {
        GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE
    }

    public enum ContentType {
        FORM_DATA("multipart/form-data"),
        X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
        TEXT("text/plain");

        private String value;

        ContentType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static String inputStreamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

}
