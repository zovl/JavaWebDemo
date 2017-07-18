package zovlzhongguanhua.demo.httprequest;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpRequestDemo {

    public static void main(String[] args) {

        // doGet();
        // doGetReceiveSystem();
        // doGetReceiveFile();

        // doPostSendString();
        // doPostSendFile();
        doPostForm();
        // doPostPartFile();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void doGet() {
        HttpRequest request = HttpRequest.get("http://httpbin.org/get")
                .connectTimeout(12000)
                .acceptCharset("UTF-8")
                .acceptEncoding("UTF-8")
                .accept("application/json")
                .trustAllCerts()
                .trustAllHosts()
                .useCaches(true)
                // .header("accept", "application/json")
                .basic("username", "Tom")
                .basic("password", "123456");
        httpRequest(request);
        request.disconnect();
    }

    private static void doGetReceiveSystem() {
        HttpRequest request = HttpRequest.get("http://httpbin.org/get")
                .receive(System.out);
        request.disconnect();
    }

    private static void doGetReceiveFile() {
        HttpRequest request = HttpRequest.get("http://httpbin.org/get")
                .receive(new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "receive"));
        request.disconnect();
    }

    private static void doPostSendString() {
        HttpRequest request = HttpRequest.get("http://httpbin.org/post")
                .send("person=May&age=16&gender=false");
        httpRequest(request);
        request.disconnect();
    }

    private static void doPostSendFile() {
        HttpRequest request = HttpRequest.get("http://httpbin.org/post")
                .send(new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "send"));
        httpRequest(request);
        request.disconnect();
    }

    private static void doPostForm() {

        Map<String, Object> params = new HashMap<>();
        params.put("username", "May");
        params.put("age", 24);
        params.put("gender", false);
        HttpRequest request = HttpRequest.get("http://httpbin.org/post")
                .form(params)
                .form("descroption", "Hi, Gay!");
        httpRequest(request);
        request.disconnect();
    }

    private static void doPostPartFile() {
        HttpRequest request = HttpRequest.get("http://httpbin.org/post")
                .part("file", new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "uploadFile"));
        httpRequest(request);
        request.disconnect();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void httpRequest(HttpRequest request) {
        int code = request.code();
        int bufferSize = request.bufferSize();
        int contentLength = request.contentLength();
        String body = request.body();
        boolean created = request.created();
        boolean badRequest = request.badRequest();
        boolean serverError = request.serverError();
        long date = request.date();
        long expires = request.expires();
        long lastModified = request.lastModified();
        boolean ignoreCloseExceptions = request.ignoreCloseExceptions();
        boolean ok = request.ok();
        boolean noContent = request.noContent();
        boolean notFound = request.notFound();
        boolean notModified = request.notModified();
        boolean isBodyEmpty = request.isBodyEmpty();
        String server = request.server();
        String eTag = request.eTag();
        URL url = request.url();
        String method = request.method();
        String location = request.location();
        String message = request.message();
        String toString = request.toString();
        String cacheControl = request.cacheControl();
        String charset = request.charset();
        Map<String, List<String>> headers = request.headers();
        // byte[] bytes = request.bytes();
        // BufferedInputStream buffer = request.buffer();
        // InputStreamReader reader = request.reader();
        // OutputStreamWriter writer = request.writer();
        // InputStream stream = request.stream();

        System.out.println("-------------------->");

        System.out.println("code：" + code);
        System.out.println("bufferSize：" + bufferSize);
        System.out.println("contentLength：" + contentLength);
        System.out.println("body：" + body);
        System.out.println("created：" + created);
        System.out.println("badRequest：" + badRequest);
        System.out.println("serverError：" + serverError);
        System.out.println("date：" + date);
        System.out.println("expires：" + expires);
        System.out.println("lastModified：" + lastModified);
        System.out.println("ignoreCloseExceptions：" + ignoreCloseExceptions);
        System.out.println("ok：" + ok);
        System.out.println("noContent：" + noContent);
        System.out.println("notFound：" + notFound);
        System.out.println("notModified：" + notModified);
        System.out.println("isBodyEmpty：" + isBodyEmpty);
        System.out.println("server：" + server);
        System.out.println("eTag：" + eTag);
        System.out.println("url：" + url);
        System.out.println("method：" + method);
        System.out.println("location：" + location);
        System.out.println("message：" + message);
        System.out.println("toString：" + toString);
        System.out.println("cacheControl：" + cacheControl);
        System.out.println("charset：" + charset);

        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                List<String> values = headers.get(key);
                StringBuffer stringBuffer = new StringBuffer();
                int i = 0;
                for (String value : values) {
                    ++i;
                    if (i == values.size()) {
                        stringBuffer.append(value);
                    } else {
                        stringBuffer.append(value + "/");
                    }
                }
                System.out.println("header：" + key + "=" + stringBuffer);
            }
        }
    }
}
