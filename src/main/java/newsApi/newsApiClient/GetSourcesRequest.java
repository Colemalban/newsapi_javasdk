package newsApi.newsApiClient;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.LinkedList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.Collection;

public class GetSourcesRequest {

    private Category category;
    private Country country;
    private Language language;
    
    public static class Builder {
        private Category category = null;
        private Country country = null;
        private Language language = null;

        public Builder country(Country country) {
            this.country = country;
            return this;
        }

        public Builder language(Language lang) {
            language = lang;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public GetSourcesRequest build() {
            return new GetSourcesRequest(this);
        }
    }

    private GetSourcesRequest(Builder builder) {
        this.category = builder.category;
        this.language = builder.language;
        this.country = builder.country;
    }

    public String toUrl() {
        StringBuilder url = new StringBuilder("https://newsapi.org/v1/sources?");
        List<String> params = new ArrayList<>();
        if(category != null) {
            params.add(category.toQueryString());
        }
        if(country != null) {
            params.add(country.toQueryString());
        }
        if(language != null) {
            params.add(language.toQueryString());
        }
        url.append(String.join("&", params));
        return url.toString();
    }

    public Collection<NewsSource> execute() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response resp = client.newCall(new Request.Builder().get().url(this.toUrl()).build()).execute();
        JsonObject bodyRoot = new JsonParser().parse(resp.body().string()).getAsJsonObject();
        JsonArray jsonSources = bodyRoot.get("sources").getAsJsonArray();
        Collection<NewsSource> sources = new LinkedList<>();
        for(JsonElement ele : jsonSources) {
            sources.add(new NewsSource(ele.getAsJsonObject()));
        }
        return sources;
    }
}