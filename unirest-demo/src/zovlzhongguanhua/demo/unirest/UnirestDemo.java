package zovlzhongguanhua.demo.unirest;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.body.Body;
import org.apache.http.HttpEntity;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UnirestDemo {

    public static void main(String[] args) {

        get();
        // post();
        // upload();
        // custom();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void get() {
        HttpResponse<String> response = null;
        BaseRequest request = Unirest.get("http://httpbin.org/{method}")
                .routeParam("method", "get")
                .queryString("name", "Mark");

        try {
            response = request.asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        httpRequest(request.getHttpRequest());
        httpResponse(response);
    }

    private static void post() {
        HttpResponse<String> response = null;
        BaseRequest request = Unirest.post("http://httpbin.org/post")
                .queryString("name", "Mark")
                .field("last", "Polo");
        try {
            response = request.asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        httpRequest(request.getHttpRequest());
        httpResponse(response);
    }

    private static void upload() {
        HttpResponse<String> response = null;
        BaseRequest request = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .field("parameter", "value")
                .field("file", new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "uploadFile"));

        try {
            response = request.asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        httpRequest(request.getHttpRequest());
        httpResponse(response);
    }

    private static void custom() {
        String userDir = System.getProperty("user.dir");
        HttpResponse<String> response = null;
        BaseRequest request = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .body("{\"parameter\":\"value\", \"foo\":\"bar\"}");

        try {
            response = request.asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        httpRequest(request.getHttpRequest());
        httpResponse(response);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    public static void httpRequest(HttpRequest request) {
        System.out.println("-------------------->");

        System.out.println("url：" + request.getUrl());

        HttpMethod method = request.getHttpMethod();
        if (method != null) {
            System.out.println("method：" + method.name());
            System.out.println("method：" + method.ordinal());
        }

        Body body = request.getBody();
        if (body != null) {
            System.out.println("body：" + body.toString());
        }

        if (body != null) {
            HttpEntity entity = body.getEntity();
            if (entity != null) {
                System.out.println("isChunked：" + entity.isChunked());
                System.out.println("isRepeatable：" + entity.isRepeatable());
                System.out.println("contentLength：" + entity.getContentLength());
                System.out.println("isStreaming：" + entity.isStreaming());
            }
        }

        Map<String, List<String>> headers = request.getHeaders();
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                List<String> values = headers.get(key);
                StringBuffer buffer = new StringBuffer();
                int i = 0;
                for (String value : values) {
                    ++i;
                    if (i == values.size()) {
                        buffer.append(value);
                    } else {
                        buffer.append(value + "/");
                    }
                }
                System.out.println("header：" + key + "=" + buffer);
            }
        }

        // System.out.println("contentType：" + entity.getContentType().getName() + "=" + entity.getContentType().getValue());
        // System.out.println("contentEncoding：" + entity.getContentEncoding().getName() + "=" + entity.getContentEncoding().getValue());
    }

    public static void httpResponse(HttpResponse response) {
        System.out.println("-------------------->");

        System.out.println("body：" + response.getBody());
        System.out.println("statusText：" + response.getStatusText());
        System.out.println("status：" + response.getStatus());
        System.out.println("rawBody：" + response.getRawBody());
        System.out.println("rawBody：" + response.getRawBody().toString());

        Headers headers = response.getHeaders();
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                List<String> values = headers.get(key);
                StringBuffer buffer = new StringBuffer();
                int i = 0;
                for (Object value : values) {
                    ++i;
                    if (i == values.size()) {
                        buffer.append(value);
                    } else {
                        buffer.append(value + "/");
                    }
                }
                System.out.println("header：" + key + "=" + buffer);
            }
        }
    }
}
