import newsApi.newsApiClient.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.*;

public class GetSourcesRequestTest {


    @Test
    public void testGetAllSources() {
        GetSourcesRequest req = new GetSourcesRequest.Builder().build();
        Collection<NewsSource> sources;
        try {
            sources = req.execute();
        } catch(IOException e) {
            fail("Failed http request");
            return;
        }
        for(NewsSource source : sources) {
            assertTrue(isValidSource(source));
        }
    }

    //TODO update this to go through every source
    @Test
    public void testFilterBySource() {
        GetSourcesRequest req = new GetSourcesRequest.Builder().category(Category.TECHNOLOGY).build();
        Collection<NewsSource> techSources;
        try {
            techSources = req.execute();
        } catch(IOException e) {
            fail("Failed HTTP request");
            return;
        }
        for(NewsSource src : techSources) {
            assertTrue(isValidSource(src));
            assertEquals(src.getCategory(), Category.TECHNOLOGY);
        }
    }

    //TODO update this to go through each country
    @Test
    public void testFilterByCountry() {
        GetSourcesRequest req = new GetSourcesRequest.Builder().country(Country.UNITED_STATES).build();
        Collection<NewsSource> countryFilteredSources;
        try {
            countryFilteredSources = req.execute();
        } catch(IOException e) {
            fail("Failed HTTP req");
            return;
        }
        for(NewsSource src : countryFilteredSources) {
            assertTrue(isValidSource(src));
            assertEquals(src.getCountry(), Country.UNITED_STATES);
        }
    }

    @Test
    public void testFilterByLanguage() {
        GetSourcesRequest req = new GetSourcesRequest.Builder().language(Language.ENGLISH).build();
        Collection<NewsSource> sources;
        try {
            sources = req.execute();
        } catch(IOException e) {
            fail("HTTP req failed");
            return;
        }
        for(NewsSource src : sources) {
            assertTrue(isValidSource(src));
            assertEquals(src.getLanguage(), Language.ENGLISH);
        }

    }

    @Test
    public void testFilterByLanguageCountryCategory() {
        GetSourcesRequest req = new GetSourcesRequest.Builder().category(Category.GENERAL).language(Language.ENGLISH).country(Country.GREAT_BRITAIN).build();
        Collection<NewsSource> sources;
        try {
            sources = req.execute();
        } catch(IOException e) {
            fail("HTTP req failed");
            return;
        }
        for(NewsSource src : sources) {
            assertTrue(isValidSource(src));
            assertEquals(src.getCountry(), Country.GREAT_BRITAIN);
            assertEquals(src.getLanguage(), Language.ENGLISH);
            assertEquals(src.getCategory(), Category.GENERAL);
        }
    }


    //TODO have some test utils here to check if a country, category, or language are valid
    private boolean isValidSource(NewsSource src) {
        if(src.getCategory() == null || src.getCountry() == null || src.getDescription() == null || src.getValidSorts() == null) {
            return false;
        }
        if(src.getId() == null || src.getLanguage() == null || src.getName() == null || src.getUrl() == null) {
            return false;
        }
        return true;
    }

}


