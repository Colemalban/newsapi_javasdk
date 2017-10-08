package newsApi.newsApiClient;

public enum Category implements UrlParameter {
    BUSINESS, ENTERTAINMENT, GAMING, GENERAL, MUSIC, POLITICS, SCIENCE, SPORT, TECHNOLOGY;

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
        } else if(this == SCIENCE) {
            return "science-and-nature";
        } else if(this == SPORT) {
            return "sport";
        } else {
            return "technology";
        }
    }

	@Override
	public String toQueryString() {
        return String.format("category=%s", this.toString());
	}
}