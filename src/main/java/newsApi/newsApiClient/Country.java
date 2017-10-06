package newsApi.newsApiClient;

public enum Country {
    AUSTRALIA, UNITED_STATES, GERMANY, GREAT_BRITAIN, INDIA, ITALY;

    public String toString() {
        if(this == AUSTRALIA) {
            return "au";
        } else if(this == UNITED_STATES) {
            return "us";
        } else if(this == GERMANY) {
            return "de";
        } else if(this == GREAT_BRITAIN) {
            return "gb";
        } else if(this == INDIA) {
            return "in";
        } else {
            return "it";
        }
    }
}