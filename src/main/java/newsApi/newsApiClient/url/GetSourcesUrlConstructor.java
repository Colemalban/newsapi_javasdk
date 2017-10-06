package newsApi.newsApiClient.url;

import java.util.Map;

//TODO feels like this and the constructor for articles have a lot of duplication. Try to fix that 
public class GetSourcesUrlConstructor implements UrlConstructor {

    public NewsApiUrl build(Map<String, String> params) {
        UrlBuilder builder = new UrlBuilder();
        builder.setAPIVersion("v1");
        builder.setBaseUrl("https://newsapi.org");
        builder.setResource("sources");
        for(String key : params.keySet()) {
            builder.setParameter(key, params.get(key));
        }
        return builder.createUrl();
    }
}