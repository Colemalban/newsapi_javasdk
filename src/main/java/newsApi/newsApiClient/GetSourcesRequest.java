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
import okhttp3.HttpUrl; 

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

    public HttpUrl toUrl() {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder().scheme("https").host("newsapi.org").addPathSegment("v1").addPathSegment("sources");
        if(category != null) {
            urlBuilder.addQueryParameter("category", category.toString());
        }
        if(country != null) {
            urlBuilder.addQueryParameter("country", country.toString());
        }
        if(language != null) {
            urlBuilder.addQueryParameter("language", language.toString());
        }
        return urlBuilder.build();
    }

    public Collection<NewsSource> execute() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response resp = client.newCall(new Request.Builder().get().url(this.toUrl()).build()).execute();
        if(resp.code() != 200) {
            throw new IOException("Failed http request");
        }
        JsonObject bodyRoot = new JsonParser().parse(resp.body().string()).getAsJsonObject();
        JsonArray jsonSources = bodyRoot.get("sources").getAsJsonArray();
        Collection<NewsSource> sources = new LinkedList<>();
        for(JsonElement ele : jsonSources) {
            sources.add(new NewsSource(ele.getAsJsonObject()));
        }
        return sources;
    }
}