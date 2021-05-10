package saturnStroller.geekTime.course_202104.nio;

import okhttp3.*;

import java.io.IOException;

/**
 * @Description  HttpClient请求
 * @Author SaturnStroller
 */
public class HttpClientTest {

    static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8081";
        System.out.println(doGet(url));
    }

    /**
     * GET
     */
    public static String doGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * POST-JSON
     */
    public static String doPost(String url,String bodyJson) throws IOException {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType,bodyJson)).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
