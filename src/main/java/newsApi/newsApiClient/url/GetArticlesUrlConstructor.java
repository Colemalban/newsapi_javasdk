package newsApi.newsApiClient.url;

import java.util.Map;

public class GetArticlesUrlConstructor implements UrlConstructor {

    @Override 
    public NewsApiUrl build(Map<String, String> params) {
        UrlBuilder builder = new UrlBuilder();
        builder.setAPIVersion("v1");
        builder.setBaseUrl("https://newsapi.org");
        builder.setResource("articles");
        for(String key : params.keySet()) {
            builder.setParameter(key, params.get(key));
        }
        return builder.createUrl();
    }
}