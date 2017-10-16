package newsApi.newsApiClient;

import java.util.List;
import com.google.gson.JsonParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.ArrayList; 
import java.util.Collection;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.HashSet;

public class GetArticlesRequest {

    private String apiKey;
    private NewsSource source;
    private SortByInstruction sort;

    public static class Builder {
        
        private String apiKey = null;
        private NewsSource source = null;
        private SortByInstruction sort = null;

        public Builder(String apiKey, NewsSource source) {
            this.apiKey= apiKey;
            this.source = source;
        }

        public Builder sortBy(SortByInstruction sort) {
            this.sort = sort;
            return this;
        }

        public GetArticlesRequest build() {
            return new GetArticlesRequest(this);
        }
    }

    private GetArticlesRequest(Builder b) {
        this.apiKey = b.apiKey;
        this.source = b.source;
        this.sort = b.sort;
    }

    public HttpUrl toUrl() {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder().scheme("https").host("newsapi.org").addPathSegment("v1").addPathSegment("articles");
        urlBuilder = urlBuilder.addQueryParameter("apiKey", apiKey).addQueryParameter("source", source.getId());
        if(sort != null) {
            return urlBuilder.addQueryParameter("sortBy", sort.toString()).build();
        }
        return urlBuilder.build();
    }

    public Collection<Article> execute() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response resp = client.newCall(new Request.Builder().get().url(this.toUrl()).build()).execute();
        if(resp.code() != 200) {
            throw new IOException("Failed HTTP Request");
        }
        //TODO move this JSON parsing code somewhere else
        JsonObject body = new JsonParser().parse(resp.body().string()).getAsJsonObject();
        JsonArray jsonArticles = body.get("articles").getAsJsonArray();
        Collection<Article> articles = new HashSet<>();
        for(JsonElement article : jsonArticles) {
            try {
                articles.add(new Article(article.getAsJsonObject()));
            } catch(Exception e) {
                //Do nothing, we're just dropping bad articles for now
                //TODO make the article handling more elegant lol
            }
        }
        return articles;
    }

}