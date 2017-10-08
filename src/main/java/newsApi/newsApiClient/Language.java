package newsApi.newsApiClient;

public enum Language implements UrlParameter {
    ENGLISH, GERMAN, FRENCH;

    public String toString() {
        if(this == Language.ENGLISH) {
            return "en";
        } else if(this == Language.FRENCH) {
            return "fr";
        } else {
            return "de";
        }
    }

	@Override
	public String toQueryString() {
        return String.format("language=%s", this.toString());
	}

    
}