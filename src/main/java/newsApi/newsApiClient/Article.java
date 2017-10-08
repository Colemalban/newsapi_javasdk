package newsApi.newsApiClient;

import com.google.gson.JsonObject;

public class Article {
    private String author;
    private String title;
    private String url;
    private String description;
    private String datePublishedString;

    public Article(JsonObject json) {
        author = json.get("author").getAsString();
        title = json.get("title").getAsString();
        description = json.get("description").getAsString();
        url = json.get("url").getAsString();
        datePublishedString = json.get("publishedAt").getAsString();
    }

    @Override
    public String toString() {
        return String.format("Author: %s, Title: %s, url: %s...", author, title, url);
    }
}