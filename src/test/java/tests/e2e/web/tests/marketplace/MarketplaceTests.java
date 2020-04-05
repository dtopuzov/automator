package tests.e2e.web.tests.marketplace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openset.automator.test.web.WebTest;
import tests.e2e.web.pages.marketplace.MarketplaceHomePage;
import tests.e2e.web.pages.marketplace.MarketplaceSearchPage;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

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
            assertAll(
                    () -> assertTrue(url.contains("/category/free"), "Explore free apps leads to wrong Url."),
                    () -> assertEquals(20, results.size(), "Page should show 20 results.")
            );
        }

        @Test
        @DisplayName("Navigate to explore actions")
        void exploreActions() {
            MarketplaceSearchPage resultsPage = marketplacePage.exploreActions();
            String url = resultsPage.getUrl();
            List<WebElement> results = resultsPage.getResults();
            assertAll(
                    () -> assertTrue(url.contains("?type=actions"), "Explore actions leads to wrong Url."),
                    () -> assertEquals(20, results.size(), "Page should show 20 results.")
            );
        }
    }

    @Nested
    @DisplayName("Marketplace search tests.")
    class MarketplaceSearchTests {
        @Test
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
