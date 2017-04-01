package zovlzhongguanhua.demo.httpclient;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientDemo {

    public static void main(String[] args) {

        // doGet();
        // doPost();
        // uploadFile();
        // mutilPart();

        // handleGet();

        // fluentGet();
        // fluentPost();

        // futureGet();
        // futureCallbackGet();
        futureCallbackStreamGet();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    public static void doGet() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        httpRequest(httpGet);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpResponse(httpResponse);
        close(httpClient, httpResponse, httpGet, null);
    }

    public static void doPost() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");
        httpRequest(httpPost);
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("username", "vip"));
        pairs.add(new BasicNameValuePair("password", "secret"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResponse(httpResponse);
        close(httpClient, httpResponse, null, httpPost);
    }

    public static void uploadFile() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");

        File file = null;
        InputStreamEntity entity = null;
        try {
            file = new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "uploadFile");
            entity = new InputStreamEntity(new FileInputStream(file), -1, ContentType.APPLICATION_OCTET_STREAM);
            entity.setChunked(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // FileEntity e = new FileEntity(file, "binary/octet-stream");
        httpPost.setEntity(entity);

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResponse(httpResponse);
        close(httpClient, httpResponse, null, httpPost);
    }

    public static void mutilPart() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");

        File file = new File(System.getProperty("user.dir") + File.separator + "unirest-demo" + File.separator + "uploadFile");
        FileBody fileBody = new FileBody(file);
        StringBody stringBody = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
        HttpEntity entity = MultipartEntityBuilder.create()
                .addPart("file", fileBody)
                .addPart("string", stringBody)
                .build();
        httpPost.setEntity(entity);

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResponse(httpResponse);
        close(httpClient, httpResponse, null, httpPost);
    }

    public static void fluentGet() {

        Request request = Request.Get("http://httpbin.org/get");
        Response response = null;
        try {
            response = request.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Content content = response.returnContent();
            content(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fluentPost() {

        Form form = Form.form()
                .add("username",  "vip")
                .add("password",  "secret");
        Request request = Request.Post("http://httpbin.org/post")
                .bodyForm(form.build());
        Response response = null;
        try {
            response = request.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse httpResponse = response.returnResponse();
            httpResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleGet() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://httpbin.org/get");

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("status: " + status);
                }
            }
        };

        try {
            httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void futureGet() {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        Future<HttpResponse> future = httpclient.execute(httpGet, null);
        try {
            HttpResponse httpResponse = future.get();
            httpResponse(httpResponse);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void futureCallbackGet() {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        final CountDownLatch latch = new CountDownLatch(1);
        FutureCallback<HttpResponse> futureCallback = new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse httpResponse) {
                latch.countDown();
                httpResponse(httpResponse);
            }

            public void failed(final Exception ex) {
                latch.countDown();
            }

            public void cancelled() {
                latch.countDown();
            }
        };
        httpclient.execute(httpGet, futureCallback);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void futureCallbackStreamGet() {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        HttpAsyncRequestProducer producer = HttpAsyncMethods.create(httpGet);
        AsyncCharConsumer<HttpResponse> consumer = new AsyncCharConsumer<HttpResponse>() {

            private HttpResponse response;

            @Override
            protected void onResponseReceived(final HttpResponse response) {
                this.response = response;
            }

            @Override
            protected void onCharReceived(final CharBuffer buf, final IOControl ioctrl) throws IOException {}

            @Override
            protected void releaseResources() {}

            @Override
            protected HttpResponse buildResult(final HttpContext context) {
                return this.response;
            }

        };
        final CountDownLatch latch = new CountDownLatch(1);
        FutureCallback<HttpResponse> futureCallback = new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse httpResponse) {
                latch.countDown();
                httpResponse(httpResponse);
            }

            public void failed(final Exception ex) {
                latch.countDown();
            }

            public void cancelled() {
                latch.countDown();
            }
        };
        httpclient.execute(producer, consumer, futureCallback);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    public static void httpRequest(HttpRequest httpRequest) {
        System.out.println("-------------------->");
        System.out.println("httpRequest：" + httpRequest);

        RequestLine requestLine = httpRequest.getRequestLine();
        if (requestLine != null) {
            requestLine(requestLine);
        }
        Header[] headers = httpRequest.getAllHeaders();
        if (headers != null) {
            for (Header header : headers) {
                System.out.println("headers：" + header.getName() + "=" + header.getValue());
            }
        }
        ProtocolVersion protocolVersion = httpRequest.getProtocolVersion();
        if (protocolVersion != null) {
            protocolVersion(protocolVersion);
        }
        HttpParams httpParams = httpRequest.getParams();
        if (httpParams != null) {
            httpParams(httpParams);
        }
    }

    public static void requestLine(RequestLine requestLine) {
        System.out.println("-------------------->");
        System.out.println("requestLine：" + requestLine);
        System.out.println("requestLine method：" + requestLine.getMethod());
        System.out.println("requestLine uri：" + requestLine.getUri());

        ProtocolVersion protocolVersion = requestLine.getProtocolVersion();
        if (protocolVersion != null) {
            protocolVersion(protocolVersion);
        }
    }

    public static void httpParams(HttpParams httpParams) {
        System.out.println("-------------------->");
        System.out.println("requestLine：" + httpParams);
    }

    public static void httpResponse(HttpResponse httpResponse) {
        System.out.println("-------------------->");
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine != null) {
            statusLine(statusLine);
        }
        Locale locale = httpResponse.getLocale();
        if (locale != null) {
            locale(locale);
        }
        ProtocolVersion protocolVersion = httpResponse.getProtocolVersion();
        if (protocolVersion != null) {
            protocolVersion(protocolVersion);
        }
        Header[] headers = httpResponse.getAllHeaders();
        if (headers != null) {
            for (Header header : headers) {
                System.out.println("headers：" + header.getName() + "=" + header.getValue());
            }
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            httpEntity(httpEntity);
        }
    }

    public static void statusLine(StatusLine statusLine) {
        System.out.println("-------------------->");
        System.out.println("statusLine：" + statusLine);
        System.out.println("statusLine statusCode：" +statusLine.getStatusCode());
        System.out.println("statusLine reasonPhrase：" +statusLine.getReasonPhrase());
        
        ProtocolVersion protocolVersion = statusLine.getProtocolVersion();
        if (protocolVersion != null) {
            protocolVersion(protocolVersion);
        }
    }

    public static void protocolVersion(ProtocolVersion protocolVersion) {
        System.out.println("-------------------->");
        System.out.println("protocolVersion：" + protocolVersion);
        System.out.println("protocolVersion protocol：" + protocolVersion.getProtocol());
        System.out.println("protocolVersion major：" + protocolVersion.getMajor());
        System.out.println("protocolVersion minor：" + protocolVersion.getMinor());
    }

    public static void locale(Locale locale) {
        System.out.println("-------------------->");
        System.out.println("locale country：" + locale.getCountry());
        System.out.println("locale displayCountry：" + locale.getDisplayCountry());
        System.out.println("locale displayName：" + locale.getDisplayName());
        System.out.println("locale displayName：" + locale.getDisplayName());
    }

    public static void httpEntity(HttpEntity httpEntity) {
        System.out.println("-------------------->");
        System.out.println("httpEntity contentLength：" + httpEntity.getContentLength());
        System.out.println("httpEntity isChunked：" + httpEntity.isChunked());
        System.out.println("httpEntity isStreaming：" + httpEntity.isStreaming());
        System.out.println("httpEntity isRepeatable：" + httpEntity.isRepeatable());

        Header contentEncoding = httpEntity.getContentEncoding();
        if (contentEncoding != null) {
            System.out.println("httpEntity contentEncoding：" + contentEncoding.getName() + "=" + contentEncoding.getValue());
        }
        Header contentType = httpEntity.getContentType();
        if (contentEncoding != null) {
            System.out.println("httpEntity contentType：" + contentType.getName() + "=" + contentType.getValue());
        }
        try {
            System.out.println("httpEntity string：" + EntityUtils.toString(httpEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void content(Content content) {
        System.out.println("-------------------->");
        System.out.println("content string：" + content.asString());
        System.out.println("content string：" + content.toString());
        System.out.println("content byte[]：" + content.asBytes());
        System.out.println("content stream：" + content.asStream());

        ContentType contentType = content.getType();
        if (contentType != null) {
            contentType(contentType);
        }
    }

    public static void contentType(ContentType contentType) {
        System.out.println("-------------------->");
        System.out.println("contentType：" + contentType);
        System.out.println("contentType mimeType：" + contentType.getMimeType());
        System.out.println("contentType charset：" + contentType.getCharset());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void close(CloseableHttpClient httpclient, CloseableHttpResponse httpResponse, HttpGet httpGet, HttpPost httpPost) {
        if (httpGet != null && !httpGet.isAborted()) {
            httpGet.abort();
        }
        if (httpPost != null && !httpPost.isAborted()) {
            httpPost.abort();
        }
        if (httpResponse != null) {
            try {
                EntityUtils.consume(httpResponse.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (httpclient != null) {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
