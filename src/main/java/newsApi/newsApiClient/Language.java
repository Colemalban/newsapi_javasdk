package newsApi.newsApiClient;

public enum Language {
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
}