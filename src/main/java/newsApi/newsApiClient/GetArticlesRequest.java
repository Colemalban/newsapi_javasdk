package newsApi.newsApiClient;

import java.util.List;
import com.google.gson.JsonParser;
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

    public String toUrl() {
        StringBuilder url = new StringBuilder();
        url.append("https://newsapi.org/v1/articles?");
        List<String> params = new ArrayList<>();
        params.add(String.format("apiKey=%s", apiKey));
        params.add(source.toQueryString());
        if(sort != null) {
            params.add(sort.toQueryString());
        }
        url.append(String.join("&", params));
        return url.toString();
    }

    public Collection<Article> execute() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response resp = client.newCall(new Request.Builder().get().url(this.toUrl()).build()).execute();
        JsonObject body = new JsonParser().parse(resp.body().string()).getAsJsonObject();
        JsonArray jsonArticles = body.get("articles").getAsJsonArray();
        Collection<Article> articles = new HashSet<>();
        for(JsonElement article : jsonArticles) {
            articles.add(new Article(article.getAsJsonObject()));
        }
        return articles;
    }

}