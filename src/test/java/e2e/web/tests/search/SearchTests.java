package e2e.web.tests.search;

import e2e.web.pages.search.AdvancedSearchPage;
import e2e.web.pages.search.SearchResultsPage;
import e2e.web.pages.search.SimpleSearchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Search tests")
public class SearchTests extends WebTest {

    @Nested
    @DisplayName("Simple search form tests")
    class SimpleSearchFormTests {
        private SimpleSearchPage searchPage;

        @BeforeEach
        void beforeEach() {
            searchPage = new SimpleSearchPage(context).navigateTo();
        }

        @Test
        void searchInSimpleSearchForm() {
            SearchResultsPage searchResultsPage = searchPage.searchFor("Appium");
            String url = searchResultsPage.getUrl();
            assertTrue(url.contains("q=Appium&ref=simplesearch"),
                    "Search in simple search form do not lead to correct URL. \nCurrent URL: " + url);
        }

        @Test
        void linkToAdvancedSearchFormExists() {
            String expectedLink = "https://github.com/search/advanced";
            String actualLink = searchPage.getLinkByText("advanced search");
            assertEquals(expectedLink, actualLink, "Advanced search link leads to wrong url.");
        }

        @Test
        void prefixesLinkOpensCheatSheetForm() {
            searchPage.clickPrefixesLink();
            searchPage.searchCheatSheetFormDisplayed();
            searchPage.closeSearchCheatSheetForm();
        }
    }

    @Nested
    @DisplayName("Advanced search form tests")
    class AdvancedSearchFormTests {
        private AdvancedSearchPage searchPage;

        @BeforeEach
        void beforeEach() {
            searchPage = new AdvancedSearchPage(context).navigateTo();
        }

        @Test
        void searchInAdvancedSearchForm() {
            SearchResultsPage searchResultsPage = searchPage.searchFor("Appium");
            String url = searchResultsPage.getUrl();
            assertTrue(url.contains("q=Appium&type=Repositories&ref=advsearch"),
                    "Search in advanced search form do not lead to correct URL. \nCurrent URL: " + url);
        }

        @Test
        void searchWithAdvancedOptions() {
            // Search
            String searchOptions = "automator user:dtopuzov language:Java license:apache-2.0";
            SearchResultsPage searchResultsPage = searchPage.searchFor(searchOptions);

            // Verify Url
            String url = searchResultsPage.getUrl();
            String params = "q=automator+user%3Adtopuzov+language%3AJava+license%3Aapache-2.0&type=Repositories";
            assertTrue(url.contains(params), "Search do not lead to correct URL. \nCurrent URL: " + url);

            // Verify only one result is found
            assertEquals(1, searchResultsPage.getResults().size(), "Wrong number of results.");
        }
    }
}
