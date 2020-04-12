package tests.e2e.web.tests.marketplace;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openset.automator.test.web.WebTest;
import tests.e2e.web.pages.marketplace.MarketplaceHomePage;
import tests.e2e.web.pages.marketplace.MarketplaceSearchPage;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Marketplace tests")
public class MarketplaceTests extends WebTest {

    @Nested
    @DisplayName("Marketplace home page tests.")
    class MarketplaceHomeTests {
        private MarketplaceHomePage marketplacePage;

        @BeforeEach
        void beforeEachCareerTests() {
            marketplacePage = new MarketplaceHomePage(context).navigateTo();
        }

        @Test
        @DisplayName("Header exists")
        void headerIsDisplayedOnMarketplacePage() {
            marketplacePage.header.validate();
        }

        @Test
        @DisplayName("Navigate to explore free apps")
        void exploreFreeApps() {
            MarketplaceSearchPage resultsPage = marketplacePage.exploreFreeApps();
            String url = resultsPage.getUrl();
            List<WebElement> results = resultsPage.getResults();
            assertTrue(url.contains("/category/free"), "Explore free apps leads to wrong Url.");
            if (context.settings.web.browserType.equals(BrowserType.SAFARI)) {
                // Safari see only items in view port and can not verify items are 20.
                assertTrue(results.size() > 10, "Not enough results shown.");
            } else {
                assertEquals(20, results.size(), "Page should show 20 results.");
            }
        }

        @Test
        @DisplayName("Navigate to explore actions")
        void exploreActions() {
            MarketplaceSearchPage resultsPage = marketplacePage.exploreActions();
            List<WebElement> results = resultsPage.getResults();
            if (context.settings.web.browserType.equals(BrowserType.SAFARI)) {
                // Safari see only items in view port and can not verify items are 20.
                assertTrue(results.size() > 10, "Not enough results shown.");
            } else {
                assertEquals(20, results.size(), "Page should show 20 results.");
            }
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Marketplace search tests.")
    class MarketplaceSearchTests {
        @Test
        @Order(1)
        @DisplayName("Search for apps")
        void searchForApps() {
            MarketplaceSearchPage resultsPage = new MarketplaceSearchPage(context, "marketplace?type=apps");
            resultsPage.searchFor("Azure Pipelines");
            await()
                    .atMost(10, SECONDS)
                    .untilAsserted(() -> assertEquals(1, resultsPage.getResults().size(),
                            "Search for 'Azure Pipelines' should return only one result"));
        }

        @Test
        @Order(2)
        @DisplayName("Search for actions")
        void searchForActions() {
            MarketplaceSearchPage resultsPage = new MarketplaceSearchPage(context, "marketplace?type=actions");
            resultsPage.searchFor("jdk");
            await()
                    .atMost(10, SECONDS)
                    .untilAsserted(() -> assertTrue(resultsPage.getResults().size() > 1,
                            "Failed to find results for 'jdk' actions."));
        }
    }
}
