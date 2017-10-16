package newsApi.newsApiClient;

import com.google.gson.JsonObject;

/**
 * This class will represent an article returned from the API.
 * 
 */
public class Article {
    private String author;
    private String title;
    private String url;
    private String description;
    private String datePublishedString;

    /**
     * @param json A JsonObject that contains all the data to be parsed into an article object 
     * json. Must include author, title, description, url, publishedAt
     *
     */
    public Article(JsonObject json) {
        author = json.get("author").getAsString();
        title = json.get("title").getAsString();
        description = json.get("description").getAsString();
        url = json.get("url").getAsString();
        datePublishedString = json.get("publishedAt").getAsString();
    }

    /**
     * @return a string representation of the form (Author:.., Title:..., url:...)
     */
    @Override
    public String toString() {
        return String.format("Author: %s, Title: %s, url: %s...", author, title, url);
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getDatePublishedString() {
        return datePublishedString;
    }
}