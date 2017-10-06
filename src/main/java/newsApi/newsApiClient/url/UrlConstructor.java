package newsApi.newsApiClient.url;

import java.util.Map;

public interface UrlConstructor {
    public NewsApiUrl build(Map<String, String> params);
}