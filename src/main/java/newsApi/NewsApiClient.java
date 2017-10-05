package newsApi;

import java.io.IOException;
import java.util.Collection;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsApiClient {

    private String apiKey;
    private OkHttpClient httpClient;

    public NewsApiClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = new OkHttpClient();
    }

    public Collection<NewsSource> getSources() throws IOException {
        String endpoint = "https://newsapi.org/v1/sources?language=en";
        Request request = new Request.Builder().url(endpoint).build();
        Response resp = httpClient.newCall(request).execute(); //TODO try to throw new exceptions for this
        
        return null;
    }
}
