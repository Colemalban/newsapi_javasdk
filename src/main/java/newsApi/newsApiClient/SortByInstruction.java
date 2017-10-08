package newsApi.newsApiClient;

public enum SortByInstruction implements UrlParameter {
    TOP, LATEST, POPULAR;

    public String toString() {
        if(this == SortByInstruction.TOP) {
            return "top";
        } else if (this == SortByInstruction.LATEST) {
            return "latest";
        } else {
            return "popular";
        }
    }

    public String toQueryString() {
        return String.format("sortBy=%s", this.toString());
    }
}