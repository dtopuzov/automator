package e2e.web.tests;

import e2e.web.pages.HomePage;
import e2e.web.pages.ResultsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Google search tests")
public class SmokeSearchTests extends WebTest {
    @Test
    @DisplayName("Search from home page")
    void searchFromHomePage() {
        HomePage googleHomePage = new HomePage(context);
        ResultsPage resultsPage = googleHomePage.searchFor("lorem ipsum");

        // Example of soft asserts
        assertAll(
                () -> resultsPage.verifyResultExists("https://www.lipsum.com"),
                () -> resultsPage.verifyResultExists("https://loremipsum.io")
        );
    }

    @Test
    @DisplayName("Search from results page")
    void searchFromResultsPage() {
        // Load result page and verify initial results
        ResultsPage resultsPage = new ResultsPage(context, "loremipsum");
        resultsPage.verifyResultExists("https://www.lipsum.com");

        // Perform new search and verify results
        resultsPage.searchFor("gmail");
        resultsPage.verifyResultExists("https://www.google.com/gmail/");
        resultsPage.verifyResultDoesNotExist("https://www.lipsum.com");
    }
}