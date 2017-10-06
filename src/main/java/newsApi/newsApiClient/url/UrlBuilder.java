package newsApi.newsApiClient.url;

public class UrlBuilder {

    private NewsApiUrl url;

    public UrlBuilder() {
        url = new NewsApiUrl();
    }  

	public void setParameter(String key, String value) {
		url.addParameter(key, value);
	}

	public void setBaseUrl(String baseUrl) {
		url.setBaseUrl(baseUrl);
	}

	public void setAPIVersion(String version) {
		url.setAPIVersion(version);
	}

	public void setResource(String resource) {
        url.setResource(resource);
	}

	public NewsApiUrl createUrl() {
		return url;
	}

}