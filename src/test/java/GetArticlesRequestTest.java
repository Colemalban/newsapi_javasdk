import com.google.gson.JsonParser;
import newsApi.newsApiClient.Article;
import newsApi.newsApiClient.GetArticlesRequest;
import newsApi.newsApiClient.NewsSource;
import newsApi.newsApiClient.SortByInstruction;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.*;

public class GetArticlesRequestTest {

    private String key;
    private NewsSource source;


    @Before
    public void setup() {
        key = "7207b9a25f2c4f79856d5d2caf4bd1b6";
        String sourceString = "{\"id\":\"abc-news-au\",\"name\":\"ABC News (AU)\",\"description\":\"Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.\",\"url\":\"http://www.abc.net.au/news\",\"category\":\"general\",\"language\":\"en\",\"country\":\"au\",\"urlsToLogos\":{\"small\":\"\",\"medium\":\"\",\"large\":\"\"},\"sortBysAvailable\":[\"top\"]}";
        source = new NewsSource(new JsonParser().parse(sourceString).getAsJsonObject());
    }

    @Test
    public void testGetAllArticles() {
        GetArticlesRequest req = new GetArticlesRequest.Builder(key, source).build();
        Collection<Article> articles;
        try {
            articles = req.execute();
        } catch(IOException e) {
            fail("Failed article http req");
            return;
        }
        for(Article a : articles) {
            assertTrue(isValidArticle(a));
        }
    }

    @Test
    public void testSortByValid() {
        GetArticlesRequest req = new GetArticlesRequest.Builder(key, source).sortBy(SortByInstruction.TOP).build();
        Collection<Article> articles;
        try {
            articles = req.execute();
        } catch(IOException e) {
            fail("HTTP error");
            return;
        }
        for(Article a : articles) {
            assertTrue(isValidArticle(a));
        }
    }

    @Test
    public void testSortByInvalid() {
        //This should just default to TOP(as specified by the API)
        GetArticlesRequest req = new GetArticlesRequest.Builder(key, source).sortBy(SortByInstruction.LATEST).build();
        Collection<Article> articles;
        try {
            articles = req.execute();
        } catch(IOException e) {
            fail("HTTP error");
            return;
        }
        for(Article a : articles) {
            assertTrue(isValidArticle(a));
        }
    }

    private boolean isValidArticle(Article ele) {
        if(ele.getUrl() == null || ele.getDescription() == null || ele.getAuthor() == null) {
            return false;
        }
        if(ele.getDatePublishedString() == null || ele.getTitle() == null || ele.getImageUrl() == null) {
            return false;
        }
        return true;
    }

}
