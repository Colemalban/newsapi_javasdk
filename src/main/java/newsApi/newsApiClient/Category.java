package newsApi.newsApiClient;

public enum Category {
    BUSINESS, ENTERTAINMENT, GAMING, GENERAL, MUSIC, POLITICS, SCIENCE_AND_NATURE, SPORT, TECHNOLOGY;

    public String toString() {
        if(this == BUSINESS) {
            return "business";
        } else if(this == ENTERTAINMENT) {
            return "entertainment";
        } else if(this == GAMING) {
            return "gaming";
        } else if(this == GENERAL) {
            return "general";
        } else if(this == MUSIC) {
            return "music";
        } else if(this == POLITICS) {
            return "politics";
        } else if(this == SCIENCE_AND_NATURE) {
            return "science-and-nature";
        } else if(this == SPORT) {
            return "sport";
        } else {
            return "technology";
        }
    }

   public static Category stringToCategory(String str) {
        return Category.valueOf(str.toUpperCase().replace('-','_'));
   }
}