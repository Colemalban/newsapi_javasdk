package newsApi.newsApiClient;

public enum SortByInstruction {
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
}