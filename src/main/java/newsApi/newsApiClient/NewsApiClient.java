package newsApi.newsApiClient;

import newsApi.newsApiClient.url.GetSourcesUrlConstructor;
import newsApi.newsApiClient.url.GetArticlesUrlConstructor;
import newsApi.newsApiClient.url.NewsApiUrl;
import newsApi.newsSource.NewsSource;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.Set;

import org.junit.experimental.categories.Category;

import java.util.HashMap;

public class NewsApiClient {

    private String apiKey;
    private OkHttpClient httpClient;

    public NewsApiClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = new OkHttpClient();
    }

    public Collection<Object> getArticles(NewsSource source, String apiKey, SortByInstruction sortBy) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("source", source.getId());
        params.put("apiKey", apiKey);
        params.put("sortBy", sortBy.toString());
        NewsApiUrl getArticlesUrl = new GetArticlesUrlConstructor().build(params);
        Request request = new Request.Builder().url(getArticlesUrl.toString()).build();
        Response resp = httpClient.newCall(request).execute(); //TODO try to throw new exceptions for this
        return null;
    }


    public Collection<Object> getArticles(NewsSource source, String apiKey) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("source", source.getId());
        params.put("apiKey", apiKey);
        NewsApiUrl getArticlesUrl = new GetArticlesUrlConstructor().build(params);
        Request request = new Request.Builder().url(getArticlesUrl.toString()).build();
        Response resp = httpClient.newCall(request).execute(); //TODO try to throw new exceptions for this
        return null;
    }

    public Collection<NewsSource> getSources(Map<String, Enum> params) throws IOException {
        NewsApiUrl getSourcesUrl = new GetSourcesUrlConstructor().build(params);
        Request request = new Request.Builder().url(getSourcesUrl.toString()).build();
        Response resp = httpClient.newCall(request).execute(); //TODO try to throw new exceptions for this
        JsonObject responseObject = new JsonParser().parse(resp.body().string()).getAsJsonObject();
        JsonArray sourceObjects = responseObject.get("sources").getAsJsonArray(); 
        Collection<NewsSource> sources = new HashSet<>();
        for(JsonElement sourceObject : sourceObjects) {
            sources.add(new NewsSource(sourceObject.getAsJsonObject()));
        }
        return sources;
    }



}
