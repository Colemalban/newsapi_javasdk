import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newsApi.newsApiClient.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NewsSourceTest {

    private JsonObject validSource;
    private JsonObject emptySource;
    private JsonObject missingAttributesSource;

    @Before
    public void setup() {
        String validString = "{\"id\":\"abc-news-au\",\"name\":\"ABC News (AU)\",\"description\":\"Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.\",\"url\":\"http://www.abc.net.au/news\",\"category\":\"general\",\"language\":\"en\",\"country\":\"au\",\"urlsToLogos\":{\"small\":\"\",\"medium\":\"\",\"large\":\"\"},\"sortBysAvailable\":[\"top\"]}";
        validSource = new JsonParser().parse(validString).getAsJsonObject();
        String emptyString = "{}";
        emptySource = new JsonParser().parse(emptyString).getAsJsonObject();
        String missingString = "{\"id\":\"abc-news-au\",\"name\":\"ABC News (AU)\",\"description\":\"Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.\",\"category\":\"general\",\"language\":\"en\",\"country\":\"au\",\"urlsToLogos\":{\"small\":\"\",\"medium\":\"\",\"large\":\"\"},\"sortBysAvailable\":[\"top\"]}";
        missingAttributesSource = new JsonParser().parse(missingString).getAsJsonObject();
    }

    @Test
    public void testValidSource() {
        NewsSource s = new NewsSource(validSource);
        assertEquals(s.getCategory(), Category.GENERAL);
        assertEquals(s.getCountry(), Country.AUSTRALIA);
        assertEquals(s.getId(), "abc-news-au");
        assertEquals(s.getUrl(), "http://www.abc.net.au/news");
        assertEquals(s.getDescription(), "Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.");
        assertEquals(s.getLanguage(), Language.ENGLISH);
        assertEquals(s.getName(), "ABC News (AU)");
        assertTrue(s.isValidSortInstruction(SortByInstruction.TOP));
        assertFalse(s.isValidSortInstruction(SortByInstruction.LATEST));
        assertEquals(s.getSmallLogoUrl(), "");
        assertEquals(s.getMediumLogoUrl(), "");
        assertEquals(s.getLargeLogoUrl(), "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySource() {
        NewsSource s = new NewsSource(emptySource);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingAttributesSource() {
        NewsSource s = new NewsSource(missingAttributesSource);
    }

}
