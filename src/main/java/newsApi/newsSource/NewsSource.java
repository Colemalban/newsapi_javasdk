package newsApi.newsSource;

import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.HashSet;

public class NewsSource {
    
    private String id;
    private String name;
    private String url;
    private String description;
    private String category;
    private String language;
    private String country;
    private Collection<String> sortByInstructions;

    public NewsSource(JsonObject object) {
        this.id = object.get("id").getAsString();
        this.name = object.get("name").getAsString();
        this.url = object.get("url").getAsString();
        this.description = object.get("description").getAsString();
        this.category = object.get("category").getAsString();
        this.language = object.get("language").getAsString();
        this.country = object.get("country").getAsString();
        this.sortByInstructions = new HashSet<>();
    }

    public String toString() {
        return this.name + " " + this.url;
    }

    public String getId() {
        return id;
    }
}