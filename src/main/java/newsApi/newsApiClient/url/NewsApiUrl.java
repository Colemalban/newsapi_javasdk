package newsApi.newsApiClient.url;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class NewsApiUrl {
   
    private Map<String, String> parameters;
    private String baseUrl;
    private String apiVersion;
    private String resource;


    public NewsApiUrl() {
        this.parameters = new HashMap<>();
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public void setBaseUrl(String base) {
        baseUrl = base;
    }

    public void setAPIVersion(String version) {
        apiVersion = version;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    private String getQueryString() {
        List<String> queryString = new LinkedList<>();
        for(String key : parameters.keySet()) {
            queryString.add(key + "=" + parameters.get(key));
        }
        return String.join("&", queryString);
    }
    public String toString() {
        return String.join("/", new String[]{baseUrl, apiVersion, resource, "?", getQueryString()});
    }

}