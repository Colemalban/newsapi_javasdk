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

    public static Language stringToLanguage(String str) {
        switch(str) {
            case "en":
                return ENGLISH;
            case "fr":
                return FRENCH;
            case "de":
                return GERMAN;
            default:
                throw new IllegalArgumentException();
        }
    }

}