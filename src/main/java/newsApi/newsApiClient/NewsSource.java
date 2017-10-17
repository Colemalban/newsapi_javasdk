package newsApi.newsApiClient;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashSet;
import java.util.Set;

public class NewsSource {
    
    private String id;
    private String name;
    private String url;
    private String description;
    private Category category;
    private Language language;
    private Country country;
    private Set<SortByInstruction> sortByInstructions;
    private String smallLogoUrl;
    private String mediumLogoUrl;
    private String largeLogoUrl;

    public NewsSource(JsonObject object) {
        try {
            this.id = object.get("id").getAsString();
            this.name = object.get("name").getAsString();
            this.url = object.get("url").getAsString();
            this.description = object.get("description").getAsString();
            this.category = Category.stringToCategory(object.get("category").getAsString());
            this.language = Language.stringToLanguage(object.get("language").getAsString());
            this.country = Country.stringToCountry(object.get("country").getAsString());
            this.sortByInstructions = new HashSet<>();
            for(JsonElement ele : object.get("sortBysAvailable").getAsJsonArray()) {
                String sortBy = ele.getAsString();
                sortByInstructions.add(SortByInstruction.valueOf(sortBy.toUpperCase()));
            }
            JsonObject urls = object.get("urlsToLogos").getAsJsonObject();
            smallLogoUrl = urls.get("small").getAsString();
            mediumLogoUrl = urls.get("medium").getAsString();
            largeLogoUrl = urls.get("large").getAsString();
        } catch(NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isValidSortInstruction(SortByInstruction sort) {
        return sortByInstructions.contains(sort);
    }

    public Set<SortByInstruction> getValidSorts() {
        return sortByInstructions;
    }

    public String toString() {
        return this.name + " " + this.url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Language getLanguage() {
        return language;
    }

    public Country getCountry() {
        return country;
    }

    public String getSmallLogoUrl() {
        return smallLogoUrl;
    }

    public String getMediumLogoUrl() {
        return mediumLogoUrl;
    }

    public String getLargeLogoUrl() {
        return largeLogoUrl;
    }

}