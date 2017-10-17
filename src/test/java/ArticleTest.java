import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newsApi.newsApiClient.Article;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleTest {


    private JsonObject validJsonObject;
    private JsonObject emptyJsonObject;
    private JsonObject missingAttributesObject;

    @Before
    public void setupTest() {
        String emptyJsonString = "{}";
        emptyJsonObject = new JsonParser().parse(emptyJsonString).getAsJsonObject();
        String validJsonString = "{\"author\":\"Inés Casserly\",\"title\":\"An addict’s tale: My week without social media\",\"description\":\"By Monday I’d deleted Instagram, Twitter, Snapchat, Facebook, Pinterest and LinkedIn applications off my phone and logged out of all accounts on my laptop: it had begun Hi, my name is Inés and I’m a social media addict. But this week, I decided to go through digital detox by turning off all of my major …\",\"url\":\"https://thenextweb.com/insider/2017/10/16/addicts-tale-week-without-social-media/\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2014/12/chained-smartphone-addict-social.jpg\",\"publishedAt\":\"2017-10-16T19:04:20Z\"}";
        validJsonObject = new JsonParser().parse(validJsonString).getAsJsonObject();
        String missingAttributesString = "{\"title\":\"An addict’s tale: My week without social media\",\"description\":\"By Monday I’d deleted Instagram, Twitter, Snapchat, Facebook, Pinterest and LinkedIn applications off my phone and logged out of all accounts on my laptop: it had begun Hi, my name is Inés and I’m a social media addict. But this week, I decided to go through digital detox by turning off all of my major …\",\"url\":\"https://thenextweb.com/insider/2017/10/16/addicts-tale-week-without-social-media/\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2014/12/chained-smartphone-addict-social.jpg\"}";
        this.missingAttributesObject = new JsonParser().parse(missingAttributesString).getAsJsonObject();
    }

    @Test
    public void testCreateRegularArticle() {
        Article article = new Article(validJsonObject);
        assertEquals(article.getAuthor(), "Inés Casserly");
        assertEquals(article.getTitle(), "An addict’s tale: My week without social media");
        assertEquals(article.getUrl(), "https://thenextweb.com/insider/2017/10/16/addicts-tale-week-without-social-media/");
        assertEquals(article.getDescription(), "By Monday I’d deleted Instagram, Twitter, Snapchat, Facebook, Pinterest and LinkedIn applications off my phone and logged out of all accounts on my laptop: it had begun Hi, my name is Inés and I’m a social media addict. But this week, I decided to go through digital detox by turning off all of my major …");
        assertEquals(article.getDatePublishedString(),"2017-10-16T19:04:20Z");
        assertEquals(article.getImageUrl(), "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2014/12/chained-smartphone-addict-social.jpg");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmptyArticle() {
        Article a = new Article(emptyJsonObject);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingAttributes() {
        Article a = new Article(missingAttributesObject);
    }

}
